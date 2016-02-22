package controllers.util;

import com.google.gson.Gson;

/**
 * @author aurum
 * @since 06-04-2015
 *
 */
public class GsonProvider {
	
	public static final Gson gson = new Gson();
	
	private GsonProvider() {}
	
	public static Gson getInstance() {
		return gson;
	}

}
