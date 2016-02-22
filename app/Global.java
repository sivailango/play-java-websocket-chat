import java.lang.reflect.Method;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http.Request;
import controllers.connection.CouchBase;

/**
 * 
 * @since 07-04-2015
 * @author aurum
 * 
 */
public class Global extends GlobalSettings {
	
	@SuppressWarnings("rawtypes")
	public Action onRequest(Request request, Method method) {
		Logger.info("method=" + request.method() + " uri=" + request.uri() + " remote-address=" + request.remoteAddress());
		return super.onRequest(request, method);
	}
	
	public void onStart(Application application) {
		CouchBase.connect();
	}
	
	public void onStop(Application application) {
		CouchBase.close();
	}
		
}
 

