package com.event.dao;

import org.springframework.web.multipart.MultipartFile;

import com.event.model.Images;

public class ImagesDao {
	private String imageID;
	private String userID;
	MultipartFile image;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public String getImageID() {
		return imageID;
	}
	public void setImageID(String imageID) {
		this.imageID = imageID;
	}
	public Images toImage(){
		Images image=new Images();
		image.setImageID(this.getImageID());
		image.setUserID(this.getUserID());
		return image;
	}
	public  ImagesDao fromImage(Images image){
		ImagesDao dao=new ImagesDao();
		dao.setImageID(image.getImageID());
		dao.getImageID();
		dao.setUserID(image.getUserID());
		return dao;
	}
  public String getID(){
	  
	  return this.getImageID();
  }
}
