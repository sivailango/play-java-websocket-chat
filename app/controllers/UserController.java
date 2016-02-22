/**
 * 
 */
package controllers;

import java.util.UUID;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author valore
 *
 */
public class UserController extends Controller {
	
	public static Result save() {
		User u = new User();
		u.id = UUID.randomUUID().toString();
		User.save(u);
		return ok();
	}
	
}
