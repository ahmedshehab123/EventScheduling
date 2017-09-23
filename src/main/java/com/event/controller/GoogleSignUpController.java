package com.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.event.services.OAuthServiceProvider;

@RequestMapping(value="social/google")
@Controller
public class GoogleSignUpController<GoogleApi> {
	private static final String PUBLISH_SUCCESS = "success";

	private static final String Google = "google";
	@Autowired
	private ConnectionFactoryRegistry connectionFactoryRegistry;
	@Autowired
	@Qualifier("oAuthGoogleSignupParameters")
	private OAuth2Parameters oAuth2Parameters;
	@Autowired
	@Qualifier("googleSignUp")
	private OAuthServiceProvider<GoogleApi> googleServiceprovide;

	@RequestMapping(value = "/googlesignin", method = RequestMethod.GET)
	public ModelAndView signin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GoogleConnectionFactory googleConnectionFactory=(GoogleConnectionFactory) connectionFactoryRegistry.getConnectionFactory(Google);
		OAuth2Operations oauthOperations =googleConnectionFactory.getOAuthOperations();
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(
				GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		RedirectView redirectView = new RedirectView(authorizeUrl, true, true,
				true);
			System.out.println(authorizeUrl);
			System.out.println("ahme ahmed ahmed ahemd");
		return new ModelAndView(redirectView);
	}
	@RequestMapping(value = "/googlecallback", method = RequestMethod.GET)
	
	public ModelAndView postOnWall(@RequestParam("code") String code,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		OAuthService oAuthService = googleServiceprovide.getService();
      System.out.println("token token token ");
		Verifier verifier = new Verifier(code);
		Token accessToken = oAuthService
				.getAccessToken(Token.empty(), verifier);
		GoogleTemplate googleTemplate = new GoogleTemplate(accessToken.getToken());
		RedirectView redirectView = new RedirectView("http://localhost:8080/EventScheduling/");
         System.out.println("call back work good");		
         return new ModelAndView(redirectView);
	}
	

}
