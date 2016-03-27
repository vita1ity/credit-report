package org.crama.creditreport.services;

import org.crama.creditreport.models.User;

public interface UserService {

	public void saveUser(User user);
	
	public User getUser(String email);
	
}
