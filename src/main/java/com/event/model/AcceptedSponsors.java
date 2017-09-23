package com.event.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="acceptedsponsors")
public class AcceptedSponsors {
	private String eventID;
	private String sponsorEmail;
	
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
	
	

}
