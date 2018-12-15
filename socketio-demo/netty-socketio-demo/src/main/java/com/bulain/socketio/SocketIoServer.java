package com.bulain.socketio;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.socketio.pojo.DataEvent;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class SocketIoServer {
	private static Logger logger = LoggerFactory.getLogger(SocketIoServer.class);

	public static void main(String[] args) throws Exception {
		Configuration config = new Configuration();
		config.setHostname("0.0.0.0");
		config.setPort(8080);

		config.setAuthorizationListener(new AuthorizationListener() {
			public boolean isAuthorized(HandshakeData data) {
				logger.info("isAuthorized - {}", data);
				return true;
			}

		});

		SocketIOServer server = new SocketIOServer(config);

		DataListener<DataEvent> listner = new DataListener<DataEvent>() {
			@Override
			public void onData(SocketIOClient client, DataEvent data, AckRequest ackSender) throws Exception {
				logger.info("regist - {}", data);
				
				UUID sessionId = client.getSessionId();
				logger.info("sessionId - {}", sessionId);
			}
		};
		server.addEventListener("regist", DataEvent.class, listner);

		server.addConnectListener(new ConnectListener() {
			public void onConnect(SocketIOClient client) {
				logger.info("onConnect - {}", client);
			}
		});

		server.addDisconnectListener(new DisconnectListener() {
			public void onDisconnect(SocketIOClient client) {
				logger.info("onDisconnect - {}", client);
			}
		});

		server.start();
		Thread.sleep(Integer.MAX_VALUE);
		server.stop();
	}

}
