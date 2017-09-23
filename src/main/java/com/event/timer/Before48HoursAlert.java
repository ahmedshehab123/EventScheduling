package com.event.timer;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;

import com.event.model.Event;
import com.event.model.Judges;
import com.mongodb.BasicDBObject;

public class Before48HoursAlert {
	@Autowired
	MongoOperations mongoTemplate;

	public void sendMail() throws ParseException {
		System.out.println("we are here");
		Date now = new Date();
		System.out.println("now "+now);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		calendar.set(Calendar.SECOND, 0);
		System.out.println("calnder date " + calendar.getTime());
		BasicDBObject basicDataBaseObject = new BasicDBObject();
		basicDataBaseObject.put("regDate", new BasicDBObject("$gte", now).append("$lte", calendar.getTime()));
		//basicDataBaseObject.put("regDate", new BasicDBObject("$lte", calendar.getTime()));
		BasicQuery query = new BasicQuery(basicDataBaseObject);

		List<Event> events = mongoTemplate.find(query, Event.class);
		System.out.println(" size " + events.size());
		for (Event event : events) {
			System.out.println("regestration date" + event.getRegDate());
			BasicQuery querytojudges = new BasicQuery("{eventID:'" + event.getEventID() + "',be48hFlag:false }");
			List<Judges> judges = mongoTemplate.find(querytojudges, Judges.class);
			for (Judges judge : judges) {
				System.out.println("judges size" + judges.size());
				sendMailAlert(judge.getToAddress(), judge.getFromAddress(), judge.getEventID());

			}
			BasicDBObject selectQuery = new BasicDBObject().append("eventID", event.getEventID()).append("be48hFlag",
					false);
			Update updateQuery = new Update().set("be48hFlag", true);
			mongoTemplate.updateMulti(new BasicQuery(selectQuery), updateQuery, Judges.class);

		}
	}

	public void sendMailAlert(String toAddress, String fromAddress, String eventID) {
		final String username = "eventschedulingsite@gmail.com";
		final String password = "andreaandrea";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject("Event invitation warrning");
			String body = "<html> <body><h2>Hello, </h2> <br> we would to remeber you that the last chance to accept invitation is after two days"
					+ "please join us by accessing this link thanks,\n "
					+ "http://localhost:8080/EventScheduling/checkjudgeinvitation?eventID=" + eventID + " </body> </html>";
			message.setContent(body, "text/html; charset=utf-8");
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
