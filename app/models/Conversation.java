/**
 * 
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import controllers.couch.CBQuery;
import controllers.couch.CouchOps;
import controllers.couch.QueryObject;

/**
 * @author valore
 *
 */
public class Conversation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String id;
	public String type = "conversation";
	
	public String senderId;
	public String receiverId;
	
	public List<Message> messages = new ArrayList<Message>();
	
	private static CouchOps<Conversation> bucket = new CouchOps<Conversation>(Conversation.class);
	
	public static void save(Conversation c) {
		bucket.save(c.id, c);
	}
	
	public static Conversation findById(String key) {
		return bucket.findByKey(key, Conversation.class);
	}
	
	public static boolean isExist(String senderId, String receiverId) {
		Object keys[] = new Object[2];
		keys[1] = senderId;
		keys[2] = receiverId;
		return bucket.isExist("dev_conversation", "by_isexist", CBQuery.get(new QueryObject(keys)),
								Conversation.class);
	}

}
