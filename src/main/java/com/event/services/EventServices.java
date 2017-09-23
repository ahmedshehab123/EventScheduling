package com.event.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
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
import com.event.model.JudgeSubscribers;
import com.event.model.Judges;
import com.event.model.Sponsors;
import com.event.model.Subscribers;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class EventServices {

	@Autowired
	MongoOperations mongoTemplate;

	@Autowired
	GridFsTemplate gridFsTemplate;

	public Event createEvent(Event event, MultipartFile image, MultipartFile faqFile) {

		mongoTemplate.insert(event);
		try {
			String faqName = "faqFile";
			gridFsTemplate.store(image.getInputStream(), event.getEventID());
			gridFsTemplate.store(faqFile.getInputStream(), faqName, event.getEventID());
			System.out.println("faq file name");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;

	}

	public void updateEventPhoto(MultipartFile image, String eventID) {
		try {GridFSDBFile imageDbFile = gridFsTemplate
				.findOne(new Query().addCriteria(Criteria.where("filename").is(eventID)));
		
		
			if(imageDbFile==null){gridFsTemplate.store(image.getInputStream(), eventID);
			}else{
				
			gridFsTemplate.delete(new BasicQuery(imageDbFile));
			gridFsTemplate.store(image.getInputStream(), eventID);}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateFaqFile(MultipartFile faqFile, String eventID) {
		String faqName = "faqFile";
		try {
			GridFSDBFile imageDbFile = gridFsTemplate.findOne(new Query().addCriteria(
					Criteria.where("filename").is("faqFile").andOperator(Criteria.where("contentType").is(eventID))));
			if(imageDbFile==null){
				gridFsTemplate.store(faqFile.getInputStream(), faqName, eventID);
			}else{
			gridFsTemplate.delete(new BasicQuery(imageDbFile));
			gridFsTemplate.store(faqFile.getInputStream(), faqName, eventID);}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] getEventBackgroundImage(String eventId) {

		GridFSDBFile imageDbFile = gridFsTemplate
				.findOne(new Query().addCriteria(Criteria.where("filename").is(eventId)));
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

	public byte[] getFAQ(String eventId) {

		GridFSDBFile imageDbFile = gridFsTemplate.findOne(new Query().addCriteria(
				Criteria.where("filename").is("faqFile").andOperator(Criteria.where("contentType").is(eventId))));
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

	public void deleteEvent(String eventID) {
		BasicQuery query = new BasicQuery("{eventID:'" + eventID + "'}");
		mongoTemplate.remove(query, AcceptedSponsors.class);
		mongoTemplate.remove(query, Sponsors.class);
		mongoTemplate.remove(query, Judges.class);
		mongoTemplate.remove(query, JudgeSubscribers.class);
		mongoTemplate.remove(query, Subscribers.class);
		mongoTemplate.remove(query, Event.class);

	}

	public List<Event> getUserEvents(String id) {
		BasicQuery query = new BasicQuery("{userID: '" + id + "' }");
		List<Event> events = mongoTemplate.find(query, Event.class);
		return events;
	}

	public List<Event> invitationEvent(String id) {
		BasicQuery query = new BasicQuery("{eventID: '" + id + "' }");
		List<Event> events = mongoTemplate.find(query, Event.class);
		return events;

	}

	public List<Event> getUserEventsTolandingPAge() {
		List<Event> events = mongoTemplate.findAll(Event.class);
		return events;
	}

	public List<Event> getEventInfoTolandingPAge(String id) {
		BasicQuery query = new BasicQuery("{eventID: '" + id + "' }");
		List<Event> events = mongoTemplate.find(query, Event.class);
		return events;
	}
	public List<Event> searchByTitle(String title) {
		BasicQuery query = new BasicQuery("{\"title\": {$regex : '" + title + "'}}");
		List<Event> events = mongoTemplate.find(query, Event.class);
		return events;
	}

	public List<Event> searchEvent(String title, String location, String date) {
		// BasicQuery query=new BasicQuery("{ '$or': [ { title: /'"+title+"'/ }
		// , { location: /'"+location+"'/ } ,{date:/'"+date+"'/}] }");
		BasicQuery query = new BasicQuery("{'$or': [{\"title\": {$regex : '" + title + "'} },"
				+ "{\"location\": {$regex : '" + location + "'} },{\"regDate\": {$regex : '" + date + "'} }]}");
		List<Event> events = mongoTemplate.find(query, Event.class);
		return events;
	}
}