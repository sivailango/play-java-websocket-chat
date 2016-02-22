package controllers.connection;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import play.Logger;

import com.couchbase.client.CouchbaseClient;

import controllers.config.AppConfig;

/**
 * @author aurum
 * @since 03-11-2015
 * 
 * <p>
 * Connection with Couchbase server at application startup 
 * </p>
 *
 */
public final class CouchBase {
	
	private static CouchbaseClient client = null;
	
	/**
	 * @return
	 */
	public static boolean connect() {
 
        List<URI> uris = new LinkedList<URI>();
        uris.add(URI.create("http://" + AppConfig.getCouchHost() + ":" + AppConfig.getCouchPort() + "/pools"));
 
        try {
        	client = new CouchbaseClient(uris, AppConfig.getCouchBucket(), AppConfig.getCouchUser(), AppConfig.getCouchPassword());
        } 
        catch(IOException e) {
            Logger.error("Exception: Couchbase connection could not established");
            System.exit(0);
        }
        
		return true;
	}
	
	/**
	 * @return
	 */
	public static boolean close() {
        if(client == null) 
            return false;
        return client.shutdown(3, TimeUnit.SECONDS);
    }
		
    /**
     * @return
     */
    public static CouchbaseClient get() {
        if(client == null) 
            connect();
        return client;
    }

    
}
