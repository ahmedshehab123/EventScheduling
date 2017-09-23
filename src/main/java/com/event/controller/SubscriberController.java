package com.event.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.model.Subscribers;
import com.event.model.User;
import com.event.services.SubscriberServices;

@Controller
public class SubscriberController {
	@Autowired
	SubscriberServices subscriberServices;
	@RequestMapping(value="/getsubscribe",method=RequestMethod.POST)
	public String getsubscribe(@ModelAttribute("subscribers") Subscribers subscriber,@RequestParam String eventID){
		subscriberServices.getSubscription(subscriber);
		
		
		return "redirect:geteventInfo?eventID="+eventID+""; 
	}
	@RequestMapping(value="/removesubscribe",method=RequestMethod.POST)
	public String remove(@ModelAttribute("subscribers") Subscribers subscriber,
			@RequestParam String eventID,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("userlogin");
		subscriberServices.removesubscriber(eventID, user.getEmail());
		
		
		return "redirect:geteventInfo?eventID="+eventID+""; 
	}
}
