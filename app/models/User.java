/**
 * 
 */
package models;

import java.io.Serializable;
import java.util.List;

import controllers.couch.CBQuery;
import controllers.couch.CouchOps;

/**
 * @author valore
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String id;
	public String type = "user";
	
	private static CouchOps<User> bucket = new CouchOps<User>(User.class);
	
	public static void save(User u) {
		bucket.save(u.id, u);
	}
	
	public static List<User> list() {
		return bucket.findAll("dev_user", "by_list", CBQuery.get(), User.class);
	}

}
