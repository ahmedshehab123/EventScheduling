package com.event.model;



import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="judges")
public class Judges {
	@Id
	private String JudgeID;
	private String toAddress;
	private String eventID;
	private String fromAddress;
	private boolean be48hFlag;
	
	
	
	public String getJudgeID() {
		return JudgeID;
	}
	public void setJudgeID(String judgeID) {
		JudgeID = judgeID;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public boolean isBe48hFlag() {
		return be48hFlag;
	}
	public void setBe48hFlag(boolean be48hFlag) {
		this.be48hFlag = be48hFlag;
	}
	
	
	
    
}
