package controllers.couch;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;

/**
 * @author aurum
 * @since 03-13-2015
 */
public class CBQuery {
	
	public static Query get() {
		return new Query().setIncludeDocs(true).setStale(Stale.FALSE);
	}
	
	public static Query get(QueryObject object) {
		Query query = new Query().setIncludeDocs(true).setStale(Stale.FALSE);
		if(object.key != null) {
			query.setKey(object.key);
		}	
		if(object.keys != null) {
			query.setKey(ComplexKey.of(object.keys));
		}
		if(object.key1 != null && object.key2 != null) {
			query.setKey(ComplexKey.of(object.key1, object.key2));
		}
		return query;
	}
	
}
