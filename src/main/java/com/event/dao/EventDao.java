package com.event.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.event.model.Event;

public class EventDao  {
	
	static 	SimpleDateFormat defaultDateFormater =new SimpleDateFormat("MM/dd/yyyy h:mm aa");
	
	private String userID;
	private String organizerName;
	private String organizerDescription;
    @NotEmpty(message="you cann't create event with out title")
	private String title;
    @NotEmpty(message="you cann't create event with out description")
	private String description;
    @NotEmpty(message="insert event location")
	private String location;
	private Date startDate_obj;
	private Date regDate_obj;
	private Date eventDateAndTime_obj;
    @NotEmpty(message=" insert event date")
	private String startDate;
    @NotEmpty(message=" insert deadline to register")
	private String regDate;
    @NotEmpty(message=" insert event date andtime")
    private String eventDateAndTime;
	private String subject;
	private Boolean status;
	MultipartFile image;
	MultipartFile faqFile;
	
	
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOrganizerName() {
		return organizerName;
	}
	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}
	public String getOrganizerDescription() {
		return organizerDescription;
	}
	public void setOrganizerDescription(String organizerDescription) {
		this.organizerDescription = organizerDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		try {
			this.startDate_obj = defaultDateFormater.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.startDate = startDate;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		try {
			this.regDate_obj = defaultDateFormater.parse(regDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.regDate = regDate;
	}
	
	public String getEventDateAndTime() {
		return eventDateAndTime;
	}
	public void setEventDateAndTime(String eventDateAndTime) {
		try {
			this.eventDateAndTime_obj=defaultDateFormater.parse(eventDateAndTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.eventDateAndTime = eventDateAndTime;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public MultipartFile getFaqFile() {
		return faqFile;
	}
	public void setFaqFile(MultipartFile faqFile) {
		this.faqFile = faqFile;
	}
	public Date getStartDate_obj() {
		
		return startDate_obj;
	}
	public void setStartDate_obj(Date startDate_obj) {
		this.startDate = defaultDateFormater.format(startDate_obj);
		this.startDate_obj = startDate_obj;
	}
	public Date getRegDate_obj() {
		return regDate_obj;
	}
	
	public void setRegDate_obj(Date regDate_obj) {
		this.regDate = defaultDateFormater.format(regDate_obj);
		this.regDate_obj = regDate_obj;
	}
	
	public Date getEventDateAndTime_obj() {
		return eventDateAndTime_obj;
	}
	public void setEventDateAndTime_obj(Date eventDateAndTime_obj) {
		this.eventDateAndTime=defaultDateFormater.format(eventDateAndTime_obj);
		this.eventDateAndTime_obj = eventDateAndTime_obj;
	}
	public Event toEvent(){
		Event result = new Event();
		result.setUserID(this.getUserID());
		result.setDescription(this.getDescription());
		result.setLocation(this.getLocation());
		result.setTitle(this.getTitle());
		result.setStartDate(this.getStartDate_obj());
		result.setRegDate(this.getRegDate_obj());
		result.setOrganizerName(this.getOrganizerName());
		result.setOrganizerDescription(this.getOrganizerDescription());
		result.setEventDateAndTime(this.getEventDateAndTime_obj());
		result.setSubject(this.getSubject());
		result.setStatus(this.getStatus());
		return result;
	}
	
	public static EventDao fromEvent(Event event){
	   EventDao dao= new EventDao();
	   dao.setUserID(event.getUserID());
	   dao.setDescription(event.getDescription());
	   dao.setLocation(event.getDescription());
	   dao.setTitle(event.getTitle());
	   dao.setStartDate_obj(event.getStartDate());
	   dao.setRegDate_obj(event.getRegDate());
	   dao.setOrganizerName(event.getOrganizerName());
	   dao.setOrganizerDescription(event.getOrganizerDescription());
	   dao.setEventDateAndTime_obj(event.getEventDateAndTime());
	   dao.setSubject(event.getSubject());
	   dao.setStatus(event.getStatus());
	   return dao;
	}
	
	
}
