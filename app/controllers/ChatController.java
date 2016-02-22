package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import controllers.websocket.ChatSocket;

/**
 * @author valore
 *
 */
public class ChatController extends Controller {
	
	public static Result mychat(String id) {
		return ok(views.html.chat.chat.render(id, User.list()));
	}
	
	public static WebSocket<String> chat() {
		
		return new WebSocket<String>() {

			@Override
			public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
				ChatSocket.start(in, out);
			}
			
		};
		
	}
	
	/*
	public static WebSocket<JsonNode> chat() {
		return new WebSocket<JsonNode>() {
			@Override
			public void onReady(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) {
				//ChatSocket.start(in, out);
				ChatSocket.start(in, out);
			}
		};
		
	}*/
	
}
