package com.springboot.practice.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.practice.user.dto.User;

@Component
public class UserManageService {

	private static List<User> users = new ArrayList<>();
	private static Integer usrId = 3;

	static {
		users.add(new User(1, "Bikram", new Date()));
		users.add(new User(2, "Anushka", new Date()));
		users.add(new User(3, "Arnit", new Date()));
	}

	// Get one user
	public User getUserById(Integer id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

	// Get all users
	public List<User> getAllUser() {
		return users;
	}
	
	// Add one user
	public User saveUser(User user) {
		if(user.getId() == null) {
			++usrId;
			user.setId(usrId);
		}
		users.add(user);
		return user;
	}
	
	//Delete a user
	public User deleteUser(Integer id) {
		Iterator<User> userItr = users.iterator();
		while(userItr.hasNext()) {
			User user = userItr.next();
			if(user.getId() == id) {
				userItr.remove();
				return user;
			}
		}
		return null;
	}

}
