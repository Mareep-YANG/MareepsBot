package org.mareep.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.mareep.Main;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class wsClient extends WebSocketClient {

    public wsClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("go-cqhttp服务器已连接");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("接收到机器人消息 : " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("机器人连接已断开");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("错误 : " + ex.getMessage());
    }

}
