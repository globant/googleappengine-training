package com.globant.gaetraining.addsincgae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.CustomerDao;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.google.appengine.api.datastore.Key;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> getCustomers() {
		
		return this.customerDao.findAll(Customer.class);
	}
	
	public Customer getCustomer(long customerId) {
						
		return null; //TODO
	}
	
	public void addCustomer(Customer customer) {
		customerDao.persist(customer);
	}
}
