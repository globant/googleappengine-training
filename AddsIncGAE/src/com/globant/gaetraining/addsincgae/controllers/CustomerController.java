package com.globant.gaetraining.addsincgae.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globant.gaetraining.addsincgae.model.Customer;
import com.globant.gaetraining.addsincgae.services.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

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
	
	@RequestMapping(value="/customers", method = RequestMethod.POST)
	public String addCustomer(HttpServletRequest request, ModelMap model){
		String name = request.getParameter("name");
		Customer customer = new Customer();
		customer.setName(name);
		customer.setOwners(null);
		customer.setRepresentative(null);
		customerService.addCustomer(customer);
		
		return "AddCustomer";
	}
	
	@RequestMapping(value="/addCustomer", method = RequestMethod.GET)
	public String add(){
		return "AddCustomer";
	}
}