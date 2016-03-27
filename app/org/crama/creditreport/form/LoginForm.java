package org.crama.creditreport.form;

import java.util.ArrayList;
import java.util.List;

import org.crama.creditreport.models.User;

import com.avaje.ebean.Model;

import play.data.validation.ValidationError;

public class LoginForm extends Model {
	
	private String email;
	
    private String password;
    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public List<ValidationError> validate() {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		User userByEmail = User.findByEmail(this.email);
		System.out.println(userByEmail);
		if (userByEmail == null) {
			errors.add(new ValidationError("email", "Invalid email"));
		}
		else if (!User.checkPassword(this.password, userByEmail.getPassword())) {
			errors.add(new ValidationError("password", "Invalid password"));
			
		}
		if (errors.size() != 0) {
			return errors;
		}
		else {
			return null;
		}
	}
    
	
	
}
