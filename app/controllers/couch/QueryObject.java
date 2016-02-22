/**
 * 
 */
package controllers.couch;

/**
 * @author valore
 *
 */
public class QueryObject {

	public String key;
	public Object[] keys;
	public String key1;
	public String key2;
	
	public QueryObject(String key) {
		this.key = key;
	}
	
	public QueryObject(Object[] keys) {
		this.keys = keys;
	}
	
	public QueryObject(String key1, String key2) {
		this.key1 = key1;
		this.key2 = key2;
	}
	
}
