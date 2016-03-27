package org.crama.creditreport.controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.crama.creditreport.models.User;
import org.crama.creditreport.services.UserService;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Singleton
@Security.Authenticated(Secured.class)
public class UserController extends Controller {
	
	private final UserService userService;

    @Inject
    public UserController(UserService userService) {
       this.userService = userService;
    }
    
    
	public Result userPage() {
		
		String email = session().get("email");
		User user = userService.getUser(email);
        return ok(views.html.user.render(user));
    }
	
}
