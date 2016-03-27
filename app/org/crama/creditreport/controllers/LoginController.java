package org.crama.creditreport.controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.crama.creditreport.form.LoginForm;
import org.crama.creditreport.services.UserService;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

@Singleton
public class LoginController extends Controller {
	
	private final UserService userService;
	private final FormFactory formFactory;

    @Inject
    public LoginController(UserService userService, FormFactory formFactory) {
       this.userService = userService;
       this.formFactory = formFactory;	
    }
	    
    public Result loginPage() {
    	Form<LoginForm> loginForm = formFactory.form(LoginForm.class);
        return ok(views.html.login.render(loginForm));
    }    
	
    public Result login() {
    
    	Form<LoginForm> loginForm = formFactory.form(LoginForm.class).bindFromRequest();
    	
    	if (loginForm.hasErrors()) {
    		return badRequest(views.html.login.render(loginForm));
    	} else {
    		session().clear();
    		
	        session("email", loginForm.get().getEmail());
	        return redirect(
	            routes.UserController.userPage()
	        );
    		
    	}
    }
    
    public Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.LoginController.login()
        );
    }
}
