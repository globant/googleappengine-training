package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Customer;

@Repository
public class CustomerDao extends GenericDao<Customer> {

	/**
	 * Find all customers
	 * 
	 * @return {@link List} of all {@link Customer}
	 */
	public List<Customer> findAll() {
		return super.findAll(Customer.class);
	}

}