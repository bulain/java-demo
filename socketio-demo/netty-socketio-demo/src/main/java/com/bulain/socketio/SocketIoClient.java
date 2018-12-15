package com.bulain.socketio;

import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketIoClient {
	private static Logger logger = LoggerFactory.getLogger(SocketIoClient.class);

	public static void main(String[] args) throws Exception {
		IO.Options opts = new IO.Options();
		opts.forceNew = true;
		opts.reconnection = true;

		final Socket socket = IO.socket("http://localhost:8080", opts);

		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				logger.info("connect - {}", args);

				try {
					JSONObject obj = new JSONObject();
					obj.put("type", "sevent");
					obj.put("time", new Date().getTime());
					obj.put("data", "data");
					socket.emit("regist", obj);
				} catch (Exception e) {
					socket.disconnect();
				}
			}
		});
		socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				logger.info("disconnect - {}", args);
			}
		});

		socket.on("stop", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				logger.info("stop - {}", args[0]);

				if (args.length > 1) {
					if (args[1] instanceof Ack) {
						Ack ack = (Ack) args[1];
						ack.call();
					}
				}

			}
		});
		socket.on("data", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				logger.info("data - {}", args[0]);

				if (args.length > 1) {
					if (args[1] instanceof Ack) {
						Ack ack = (Ack) args[1];
						ack.call();
					}
				}

			}
		});

		socket.connect();

	}

}
