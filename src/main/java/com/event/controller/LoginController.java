package com.event.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.event.model.Judges;
import com.event.model.User;
import com.event.services.EventServices;
import com.event.services.JudgeServices;
import com.event.services.ScoreServices;
import com.event.services.UserServices;

@Controller
public class LoginController {
	@Autowired
	UserServices userService;
	@Autowired
	JudgeServices judgeServices;
	@Autowired
	EventServices eventService;
	@Autowired
	ScoreServices scoreServices;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam(required=false) String email,@RequestParam(required=false) String password, ModelMap model,
			HttpServletRequest request) {
		User user = userService.login(email, password);
		if(user == null){
			
			model.addAttribute("usernameError", "User Name errro");
			model.addAttribute("passwordError", "Password errro");
			model.addAttribute("user", new User());
			return "index";
			
			
		}else{
		request.getSession(true).setAttribute("userlogin", user);
		System.out.println("user log in successfully");
		List<Judges> judges=judgeServices.sendJudgeNotification(user.getEmail());
        request.setAttribute("judges", judges);
		return "redirect:/";
		}
	}
	@RequestMapping(value = "/loginpage", method = RequestMethod.GET)
	public String loginPage() {
		
			return "login";
	}
	@RequestMapping(value = "/subscribelogin", method = RequestMethod.POST)
	public String loginUserSubscribe(@RequestParam(required=false) String email,@RequestParam(required=false) String password,
			@RequestParam String eventID,ModelMap model,
			HttpServletRequest request) {
		User user = userService.login(email, password);
		if(user == null){
			
			model.addAttribute("usernameError", "User Name errro");
			model.addAttribute("passwordError", "Password errro");
			model.addAttribute("user", new User());
			return "index";
			
			
		}else{
		request.getSession(true).setAttribute("userlogin", user);
		System.out.println("user log in successfully");
		return "redirect:geteventInfo?eventID="+eventID+"";
		}
	}
	
	@RequestMapping(value = "/subscriberegister", method = RequestMethod.POST)
	public String registerNewUser(@Valid @ModelAttribute("user") User user,BindingResult result,
			@RequestParam String eventID,ModelMap model, HttpServletRequest request) {
		

			if (result.hasErrors()){
				return "index";
			}else{
				if(user.isBusinessUser()==true){
					
					return "businesspaid";
				}else{
				userService.createUser(user);
				 request.getSession().setAttribute("userlogin", user);
                List<Judges> judges=judgeServices.sendJudgeNotification(user.getEmail());
                request.setAttribute("judges", judges);
				return "redirect:geteventInfo?eventID="+eventID+"";
				}
			}
			}
	@RequestMapping(value = "/judgelogin", method = RequestMethod.POST)
	public String loginJudge(@RequestParam(required=false) String email,@RequestParam(required=false) String password,
			@RequestParam String eventID,ModelMap model,
			HttpServletRequest request) {
		User user = userService.login(email, password);
		if(user == null){
			
			model.addAttribute("usernameError", "User Name errro");
			model.addAttribute("passwordError", "Password errro");
			model.addAttribute("user", new User());
			return "index";
			
			
		}else{
		request.getSession(true).setAttribute("userlogin", user);
		System.out.println("user log in successfully");
		
		return "redirect:checkjudgeinvitation?eventID="+eventID+"";
		}
	}
	@RequestMapping(value = "/judgeregister", method = RequestMethod.POST)
	public String registerJudge(@Valid @ModelAttribute("user") User user,BindingResult result,
			@RequestParam String eventID,ModelMap model, HttpServletRequest request) {
		

			if (result.hasErrors()){
				return "index";
			}else{
				if(user.isBusinessUser()==true){
					
					return "businesspaid";
				}else{
				userService.createUser(user);
				 request.getSession().setAttribute("userlogin", user);
               
				return "redirect:checkjudgeinvitation?eventID="+eventID+"";
				}
			}
			}
	@RequestMapping(value = "/sponsorlogin", method = RequestMethod.POST)
	public String loginSponsor(@RequestParam(required=false) String email,@RequestParam(required=false) String password,
			@RequestParam String eventID,ModelMap model,
			HttpServletRequest request) {
		User user = userService.login(email, password);
		if(user == null){
			
			model.addAttribute("usernameError", "User Name errro");
			model.addAttribute("passwordError", "Password errro");
			model.addAttribute("user", new User());
			return "index";
			
			
		}else{
		request.getSession(true).setAttribute("userlogin", user);
		System.out.println("user log in successfully");
		
		return "redirect:displaysponsornotify?eventID="+eventID+"";
		}
	}
	@RequestMapping(value = "/sponsoregister", method = RequestMethod.POST)
	public String registerSponsor(@Valid @ModelAttribute("user") User user,BindingResult result,
			@RequestParam String eventID,ModelMap model, HttpServletRequest request) {
		

			if (result.hasErrors()){
				return "index";
			}else{
				if(user.isBusinessUser()==true){
					
					return "businesspaid";
				}else{
				userService.createUser(user);
				 request.getSession().setAttribute("userlogin", user);
               
				return "redirect:displaysponsornotify?eventID="+eventID+"";
				}
			}
			}
	@RequestMapping(value="/facebooklogin",method=RequestMethod.GET)
	public String facebookLogin(){
		
		
		return "redirect:/";
	}
}
