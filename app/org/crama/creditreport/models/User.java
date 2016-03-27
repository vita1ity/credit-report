package org.crama.creditreport.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.mindrot.jbcrypt.BCrypt;

import com.avaje.ebean.Model;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;

@Entity
public class User extends Model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID", nullable=false)
	private long id;
	
	@Required
	@Email
	@MinLength(5)
	@MaxLength(255)
	@Column(name="EMAIL", nullable=false, unique=true)
	private String email;
	
	@Required
	@MinLength(5)
	@MaxLength(255)
	@Column(name="NAME", nullable=false, unique=true)
	public String name;
	
	@Required
	@MinLength(5)
	@MaxLength(255)
	@Column(name="PASSWORD", nullable=false)
	private String password;
    
	public User () {
		
	}
	
    public User(String email, String name, String password) {
      this.email = email;
      this.name = name;
      this.password = password;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Finder<Long, User> find = new Finder<Long, User>(User.class);
    
	
	public List<ValidationError> validate() {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		User userByEmail = findByEmail(this.email);
		if (userByEmail != null) {
			errors.add(new ValidationError("email", "User with such email is already registered"));
			return errors;
		}
		
		return null;
	    
	}
	
	public void hashPassword() {
		
		this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
	
	}
	
	public static boolean checkPassword(String candidate, String encryptedPassword) {
		
	    if (candidate == null) {
	        return false;
	    }
	    if (encryptedPassword == null) {
	        return false;
	    }
	    return BCrypt.checkpw(candidate, encryptedPassword);
	    
	}
	
	public static User findByEmail(String email) {
		User user = User.find.where().eq("email", email).findUnique();
		return user;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + "]";
	}
	
	
	
}
