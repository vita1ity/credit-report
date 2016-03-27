package org.crama.creditreport.services;

import javax.inject.Singleton;

import org.crama.creditreport.models.User;

@Singleton
public class UserServiceImpl implements UserService {

	@Override
	public void saveUser(User user) {
		user.hashPassword();
		user.save();
		
	}


	@Override
	public User getUser(String email) {
		User user = User.findByEmail(email);
		return user;
	}

}
