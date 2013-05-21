package com.globant.gaetraining.addsincgae.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.UserDao;
import com.globant.gaetraining.addsincgae.model.User;
import com.google.appengine.api.datastore.Key;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public List<User> getUsers() {
		return userDao.findAll(User.class);
	}

	public void addUser(User user) {
		userDao.persist(user);
	}

	public User getUser(Key userKey) {

				return userDao.findByKey(userKey, User.class, null);
	}

	public void updateUser(Key userKey, User user) {

		user.setKey(userKey);
		userDao.persist(user);
	}
	
	public void deleteUser(Key userKey){
		userDao.delete(User.class,userKey);
	}
	
	public List<String> getUserRoles(String userName){
		return userDao.getUserRoles(userName);
	}
	
	public List<String> getRoles(){
		List<String> roles = new ArrayList<>();
		roles.add("admin");
		roles.add("representative");
		roles.add("customer");
		return roles;
	}
}
