package com.globant.gaetraining.addsincgae.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.Customer;
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

	@RequestMapping(value="/users", method = RequestMethod.POST)
	public String adddUserSubmit(@ModelAttribute("user") User user, ModelMap model){

		userService.addUser(user);
		
		return "redirect:/users";
	}
	
	
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, produces = "text/html")
	public String editUser(@PathVariable Long userId, Model model) {
	
		User user = userService.getUser(userId);
		
		model.addAttribute("user", user);
		
		return "EditUser";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String addUser(Map<String, Object> model) {
		
		User user = new User();
		
		List<String> roles = new ArrayList<>();

		roles.add("admin");
		roles.add("representative");
		roles.add("customer");

		user.setRoles(roles);
		
		model.put("user", user);
		
		return "AdUser";
	}
}
