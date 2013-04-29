package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.Customer;

@Repository
public class CustomerDao extends GenericDao<Customer> {

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomers() {
		
		PersistenceManager pm = PMF.getPM();

		Query q = pm.newQuery(Customer.class);

		try {

			return (List<Customer>) q.execute();
		} finally {
			pm.close();
		}
	}

}