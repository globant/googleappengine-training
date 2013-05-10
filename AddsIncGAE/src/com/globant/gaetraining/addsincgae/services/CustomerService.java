package com.globant.gaetraining.addsincgae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.CustomerDao;
import com.globant.gaetraining.addsincgae.model.Customer;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> getCustomers() {
		
		return this.customerDao.findAll(Customer.class);
	}
	
	public Customer getCustomer(long customerId) {
	
		Key key = KeyFactory.createKey(Customer.class.getSimpleName(), customerId);
		return customerDao.findByKey(key, Customer.class, null);
	}

	public Customer getCustomer(Key customerKey) {

		return customerDao.findByKey(customerKey, Customer.class, null);
	}

	
	public void addCustomer(Customer customer) {
		customerDao.persist(customer);
	}
	
	public void deleteCustomer(Key keyCustomer){
		customerDao.delete(Customer.class, keyCustomer);
	}
}
