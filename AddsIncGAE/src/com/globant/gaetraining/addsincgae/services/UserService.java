package com.globant.gaetraining.addsincgae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.gaetraining.addsincgae.daos.UserDao;
import com.globant.gaetraining.addsincgae.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public List<User> getUsers() {
		return userDao.findAll(User.class);
	}
}
