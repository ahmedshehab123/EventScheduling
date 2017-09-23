package com.event.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.event.model.AcceptedSponsors;
import com.event.model.Event;
import com.event.model.JudgeSubscribers;
import com.event.model.Judges;
import com.event.model.Score;
import com.event.model.Sponsors;
import com.event.model.User;
import com.event.services.EventServices;
import com.event.services.JudgeServices;
import com.event.services.ScoreServices;
import com.event.services.SponsorServices;

@Controller
public class JudgeController {
	@Autowired
	JudgeServices judgeServices;
	@Autowired
	ScoreServices scoreServices;
	@Autowired
	EventServices eventService;
	@Autowired
	SponsorServices sponsorServices;

	@RequestMapping(value = "/setSubInfo", method = RequestMethod.POST)
	public String setSubScriberIfo(@ModelAttribute("judgeSubscribers") JudgeSubscribers subscriber,
			@RequestParam String eventID, @RequestParam String subscriberEmail, HttpServletRequest request) {
		subscriber = judgeServices.setSubscriber(subscriber);
		System.out.println(subscriber.getEventID());
		System.out.println(subscriber.getSubscriberEmail());
		// System.out.println(subscribers.size());
		return "redirect:checkjudgeinvitation?eventID=" + eventID + "";
	}

	@RequestMapping(value = "/unsubscriber", method = RequestMethod.POST)
	public String removeSubscriber(@RequestParam String eventID, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userlogin");
		scoreServices.removeScore(eventID, user.getEmail());
		judgeServices.unsubscribe(eventID, user.getEmail());
		return "redirect:checkjudgeinvitation?eventID=" + eventID + "";
	}

	@RequestMapping(value = "/checkjudgeinvitation", method = RequestMethod.GET)
	public String getjudgeinvitation(@RequestParam String eventID, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userlogin");
		// System.out.println("sessin email "+user.getEmail());
		List<Event> events = eventService.invitationEvent(eventID);
		request.setAttribute("events", events);
		List<Judges> judges = judgeServices.sendJudgeInfo(eventID);
		request.setAttribute("judges", judges);
		if (user != null) {
			List<Judges> checkJudge = judgeServices.checkJudgeInvitation(eventID, user.getEmail());
			request.setAttribute("checkJudge", checkJudge);
			List<JudgeSubscribers> subscribers = judgeServices.getJudgeSubscriber(eventID, user.getEmail());
			request.setAttribute("subscribers", subscribers);
			List<Score> scores = scoreServices.getScoreValue(eventID, user.getEmail());
			request.setAttribute("scores", scores);
			System.out.println("!!!!!!!!!!!!!!!====" + scores.size());
		}
		return "checkinvitation";
	}

	@RequestMapping(value = "/sendJudgeEmail", method = RequestMethod.POST)
	public String sendJudgeEmail(@ModelAttribute("judge") Judges judge, @RequestParam String toAddress,
			@RequestParam String fromAddress, @RequestParam String eventID, HttpServletRequest request,
			ModelMap model) {
		List<Judges> checkinvitation = judgeServices.checkJudgeInvitation(eventID, toAddress);
		if (checkinvitation.isEmpty()) {
			judgeServices.sendJudgeEmail(toAddress, fromAddress, eventID);
			System.out.println(toAddress);
			System.out.println(fromAddress);
			judgeServices.saveJudgeInfo(judge);
			model.addAttribute("email invited", "email invited successfully");

			return "redirect:displayevents";
		} else {

			model.addAttribute("emailexist", "this is email is already invited to this event before ");
			return "redirect:displayevents";
		}

	}
    @RequestMapping(value="/reviewinvitation",method=RequestMethod.GET)
    public String reviewInvitation(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("userlogin");

    	if (user != null) {
			List<Judges> judges = judgeServices.sendJudgeNotification(user.getEmail());
			request.setAttribute("judges", judges);
		}
    	return "reviewinvitation";
    }
	@RequestMapping(value = "/listjudges", method = RequestMethod.GET)
	public String listJudges(@RequestParam String eventID, HttpServletRequest request) {
		List<Judges> judges = judgeServices.sendJudgeInfo(eventID);
		request.setAttribute("judges", judges);
		List<JudgeSubscribers> judgeSubscriber = judgeServices.displaySubscribersJudges(eventID);
		request.setAttribute("judgeSubscriber", judgeSubscriber);
		List<Sponsors> sponsors = sponsorServices.diplaySponsorList(eventID);
		request.setAttribute("sponsors", sponsors);
		List<AcceptedSponsors> acceptedSponsors = sponsorServices.dipalyAcceptedSponsor(eventID);
		request.setAttribute("acceptedSponsors", acceptedSponsors);
		for (Sponsors temp : sponsors) {

			System.out.println("%%%%%%%%%%" + temp.getSponsorEmail());
		}
		return "eventlists";
	}
}
