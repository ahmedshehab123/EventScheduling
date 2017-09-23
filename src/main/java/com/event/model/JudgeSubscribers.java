package com.event.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="judgeSubscribers")
public class JudgeSubscribers {
	private String eventID;
	private String subscriberEmail;
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getSubscriberEmail() {
		return subscriberEmail;
	}
	public void setSubscriberEmail(String subscriberEmail) {
		this.subscriberEmail = subscriberEmail;
	}
	

}
