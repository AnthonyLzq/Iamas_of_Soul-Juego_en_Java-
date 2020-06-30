package server;

import server.message.ReceiveProtocol;

import java.net.Socket;
import java.util.List;

public class BattleCityServerThread extends Thread {
    private final List<BattleCityServerThread> clientList;
    private Socket socket = null;

    public BattleCityServerThread(Socket socket, List<BattleCityServerThread> clientList) {
        super("BattleCityThread");
        this.socket = socket;
        this.clientList = clientList;
    }

    public void run() {
        new ReceiveProtocol(socket, clientList).start();
    }

    public Socket getSocket() {
        return socket;
    }
}
