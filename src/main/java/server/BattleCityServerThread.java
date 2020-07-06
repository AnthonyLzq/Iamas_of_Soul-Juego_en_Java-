package server;

import server.message.ReceiveProtocol;

import java.net.Socket;
import java.util.List;

public class BattleCityServerThread extends Thread {
    private final List<BattleCityServerThread> clientList;
    private final List<String> clientIds;
    private final String clientId;
    private final Socket SOCKET;

    public BattleCityServerThread(Socket socket, List<BattleCityServerThread> clientList, String clientId, List<String> clientIds) {
        super("BattleCityThread");
        this.clientList = clientList;
        this.clientIds = clientIds;
        this.clientId = clientId;
        this.SOCKET = socket;
    }

    public void run() {
        new ReceiveProtocol(clientList,  clientIds, clientId, SOCKET).start();
    }

    public Socket getSocket() {
        return SOCKET;
    }

    public String getClientId() {
        return clientId;
    }
}
