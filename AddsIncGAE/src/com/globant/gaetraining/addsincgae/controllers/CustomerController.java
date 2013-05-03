package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

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
import com.globant.gaetraining.addsincgae.services.CustomerService;
import com.globant.gaetraining.addsincgae.services.UserService;
import com.google.appengine.api.datastore.Key;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String getCustomers(Map<String, Object> model) {

		List<Customer> customers = customerService.getCustomers();
		
		model.put("customers", customers);
		
		return "CustomerList";
	}

	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET, produces = "text/html")
	public String editCustomer(@PathVariable Long customerId, Model model) {
	
		Customer customer = customerService.getCustomer(customerId);
		
		model.addAttribute("customer", customer);
		
		return "EditCustomer";
	}
	
	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.POST)
	public String saveCustomer(@PathVariable Long customerId, @ModelAttribute("customer") Customer customer, Model model) {
	 
		//customer.setId(customerId);
		
		this.customerService.addCustomer(customer);
	  
		model.addAttribute("customer", customer);
	  
		return "EditCustomer";
	}
	
	@RequestMapping(value="/customers", method = RequestMethod.POST)
	public String addCustomer(HttpServletRequest request, ModelMap model){
		String name = request.getParameter("name");
		String legalName = request.getParameter("legalName");
		String description = request.getParameter("description");
		String employeeAmount = request.getParameter("employeesAmount");
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setLegalName(legalName);
		customer.setDescription(description);
		customer.setEmployeesAmount(employeeAmount == null ? 0 : Integer.parseInt(employeeAmount));
		//TODO Find representatives and owners properly 
	    List<Key> users = Lists.transform(userService.getUsers(), new Function<User, Key>() {
	      @Override
	      @Nullable
	      public Key apply(@Nullable User user) {
	        return user.getKey();
	      }
	      
	    });
	    customer.setOwners(users);
	    customer.setRepresentative(users);
customerService.addCustomer(customer);
		
		return "redirect:/customers";
	}
	
	@RequestMapping(value="/addCustomer", method = RequestMethod.GET)
	public String add(){
		return "AddCustomer";
	}
}
