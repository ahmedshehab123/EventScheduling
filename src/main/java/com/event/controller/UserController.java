package com.event.controller;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.dao.UserDao;
import com.event.model.Event;
import com.event.model.User;
import com.event.services.EventServices;
import com.event.services.JudgeServices;
import com.event.services.UserServices;

@Controller
public class UserController {

	@Autowired
	UserServices userService;
	@Autowired
	JudgeServices judgeServices;
	@Autowired
	EventServices eventServices;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) throws ParseException {

		model.addAttribute("message", "Welcome to event app");
		model.addAttribute("other", "registeration");
		model.addAttribute("user", new User());
		List<Event> events = eventServices.getUserEventsTolandingPAge();
		request.setAttribute("events", events);

		return "landingPage";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String reg(ModelMap model) {
		model.addAttribute("message", "Welcome to event app");
		model.addAttribute("other", "registeration");
		model.addAttribute("user", new User());
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		if (userService.checkExistEMail(user.getEmail()) == false) {
			model.addAttribute("exist", "user already exist");
			return "index";
		} else {

			if (result.hasErrors()) {
				return "index";
			} else {

				userService.createUser(user);
				request.getSession().setAttribute("userlogin", user);
				/*
				 * List<Judges> judges =
				 * judgeServices.sendJudgeNotification(user.getEmail());
				 * request.setAttribute("judges", judges);
				 */
				return "redirect:/";

			}
		}

	}

	@RequestMapping(value = "/redirecttobusinessregistration", method = RequestMethod.GET)
	public String businessRdirect() {

		return "businesspaid";
	}

	@RequestMapping(value = "/registerBusinessUser", method = RequestMethod.POST)
	public String registerBusinessUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		if (userService.checkExistEMail(user.getEmail()) == false) {
			model.addAttribute("exist", "user already exist");
			return "businesspaid";
		} else {
			if (result.hasErrors()) {

				return "businesspaid";
			} else {
				if (user.getCardnumber().length() < 16) {
                    model.addAttribute("invalidcardnumber", "invalid card number");
					return "businesspaid";
				} else {
					if (userService.paidForRegister(user.getCardnumber()) == true) {
						System.out.println("user paid successfully");
						userService.createUser(user);
						request.getSession().setAttribute("userlogin", user);
						return "redirect:/";
					} else {
						model.addAttribute("paiderror", "paid error");
						System.out.println("user don't paid");
						return "businesspaid";
					}
				}

			}
		}
	}

	@RequestMapping(value = "/accountingSetting", method = RequestMethod.GET)
	public String userSetting(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userlogin");
		List<User> users = userService.userSetting(user.getId());
		request.setAttribute("users", users);
		return "accountingSetting";
	}

	@RequestMapping(value = "/updatesetting", method = RequestMethod.POST)
	public String updateUserSetting(@RequestParam String fname, @RequestParam String lname, @RequestParam String email,
			@RequestParam String workPhone, @RequestParam String homePhone, @RequestParam String workAddress,
			@RequestParam String homeAddress, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userlogin");

		userService.updataUserSetting(user.getId(), fname, lname, email, workPhone, homePhone, workAddress,
				homeAddress);
		return "redirect:accountingSetting";
	}

	@RequestMapping(value = "/updateuserimage", method = RequestMethod.POST)
	public String updateUserImage(UserDao userDao, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("userlogin");
		userService.updateUserImage(userDao.getUserImage(), user.getId());
		return "redirect:accountingSetting";
	}

	@RequestMapping(value = "/displayuserimage", method = RequestMethod.GET)
	public void getUserImage(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("userlogin");
		try {

			byte[] imagenEnBytes = userService.dipalyUserImage(user.getId());

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

}
