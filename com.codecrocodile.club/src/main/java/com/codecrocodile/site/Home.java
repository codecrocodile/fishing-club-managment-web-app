package com.codecrocodile.site;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codecrocodile.club.common.UserSession;

@Controller
public class Home {
	
	private static String PAGE_LOCACTION = "home";
	
	@Autowired
	private UserSession userSession;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model uiModel) {
		System.out.println("home page controller");
		
		List<String> messages = new ArrayList<>();
		messages.add("M1");
		messages.add("M2");
		messages.add("M3");
		messages.add("M4");
		
		uiModel.addAttribute("messages", messages);
		uiModel.addAttribute("motd", "Hello and welcome to the first page");
		
		System.out.println(userSession);
		userSession.incrementCallCount();
		System.out.println(userSession.getCallCount());
		
		
		return Home.PAGE_LOCACTION;
	}
}
