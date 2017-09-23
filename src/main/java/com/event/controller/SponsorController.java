package com.event.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.dao.ImagesDao;
import com.event.model.AcceptedSponsors;
import com.event.model.Event;
import com.event.model.Images;
import com.event.model.Sponsors;
import com.event.model.User;
import com.event.services.SponsorServices;

@Controller
public class SponsorController {
	@Autowired
	SponsorServices sponsorServices;
    @RequestMapping(value="/sendsponsorOffernotify",method=RequestMethod.GET)
    public String sendSponsorOffer(HttpServletRequest request){
    	User user=(User)request.getSession().getAttribute("userlogin"); 
    	List<Sponsors> sponsors= sponsorServices.sendSponsorOffer( user.getEmail());
    	request.setAttribute("sponsors", sponsors);
    	
    	return "sponsorofferslist";
    }
    @RequestMapping(value="/displaysponsornotify",method=RequestMethod.GET)
    public String diplaySponsorOffer(@RequestParam String eventID, HttpServletRequest request){
    	User user=(User)request.getSession().getAttribute("userlogin"); 
        if(user!=null){
        	List<Sponsors> checkSponsor=sponsorServices.checkSponsorlist(eventID, user.getEmail());
        	request.setAttribute("checkSponsor", checkSponsor);
        	List<AcceptedSponsors> acceptedSponsor=sponsorServices.checkAcceptedSponosorOffer(eventID, user.getEmail());
        	request.setAttribute("acceptedSponsor", acceptedSponsor);
        	List<Images> images=sponsorServices.getSponsorImage(user.getId());
        	request.setAttribute("images", images);
        }
    	List<Event> events=	sponsorServices.sponsorEvent(eventID);
    	request.setAttribute("events", events);
    	return "checkSponsorOffer";
    }
   
    @RequestMapping(value="/acceptoffer",method=RequestMethod.POST)
    public String acceptSponsorOffer(@ModelAttribute("acceptedsponsors") AcceptedSponsors sponsor, @RequestParam String eventID){
    	sponsorServices.saveAcceptedSponsor(sponsor);
    	return "redirect:displaysponsornotify?eventID="+eventID+"";
    }
    @RequestMapping(value="/rejectOffer",method=RequestMethod.POST)
    public String rejectSponsorOffer(@ModelAttribute("acceptedsponsors") AcceptedSponsors sponsor,
    		HttpServletRequest request,@RequestParam String eventID){
    	User user=(User)request.getSession().getAttribute("userlogin"); 

    	sponsorServices.removeSponsor(eventID, user.getEmail());
    	return "redirect:displaysponsornotify?eventID="+eventID+"";
    }
    @RequestMapping(value="/sendSponsorEmail",method= RequestMethod.POST )
	public String sendSponsorEmail(@ModelAttribute("sponsors") Sponsors sponsor,@RequestParam String sponsorEmail,
			@RequestParam String ownerEmail,
			@RequestParam String eventID,HttpServletRequest request){
		List<Sponsors> check=sponsorServices.checkSponsorlist(eventID, sponsorEmail);
		if(check.isEmpty()){
		sponsorServices.sendSponsorEmail(sponsorEmail, ownerEmail, eventID);
		sponsorServices.saveSponsorinfo(sponsor);
		}else{
			
		}
		
		return "redirect:displayevents";
		
	}
    @RequestMapping(value="/contactowner",method=RequestMethod.POST)
    public String contactOwner(@RequestParam String eventID,@RequestParam String userMessage,HttpServletRequest request){
    	User user=(User)request.getSession().getAttribute("userlogin");
    	List<Sponsors> ownerEmail=sponsorServices.getOwnerEmail(eventID);
    	for(Sponsors sponsor:ownerEmail){
    		String email=sponsor.getOwnerEmail();
    		sponsorServices.contctEventOwner( email,user.getEmail(), eventID, userMessage);
    		System.out.println(sponsor.getOwnerEmail());
    	}
    	return "redirect:displaysponsornotify?eventID="+eventID+"";
    }
    @RequestMapping(value="/savesponsorimage",method=RequestMethod.POST)
    public String saveImage(@ModelAttribute("images")ImagesDao image,  @RequestParam String eventID,
    		HttpServletRequest request){
    	User user=(User)request.getSession().getAttribute("userlogin");
    	Images imageOBJ=new Images();
    	imageOBJ=sponsorServices.saveSponsorImage(image.toImage(), image.getImage());
    	
    	  String id=imageOBJ.getImageID();
    	List<Sponsors> ownerEmail=sponsorServices.getOwnerEmail(eventID);
    	for(Sponsors sponsor:ownerEmail){
    		String email=sponsor.getOwnerEmail();
    		sponsorServices.sendEventOwnerImages( email,user.getEmail(), eventID, id);
    	}
    	return "redirect:displaysponsornotify?eventID="+eventID+"";
    }
    @RequestMapping(value="/displaysponsorimage",method=RequestMethod.GET)
    public void playImage(@RequestParam String imageID, HttpServletRequest request, HttpServletResponse response){
    	try {

			byte[] imagenEnBytes = sponsorServices.displaySponsorImages(imageID);

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
