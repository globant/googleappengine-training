package com.globant.gaetraining.addsincgae.daos;

import java.util.List;

import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.globant.gaetraining.addsincgae.model.User;

@Repository
public class UserDao extends GenericDao<User>{
	
	public List<String> getUserRoles(String userName){
		Query q = this.getPM().newQuery(User.class);
		try{
			q.setFilter("userName == userNameParam ");
			q.declareParameters("String userNameParam");
			List<User> theUsers =  (List<User>)q.execute(userName);
			return theUsers.get(0).getRoles();
		}finally{
		q.getPersistenceManager().close();
		}
	}

}
