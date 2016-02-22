package controllers.config;

import play.Configuration;
import play.Play;

/**
 * @author valore
 *
 */
public class AppConfig {
	
	private static final String COUCH_HOST = "couchbase.hostname";
	private static final String COUCH_PORT = "couchbase.port";
	private static final String COUCH_BUCKET = "couchbase.bucket";
	
	private static final String COUCH_USER = "couchbase.user";
	private static final String COUCH_PASSWORD = "couchbase.password";
	
	private static Configuration getPlayConfig() {
		return Play.application().configuration();
	}
	
	public static String get(String name) {
		return getPlayConfig().getString(name);
	}
	
	public static String getCouchHost() {
		return get(COUCH_HOST);
	}
	
	public static String getCouchPort() {
		return get(COUCH_PORT);
	}
	
	public static String getCouchBucket() {
		return get(COUCH_BUCKET);
	}
	
	public static String getCouchUser() {
		return get(COUCH_USER);
	}
	
	public static String getCouchPassword() {
		return get(COUCH_PASSWORD);
	}
	
}
