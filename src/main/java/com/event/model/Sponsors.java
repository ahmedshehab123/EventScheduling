package com.event.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sponsors")
public class Sponsors {
	private String eventID;
	private String sponsorEmail;
	private String ownerEmail;
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getSponsorEmail() {
		return sponsorEmail;
	}
	public void setSponsorEmail(String sponsorEmail) {
		this.sponsorEmail = sponsorEmail;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	

}
