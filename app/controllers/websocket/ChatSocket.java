package controllers.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

import models.Conversation;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.WebSocket;

/**
 * @author valore
 *
 */
public class ChatSocket {
	
	private static List<WebSocket.Out<String>> connections = new ArrayList<WebSocket.Out<String>>();
	
	public static void start(WebSocket.In<String> in, WebSocket.Out<String> out) {
		
		connections.add(out);
		
		in.onMessage(new Callback<String>() {
            public void invoke(String event) {
                ChatSocket.notifyAll(event);
            }
        });
		
        in.onClose(new Callback0() {
            public void invoke() {
                ChatSocket.notifyAll("Disconnected");
            }
        });
	}
	/*
	public static void start(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {
			in.onMessage(new Callback<JsonNode>() {
				
				@Override
				public void invoke(JsonNode json) throws Throwable {
					
	            	if(Conversation.isExist(senderId, receiverId)) {
	            		Conversation c = Conversation.findById(key);
	            	} else {
	            		Conversation c = new Conversation();
	            		c.id = UUID.randomUUID().toString();
	            		c.senderId = senderId;
	            		c.receiverId = receiverId;
	            		Conversation.save(c);
	            	}
	                ChatSocket.notifyAll("asdsa");
					
				}
	        });
		
	}
	*/
	public static void notifyAll(String message) {
		for(WebSocket.Out<String> out : connections) {
			out.write(message);
		}
	}
	
}
