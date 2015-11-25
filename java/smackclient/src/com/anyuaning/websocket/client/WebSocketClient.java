package com.anyuaning.websocket.client;

import java.net.URI;
import java.util.HashMap;

import com.anyuaning.websocket.core.WebSocket;

public class WebSocketClient {

	public static void main(String[] args) {
		try {
			HashMap<String, String> headers = new HashMap<String, String>();
			headers.put("key1", "value1");
			headers.put("key2", "value2");
			
			WebSocket ws = new WebSocket(new URI("ws://192.168.1.130:5280/websocket"));
			ws.setHeaders(headers);
			ws.connect();

			String request = "Hello";
			ws.send(request);
			String response = ws.recv(); 
			System.out.println(request);
			if (request.equals(response)) {
				System.out.print("Success!");
			} else {
				System.out.print("Failed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
