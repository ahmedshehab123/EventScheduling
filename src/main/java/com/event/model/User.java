package com.event.model;



import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	@Id
	private String id;

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
	@Min(value=16)
	@NotEmpty(message="Please input your card number")
	private String cardnumber;
	private boolean businessUser;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	
    
}
