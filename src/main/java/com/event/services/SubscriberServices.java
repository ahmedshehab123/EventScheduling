package com.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;
import com.event.model.Subscribers;

@Service
public class SubscriberServices {
	@Autowired
	MongoOperations mongoTemplate;
	public void getSubscription(Subscribers subscriber){
		mongoTemplate.insert(subscriber);
		
	}
	public void removesubscriber(String eventID,String email){
		   
		   BasicQuery query=new BasicQuery("{eventID:'"+eventID+"', subscriberEmail : '"+email+"'}");
			    mongoTemplate.remove(query, Subscribers.class);
			
		   
	   }
   public List<Subscribers> getsubInfo(String eventID,String email){
	   
	   BasicQuery query=new BasicQuery("{eventID:'"+eventID+"', subscriberEmail : '"+email+"'}");
		 List<Subscribers> userSubscribe=   mongoTemplate.find(query, Subscribers.class);
		return userSubscribe;
	   
   }
}
