package org.crama.creditreport.controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.crama.creditreport.form.LoginForm;
import org.crama.creditreport.models.User;
import org.crama.creditreport.services.UserService;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

@Singleton
public class SignUpController extends Controller {

    private final UserService userService;
    private final FormFactory formFactory;

    @Inject
    public SignUpController(UserService userService, FormFactory formFactory) {
       this.userService = userService;
       this.formFactory = formFactory;	
    }

    public Result signUpPage() {
    	Form<User> userForm = formFactory.form(User.class);
    	return ok(views.html.signup.render(userForm));
    }
    
    public Result signUp() {
    	
    	Form<User> userForm = formFactory.form(User.class).bindFromRequest();
    	
    	if (userForm.hasErrors()) {
    		return badRequest(views.html.signup.render(userForm));
    	} else {
    		User user = userForm.get();
    		userService.saveUser(user);
    		flash("success", "User was registered successfully");
    		return redirect(routes.HomeController.index());
    	}
       
    }

}
