package com.event.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.event.model.User;

@Controller
public class LogoutController {
     @RequestMapping(value="/logout",method=RequestMethod.GET)
     public String logout(@ModelAttribute("user") User user,HttpSession session){
    	 session.invalidate();
        
    	 
    	 return "redirect:/";
     }
}
