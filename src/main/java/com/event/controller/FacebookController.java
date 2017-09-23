package com.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.event.services.FacebookUtil;
import com.event.services.OAuthServiceProvider;

@RequestMapping(value = "/social/facebook")
@Component
public class FacebookController<FacebookApi> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FacebookController.class);
	private static final String FACEBOOK = "facebook";
//	private static final String PUBLISH_SUCCESS = "success";
	private static final String PUBLISH_ERROR = "error";

	@Autowired
	private ConnectionFactoryRegistry connectionFactoryRegistry;

	@Autowired
	@Qualifier("oAuth2Parameters")
	private OAuth2Parameters oAuth2Parameters;

	@Autowired
	FacebookUtil facebookUtil;

	@Autowired
	@Qualifier("facebookShare")
	private OAuthServiceProvider<FacebookApi> facebookServiceProvider;

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView signin(@RequestParam("state") String eventID,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//System.out.println("authorize url"+authorizeUrl);
		FacebookConnectionFactory facebookConnectionFactory = (FacebookConnectionFactory) connectionFactoryRegistry
				.getConnectionFactory(FACEBOOK);
		OAuth2Operations oauthOperations = facebookConnectionFactory
				.getOAuthOperations();
		oAuth2Parameters.setState(eventID);
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(
				GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		RedirectView redirectView = new RedirectView(authorizeUrl, true, true,
				true);
		System.out.println("authorize url"+authorizeUrl);

		return new ModelAndView(redirectView);
	}

	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView postOnWall(@RequestParam("code") String code,
			@RequestParam("state") String state,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		OAuthService oAuthService = facebookServiceProvider.getService();

		Verifier verifier = new Verifier(code);
		Token accessToken = oAuthService
				.getAccessToken(Token.empty(), verifier);

		FacebookTemplate template = new FacebookTemplate(accessToken.getToken());

		template.userOperations().getUserProfile();

		String userId = template.userOperations().getUserProfile().getId();
		String email=template.userOperations().getUserProfile().getEmail();
		String fname=template.userOperations().getUserProfile().getFirstName();
		String lname=template.userOperations().getUserProfile().getLastName();
		System.out.println("id "+userId);
		System.out.println("email "+email);
		System.out.println("firstname "+fname);
		System.out.println("last name"+lname);
		
		

		MultiValueMap<String, Object> map = facebookUtil
				.publishLinkWithVisiblityRestriction(state);
		try {
			template.publish(userId, "feed", map);
		} catch (Exception ex) {
			LOGGER.error(
					"Exception Occurred while posting a link on facebook for user Id : {}, exception is : {}",
					userId, ex);
			RedirectView redirectView = new RedirectView(PUBLISH_ERROR);
			return new ModelAndView(redirectView);
		}

		RedirectView redirectView = new RedirectView("http://localhost:8080/EventScheduling/");

		return new ModelAndView(redirectView);
	}

	@RequestMapping(value = "/callback", params = "error_reason", method = RequestMethod.GET)
	@ResponseBody
	public void error(@RequestParam("error_reason") String errorReason,
			@RequestParam("error") String error,
			@RequestParam("error_description") String description,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			LOGGER.error(
					"Error occurred while validating user, reason is : {}",
					errorReason);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, description);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
