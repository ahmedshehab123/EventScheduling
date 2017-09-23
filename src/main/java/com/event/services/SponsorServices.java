package com.event.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.event.model.AcceptedSponsors;
import com.event.model.Event;
import com.event.model.Images;
import com.event.model.Sponsors;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class SponsorServices {
	@Autowired
	MongoOperations mongoTemplate;
	@Autowired
	GridFsTemplate gridFsTemplate;

	public void saveSponsorinfo(Sponsors sponsor) {
		mongoTemplate.insert(sponsor);
	}

	public void saveAcceptedSponsor(AcceptedSponsors sponsor) {
		mongoTemplate.insert(sponsor);
	}

	public void removeSponsor(String eventID, String email) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "',sponsorEmail:'" + email + "'}");
		mongoTemplate.remove(query, AcceptedSponsors.class);

	}

	public List<Sponsors> sendSponsorOffer(String ownerEmail) {
		BasicQuery query = new BasicQuery("{ sponsorEmail : '" + ownerEmail + "'}");
		List<Sponsors> sponsors = mongoTemplate.find(query, Sponsors.class);
		return sponsors;

	}

	public List<Event> sponsorEvent(String id) {
		BasicQuery query = new BasicQuery("{eventID: '" + id + "' }");
		List<Event> events = mongoTemplate.find(query, Event.class);
		return events;

	}

	public List<Sponsors> checkSponsorlist(String eventID, String email) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "',sponsorEmail:'" + email + "'}");
		List<Sponsors> checkSponsorOffer = mongoTemplate.find(query, Sponsors.class);
		return checkSponsorOffer;

	}

	public List<AcceptedSponsors> checkAcceptedSponosorOffer(String eventID, String email) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "',sponsorEmail:'" + email + "'}");
		List<AcceptedSponsors> checkAcceptedOffer = mongoTemplate.find(query, AcceptedSponsors.class);
		return checkAcceptedOffer;

	}

	public List<Sponsors> diplaySponsorList(String eventID) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "'}");
		List<Sponsors> checkSponsorOffer = mongoTemplate.find(query, Sponsors.class);
		return checkSponsorOffer;

	}

	public List<AcceptedSponsors> dipalyAcceptedSponsor(String eventID) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "'}");
		List<AcceptedSponsors> checkAcceptedOffer = mongoTemplate.find(query, AcceptedSponsors.class);
		return checkAcceptedOffer;

	}

	public List<Sponsors> getOwnerEmail(String eventID) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "'}");
		List<Sponsors> ownerEmail = new ArrayList<Sponsors>();
		Sponsors sponsor = mongoTemplate.findOne(query, Sponsors.class);
		ownerEmail.add(sponsor);
		return ownerEmail;

	}

	public void sendSponsorEmail(String sponsorEmail, String ownerEmail, String eventID) {

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
			message.setFrom(new InternetAddress(ownerEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sponsorEmail));
			message.setSubject("Event invitation");
			message.setText(
					"Hello i would like to invit you to my event" + "please join us by accessing this link thanks,\n "
							+ "http://localhost:8080/EventScheduling/displaysponsornotify?eventID=" + eventID + "");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void contctEventOwner(String owneremail, String useremail, String eventID, String userMessage) {

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
			message.setFrom(new InternetAddress(useremail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(owneremail));
			message.setSubject("Replay from " + useremail + "");
			message.setText(userMessage + "\t" + useremail);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public void sendEventOwnerImages(String owneremail, String useremail, String eventID, 
			String imageID) {

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
			message.setFrom(new InternetAddress(useremail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(owneremail));
			message.setSubject("Replay from " + useremail + "");
			message.setText("http://localhost:8080/EventScheduling/displaysponsorimage?imageID="+imageID+"" + "\t" + useremail);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public Images saveSponsorImage(Images imgObj, MultipartFile image) {

		mongoTemplate.insert(imgObj);
		try {
			gridFsTemplate.store(image.getInputStream(), imgObj.getImageID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgObj;

	}

	public byte[] displaySponsorImages(String id) {

		GridFSDBFile imageDbFile = gridFsTemplate
				.findOne(new Query().addCriteria(Criteria.where("filename").is(id)));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		if (imageDbFile != null) {
			try {
				imageDbFile.writeTo(os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return os.toByteArray();

	}

	public List<Images> getSponsorImage(String userID) {
		BasicQuery query = new BasicQuery("{userID:'" + userID + "'}");
		List<Images> images = mongoTemplate.find(query, Images.class);
		return images;
	}
}
