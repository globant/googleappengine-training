package com.globant.gaetraining.addsincgae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.UserDao;
import com.globant.gaetraining.addsincgae.model.User;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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

	public User getUser(Long userId) {
		
		Key key = KeyFactory.createKey(User.class.getSimpleName(), userId);
		return userDao.findByKey(key, User.class);
	}
}
