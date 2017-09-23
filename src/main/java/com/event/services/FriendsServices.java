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

import com.event.model.Friends;
import com.event.model.Judges;

@Service
public class FriendsServices {
	@Autowired
    MongoOperations mongoTemplate;
	public void saveFreinds(Friends friends){
		mongoTemplate.insert(friends);
		
	}
	public List<Friends> getfriends(String userID){
		BasicQuery query=new BasicQuery("{userID:'"+userID+"'}");
		List<Friends> friends=mongoTemplate.find(query, Friends.class);
		return friends;
	}
	
	public Judges inviteFriendsList(String toAddress, String fromAddress, String eventID) {
         Judges judge=new Judges();
         judge.setEventID(eventID);
         judge.setFromAddress(fromAddress);
         judge.setToAddress(toAddress);
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
		return judge;
	}
	public void deleteFriend(String friendID){
		BasicQuery query=new BasicQuery("{friendID:'"+friendID+"'}");
		mongoTemplate.remove(query, Friends.class);
	}
	public void clearList(String userID){
		BasicQuery query=new BasicQuery("{userID:'"+userID+"'}");
		mongoTemplate.remove(query, Friends.class);
	}
}
