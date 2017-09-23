package com.event.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="scores")
public class Score {
    private int scoreValue;
    private String eventID;
  //  private String JudgeEmail;
    private String judgeEmail;
    
	public int getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getJudgeEmail() {
		return judgeEmail;
	}
	public void setJudgeEmail(String judgeEmail) {
		this.judgeEmail = judgeEmail;
	}
	
    
    
}
