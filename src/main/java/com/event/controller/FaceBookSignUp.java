package com.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.event.model.User;
import com.event.services.OAuthServiceProvider;
import com.event.services.UserServices;

@RequestMapping(value = "/social/facebooksignup")
@Component
public class FaceBookSignUp<FacebookApi> {

	
	private static final String FACEBOOK = "facebook";
	@Autowired
	private ConnectionFactoryRegistry connectionFactoryRegistry;

	@Autowired
	@Qualifier("oAuthSignupParameters")
	private OAuth2Parameters oAuth2Parameters;

	

	@Autowired
	@Qualifier("facebookSignUp")
	private OAuthServiceProvider<FacebookApi> facebookServiceProvider;
	@Autowired
	UserServices userServices;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FacebookConnectionFactory facebookConnectionFactory = (FacebookConnectionFactory) connectionFactoryRegistry
				.getConnectionFactory(FACEBOOK);
		OAuth2Operations oauthOperations = facebookConnectionFactory
				.getOAuthOperations();
		oAuth2Parameters.setRedirectUri( "http://localhost:8080/EventScheduling/social/facebooksignup/signupcallback");
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(
				GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		RedirectView redirectView = new RedirectView(authorizeUrl, true, true,
				true);

		return new ModelAndView(redirectView);
	}

	@RequestMapping(value = "/signupcallback", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView postOnWall(@RequestParam("code") String code,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		OAuthService oAuthService = facebookServiceProvider.getService();

		Verifier verifier = new Verifier(code);
		Token accessToken = oAuthService
				.getAccessToken(Token.empty(), verifier);

		FacebookTemplate template = new FacebookTemplate(accessToken.getToken());

		template.userOperations().getUserProfile();

		String userID = template.userOperations().getUserProfile().getId();
		String email=template.userOperations().getUserProfile().getEmail();
		String fname=template.userOperations().getUserProfile().getFirstName();
		String lname=template.userOperations().getUserProfile().getLastName();
		PagedList<String> friendsId=template.friendOperations().getFriendIds();
		for(String id:friendsId){
		System.out.println(friendsId+"/n");}
		/*template.friendOperations().getfriend*/
		RedirectView redirectView = new RedirectView("http://localhost:8080/EventScheduling/");
		
		User user =	userServices.facebookLogIn(email);
        if(user==null){
        	System.out.println("facebook user sign up succesfully");
        	user=userServices.facebookSignup(fname, lname, email,userID);
        	request.getSession().setAttribute("userlogin", user);
        	
        	return new ModelAndView(redirectView);
        }else{
        	System.out.println("facebook userlogin  succesfully");
        	request.getSession().setAttribute("userlogin", user);
        	return new ModelAndView(redirectView);
        	
        }
		

	}

	
}
