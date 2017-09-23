package com.event.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.event.model.User;
import com.mongodb.gridfs.GridFSDBFile;
import net.authorize.Environment;
import net.authorize.api.contract.v1.*;
import net.authorize.api.controller.base.ApiOperationBase;
import net.authorize.api.controller.CreateTransactionController;
@Service
public class UserServices {
	
	@Autowired
	MongoOperations mongoTemplate;
	@Autowired
	GridFsTemplate gridFsTemplate;

	public User createUser(User user) {

		mongoTemplate.insert(user);
		return user;
	}
	public User login(String email, String password){
		//BasicDBObject dbObject = new  BasicDBObject("email", user.getEmail()).append("password", user.getPassword());
		//BasicQuery query= new BasicQuery(dbObject);
		BasicQuery query = new BasicQuery("{email: '"+email+"', password: '"+password+"' }");
		User user = mongoTemplate.findOne(query, User.class);
		
		return user;
	}
	public boolean checkExistEMail(String email){
		BasicQuery query = new BasicQuery("{email: '"+email+"'}");
		User user = mongoTemplate.findOne(query, User.class);
		if(user==null){
			return true;
		}else{
			return false;
		}
	}
	public List<User> userSetting(String userID){
		BasicQuery query=new BasicQuery("{id:'"+userID+"'}");
		List<User> user=mongoTemplate.find(query, User.class);
		return user;
		
	}
	
	public void updataUserSetting(String userID,String firstName,String lastName,String email,String workPhone
			,String homePhone,String workAddress,String homeAddress){
		BasicQuery selectQuery=new BasicQuery("{id:'"+userID+"'}");
		Update updateQuery = new Update();
		updateQuery.set("firstName", firstName);
		updateQuery.set("lastName", lastName);
		updateQuery.set("email", email);
		updateQuery.set("workPhone", workPhone);
		updateQuery.set("homePhone", homePhone);
		updateQuery.set("workAddress", workAddress);
		updateQuery.set("homeAddress", homeAddress);
		mongoTemplate.updateMulti(selectQuery, updateQuery, User.class);
	}
	
	public User facebookLogIn(String email){
		BasicQuery query = new BasicQuery("{email: '"+email+"'}");
		User user = mongoTemplate.findOne(query, User.class);
		
		return user;
	}
	public User facebookSignup(String firstName,String lastName,String email,String password){
		User user=new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		mongoTemplate.save(user);
		return user;
	}
	public void updateUserImage(MultipartFile image, String userID) {
		try {GridFSDBFile imageDbFile = gridFsTemplate
				.findOne(new Query().addCriteria(Criteria.where("filename").is(userID)));
		
		
			if(imageDbFile==null){
				gridFsTemplate.store(image.getInputStream(), userID);
			}else{
				
			gridFsTemplate.delete(new BasicQuery(imageDbFile));
			gridFsTemplate.store(image.getInputStream(), userID);}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] dipalyUserImage(String userID) {

		GridFSDBFile imageDbFile = gridFsTemplate
				.findOne(new Query().addCriteria(Criteria.where("filename").is(userID)));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		if (imageDbFile != null) {
			try {
				imageDbFile.writeTo(os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return os.toByteArray();

	}
	public boolean paidForRegister(String cardNumber) {

        //Common code to set for all requests
		ApiOperationBase.setEnvironment(Environment.SANDBOX);

        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType() ;
        merchantAuthenticationType.setName("4ZS9p7qLPm");
        merchantAuthenticationType.setTransactionKey("5Uf6RCj58Y58T7w8");
        ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

        // Populate the payment data
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(cardNumber);
        creditCard.setExpirationDate("0822");
        paymentType.setCreditCard(creditCard);

        // Create the payment transaction request
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setPayment(paymentType);
        txnRequest.setAmount(new BigDecimal(500.00));

        // Make the API Request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setTransactionRequest(txnRequest);
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();


        CreateTransactionResponse response = controller.getApiResponse();

        if (response!=null) {

            // If API Response is ok, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {

                TransactionResponse result = response.getTransactionResponse();
                if (result.getResponseCode().equals("1")) {
                    System.out.println(result.getResponseCode());
                    System.out.println("Successful Credit Card Transaction");
                    System.out.println(result.getAuthCode());
                    System.out.println(result.getTransId());
                    return true;
                }
                else
                {
                    System.out.println("Failed Transaction"+result.getResponseCode());
                    return false;
                }
            }
            else
            {
                System.out.println("Failed Transaction:  "+response.getMessages().getResultCode());
                return false;
            }
        }else{
        	return false;
        }

    }

}