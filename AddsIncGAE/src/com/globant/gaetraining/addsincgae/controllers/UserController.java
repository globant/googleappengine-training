package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.User;
import com.globant.gaetraining.addsincgae.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getCustomers(Map<String, Object> model) {

		List<User> users = userService.getUsers();
		
		model.put("users", users);
		
		return "UserList";
	}

}
