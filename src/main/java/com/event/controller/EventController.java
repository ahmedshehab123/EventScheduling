package com.event.controller;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.event.dao.EventDao;
import com.event.model.AcceptedSponsors;
import com.event.model.Event;
import com.event.model.Images;
import com.event.model.JudgeSubscribers;
import com.event.model.Judges;
import com.event.model.Sponsors;
import com.event.model.Subscribers;
import com.event.model.User;
import com.event.services.EventServices;
import com.event.services.JudgeServices;
import com.event.services.ScoreServices;
import com.event.services.SponsorServices;
import com.event.services.SubscriberServices;

@Controller
public class EventController {
	@Autowired
	EventServices eventService;
	@Autowired
	JudgeServices judgeServices;
	@Autowired
	ScoreServices scoreServices;
	@Autowired
	SubscriberServices subscriberServices;
	@Autowired 
	SponsorServices sponsorServices;
	@RequestMapping(value = "/createEvent", method = RequestMethod.POST)
	public String createNewEvent(@Valid @ModelAttribute("event") EventDao eventDao,BindingResult result, HttpServletRequest request)
			throws ParseException {

		if (result.hasErrors()) {
			return "createevent";
		}else{
		request.getSession().getAttribute("userlogin");

		eventService.createEvent(eventDao.toEvent(), eventDao.getImage(),eventDao.getFaqFile());
		

		return "createevent";
		}
	}
	@RequestMapping(value="/updateimage",method=RequestMethod.POST)
	public String updateEventImage(@RequestParam String eventID,EventDao eventDao){
		eventService.updateEventPhoto(eventDao.getImage(), eventID);
		return "redirect:displayevents";
	}
	@RequestMapping(value="/updatefaq",method=RequestMethod.POST)
	public String updateFaqFile(@RequestParam String eventID,EventDao eventDao){
		eventService.updateFaqFile(eventDao.getFaqFile(), eventID);
		return "redirect:displayevents";
	}
	@RequestMapping(value = "/redirectToCreateEventPage", method = RequestMethod.GET)
	public String redirectToCreateEventPage( HttpServletRequest request)
			throws ParseException {

		User user=(User)request.getSession().getAttribute("userlogin");
		List<Judges> judges=judgeServices.sendJudgeNotification(user.getEmail());
        request.setAttribute("judges", judges);

		return "createevent";

	}
	

	@RequestMapping(value = "/displayimage", method = RequestMethod.GET)
	public void getImage(@RequestParam String eventId, HttpServletRequest request, HttpServletResponse response) {

		try {

			byte[] imagenEnBytes = eventService.getEventBackgroundImage(eventId);

			response.setHeader("Accept-ranges", "bytes");
			response.setContentType("image/jpeg");
			response.setContentLength(imagenEnBytes.length);
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Content-Description", "File Transfer");
			response.setHeader("Content-Transfer-Encoding:", "binary");

			OutputStream out = response.getOutputStream();
			out.write(imagenEnBytes);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block

		}
	}
	@RequestMapping(value = "/displayfaq", method = RequestMethod.GET)
	public void getFAQ(@RequestParam String eventId, HttpServletRequest request, HttpServletResponse response) {

		try {

			byte[] imagenEnBytes = eventService.getFAQ(eventId);

			response.setHeader("Accept-ranges", "bytes");
			response.setContentType("application/pdf");
			response.setContentLength(imagenEnBytes.length);
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Content-Description", "File Transfer");
			response.setHeader("Content-Transfer-Encoding:", "binary");

			OutputStream out = response.getOutputStream();
			out.write(imagenEnBytes);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block

		}
	}
	@RequestMapping(value = "/displayevents", method = RequestMethod.GET)
	public String getname(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userlogin");
		String userID = user.getId();
		List<Event> events = eventService.getUserEvents(userID);
		request.setAttribute("events", events);

		return "reviewyourevents";

	}
	@RequestMapping(value="/geteventInfo",method=RequestMethod.GET)
	public String getEventInfo(@RequestParam String eventID,HttpServletRequest request,HttpServletResponse response){
		List<Event> events=eventService.getEventInfoTolandingPAge(eventID);
 		request.setAttribute("events", events);
 		User user=(User)request.getSession(true).getAttribute("userlogin");
 		if(user !=null){
 		List<Subscribers> userSubscribe=subscriberServices.getsubInfo(eventID, user.getEmail());
        request.setAttribute("userSubscribe", userSubscribe);
        List<JudgeSubscribers> subscribers = judgeServices.getJudgeSubscriber(eventID, user.getEmail());
		request.setAttribute("subscribers", subscribers);
		List<Sponsors> checkSponsor=sponsorServices.checkSponsorlist(eventID, user.getEmail());
    	request.setAttribute("checkSponsor", checkSponsor);
    	List<AcceptedSponsors> acceptedSponsor=sponsorServices.checkAcceptedSponosorOffer(eventID, user.getEmail());
    	request.setAttribute("acceptedSponsor", acceptedSponsor);
    	List<Images> images=sponsorServices.getSponsorImage(user.getId());
    	request.setAttribute("images", images);
 		}
		return "eventinformation";
	}
	@RequestMapping(value="/searchevent",method=RequestMethod.POST)
	public String searchEvent(@RequestParam String title,@RequestParam String location,
			@RequestParam String date,HttpServletRequest request){
		List<Event> events=eventService.searchEvent(title, location, date);
		request.setAttribute("events", events);
		return "searchresult";
	}
	@RequestMapping(value="/searchevent/ajax",method=RequestMethod.POST)
	public String searchByTitle(@RequestParam String title,@RequestParam String location,
			@RequestParam String date,HttpServletRequest request){
		List<Event> events=eventService.searchEvent(title, location, date);
		request.setAttribute("events", events);
		/*if(title.equals(null)&&location.equals(null)&&date.equals(null)){
			
			return "landingPage";
		}else{}*/
		return "ajaxsearch";
	}
	@RequestMapping(value="/deleteEvent",method=RequestMethod.POST)
	public String delete(@RequestParam String eventID){
		eventService.deleteEvent(eventID);
		return "redirect:displayevents";
		
	}
}
