package server;

import server.message.ReceiveProtocol;

import java.net.Socket;
import java.util.List;

public class BattleCityServerThread extends Thread {
    private final List<BattleCityServerThread> clientList;
    private final String clientId;
    private final List<String> clientIds;
    private Socket socket = null;

    public BattleCityServerThread(Socket socket, List<BattleCityServerThread> clientList, String clientId, List<String> clientIds) {
        super("BattleCityThread");
        this.socket = socket;
        this.clientList = clientList;
        this.clientId = clientId;
        this.clientIds = clientIds;
    }

    public void run() {
        new ReceiveProtocol(socket, clientList, clientId, clientIds).start();
    }

    public Socket getSocket() {
        return socket;
    }

    public String getClientId() {
        return clientId;
    }
}
