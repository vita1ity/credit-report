package org.crama.creditreport.controllers;

import javax.inject.*;

import org.crama.creditreport.services.Counter;

import play.*;
import play.mvc.*;

/**
 * This controller demonstrates how to use dependency injection to
 * bind a component into a controller class. The class contains an
 * action that shows an incrementing count to users. The {@link Counter}
 * object is injected by the Guice dependency injection system.
 */
@Singleton
public class SignUpController extends Controller {

    private final Counter counter;

    @Inject
    public SignUpController(Counter counter) {
       this.counter = counter;
    }

    /**
     * An action that responds with the {@link Counter}'s current
     * count. The result is plain text. This action is mapped to
     * <code>GET</code> requests with a path of <code>/count</code>
     * requests by an entry in the <code>routes</code> config file.
     */
    public Result signUpPage() {
    	System.out.println("signup");
        return ok(Integer.toString(counter.nextCount()));
    }
    
    

}
