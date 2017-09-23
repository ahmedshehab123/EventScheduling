package com.event.dao;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class UserDao {
	@NotEmpty(message="Please input your First name")
	private String firstName;
	@NotEmpty(message="Please input your Last Name")
	private String lastName;
	@NotEmpty(message="Please input your email")
	@Email(message="Please input valid email")
	private String email;
	@Min(value=8)
	@NotEmpty(message="Please input your Password")
	private String password;
	// personal data
	private String workPhone;
	private String homePhone;
	private String workAddress;
	private String homeAddress;
	private boolean businessUser;
	MultipartFile userImage;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public boolean isBusinessUser() {
		return businessUser;
	}
	public void setBusinessUser(boolean businessUser) {
		this.businessUser = businessUser;
	}
	public MultipartFile getUserImage() {
		return userImage;
	}
	public void setUserImage(MultipartFile userImage) {
		this.userImage = userImage;
	}
	

}
