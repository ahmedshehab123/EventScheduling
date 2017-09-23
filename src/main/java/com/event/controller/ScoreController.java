package com.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.model.Score;
import com.event.services.ScoreServices;

@Controller
public class ScoreController {
    @Autowired
    ScoreServices scoreServices;
	@RequestMapping(value="/savescore",method=RequestMethod.POST)
	public String saveScore(@ModelAttribute("scores") Score score, @RequestParam String eventID){
		scoreServices.saveScore(score);
		return "redirect:checkjudgeinvitation?eventID=" + eventID + "";
	}
	
	
}
