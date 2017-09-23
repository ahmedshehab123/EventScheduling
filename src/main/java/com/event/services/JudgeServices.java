package com.event.services;


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
import org.springframework.stereotype.Service;
import com.event.model.Judges;
import com.event.model.JudgeSubscribers;

@Service
public class JudgeServices {
	@Autowired
	MongoOperations mongoTemplate;

	public void saveJudgeInfo(Judges judge) {

		mongoTemplate.insert(judge);

	}

	public List<Judges> sendJudgeNotification(String email) {
		BasicQuery query = new BasicQuery("{toAddress: '" + email + "' }");
		List<Judges> judges = mongoTemplate.find(query, Judges.class);
		return judges;
	}

	public List<Judges> checkJudgeInvitation(String eventID, String toAddress) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "',toAddress: '" + toAddress + "' }");
		List<Judges> judges = mongoTemplate.find(query, Judges.class);
		return judges;
	}

	public List<Judges> sendJudgeInfo(String id) {
		BasicQuery query = new BasicQuery("{eventID: '" + id + "' }");
		List<Judges> judges = mongoTemplate.find(query, Judges.class);
		return judges;
	}

	public JudgeSubscribers setSubscriber(JudgeSubscribers subscribe) {

		mongoTemplate.insert(subscribe);
		return subscribe;
	}

	public void unsubscribe(String eventID, String subscriberEmail) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "', subscriberEmail: '" + subscriberEmail + "' }");
		mongoTemplate.remove(query, JudgeSubscribers.class);
	}

	public List<JudgeSubscribers> getJudgeSubscriber(String eventID, String subscriberEmail) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "', subscriberEmail: '" + subscriberEmail + "' }");
		List<JudgeSubscribers> subscribers = mongoTemplate.find(query, JudgeSubscribers.class);
		return subscribers;
	}

	public List<JudgeSubscribers> displaySubscribersJudges(String eventID) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "'}");
		List<JudgeSubscribers> subscribers = mongoTemplate.find(query, JudgeSubscribers.class);
		return subscribers;
	}

	public void sendJudgeEmail(String toAddress, String fromAddress, String eventID) {

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
			message.setSubject("Event invitation");
			message.setText(
					"Hello i would like to invit you to my event" + "please join us by accessing this link thanks,\n "
							+ "http://localhost:8080/EventScheduling/checkjudgeinvitation?eventID=" + eventID + "");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	

	
}
