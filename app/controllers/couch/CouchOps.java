package controllers.couch;

import java.util.ArrayList;
import java.util.List;

import play.libs.Json;
import net.spy.memcached.PersistTo;
import net.spy.memcached.ReplicateTo;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewResponse;
import com.couchbase.client.protocol.views.ViewRow;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

import controllers.connection.CouchBase;
import controllers.util.GsonProvider;

/**
 * @author valore
 *
 */
public class CouchOps<T> {
	
	public final Class<T> clazz;
	
	public static CouchbaseClient bucket = CouchBase.get();
	private static Gson gson = GsonProvider.getInstance();
	
	public CouchOps(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public void save(String key, T value) {
		bucket.set(key, gson.toJson(value), PersistTo.MASTER);
	}
	
	public T findByKey(String key, Class<T> clazz) {
		return gson.fromJson((String) bucket.get(key), clazz);
	}
	
	public JsonNode findByKey(String key) {
		return Json.parse(bucket.get(key).toString());
	}
	
	public List<T> findAll(String designDocument, String view, Query query, Class<T> clazz) {
		View v = bucket.getView(designDocument, view);
		ViewResponse response = bucket.query(v, query);
		List<T> t = new ArrayList<T>();
		if(response.size() > 0) {
			for(ViewRow row : response) {
				t.add(gson.fromJson((String) bucket.get(row.getId()), clazz));
			}	
		}
		return t;
	}
	
	public ViewResponse getViewResponse(String designDocument, String view, Query query) {
		View v = bucket.getView(designDocument, view);
		return bucket.query(v, query);
	}
	
	public int count(String designDocument, String view, Query query, Class<T> clazz) {
		View v = bucket.getView(designDocument, view);
		ViewResponse response = bucket.query(v, query);
		return response.size();
	}
	
	public void update(String key, T value) {
		bucket.replace(key, gson.toJson(value), PersistTo.MASTER, ReplicateTo.ZERO);
	}
	
	public void delete(String key) {
		bucket.delete(key, PersistTo.MASTER, ReplicateTo.ZERO);
	}
		
	public String findKey(String designDocument, String view, Query query, Class<T> clazz) {
		String key = "";
		View v = bucket.getView(designDocument, view);
		ViewResponse response = bucket.query(v, query);
		if(response.size() > 0) {
			for(ViewRow row : response) {
				key = row.getKey();
				break;
			}
		}
		return key;
	}
	
	public String findValue(String designDocument, String view, Query query, Class<T> clazz) {
		String key = "";
		View v = bucket.getView(designDocument, view);
		ViewResponse response = bucket.query(v, query);
		if(response.size() > 0) {
			for(ViewRow row : response) {
				key = row.getValue();
				break;
			}
		}
		return key;
	}
	
	public boolean isExist(String designDocument, String view, Query query, Class<T> clazz) {
		View v = bucket.getView(designDocument, view);
		ViewResponse response = bucket.query(v, query);
		if(response.size() > 0) {
			return true;
		}	
		return false;
	}
}
