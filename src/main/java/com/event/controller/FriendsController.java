package com.event.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.model.Friends;
import com.event.model.Judges;
import com.event.model.User;
import com.event.services.FriendsServices;
import com.event.services.JudgeServices;

@Controller
public class FriendsController {
	@Autowired
	FriendsServices friendservices;
	@Autowired
	JudgeServices judgeServices;

	@RequestMapping(value = "/saveFriends", method = RequestMethod.POST)
	public String saveFreinds(@ModelAttribute("friends") Friends friends, @RequestParam String userID,
			@RequestParam String friendEmail) {

		friendservices.saveFreinds(friends);
		return "redirect:dipalyFriends";
	}

	@RequestMapping(value = "/dipalyFriends", method = RequestMethod.GET)
	public String dipalyFreinds(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userlogin");
		List<Friends> friends = friendservices.getfriends(user.getId());
		request.setAttribute("friends", friends);
		return "friends";
	}
    @RequestMapping(value="/deleteFriend", method=RequestMethod.POST)
    public String deleteFriend(@RequestParam String friendID){
    	System.out.println("!!!!!"+friendID);
    	friendservices.deleteFriend(friendID);
    	return "redirect:dipalyFriends";
    }
    @RequestMapping(value="/clearlist", method=RequestMethod.POST)
    public String clearList(HttpServletRequest request){
    	User user=(User)request.getSession().getAttribute("userlogin");
    	
    	friendservices.clearList(user.getId());
    	return "redirect:dipalyFriends";
    }
	@RequestMapping(value = "/sendfriendinvitation", method = RequestMethod.POST)
	public String sendFriendsInvitation(@ModelAttribute("judge") Judges judge, HttpServletRequest request,
			@RequestParam String eventID) {
		User user = (User) request.getSession().getAttribute("userlogin");
		List<Friends> friends = friendservices.getfriends(user.getId());
		request.setAttribute("friends", friends);
		for (Friends temp : friends) {
			String toAddress = temp.getFriendEmail();
			List<Judges> checkinvitation = judgeServices.checkJudgeInvitation(eventID, toAddress);
			if (checkinvitation.isEmpty()) {
				judge = friendservices.inviteFriendsList(toAddress, user.getEmail(), eventID);
				judgeServices.saveJudgeInfo(judge);
			}
		}
		return "redirect:displayevents";
	}

}
