package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BattleCityServer {

    public static void main(String[] args) {
        int portNumber = Integer.parseInt("3000");

        List<BattleCityServerThread> clientList = new ArrayList<>();
        List<String> clientIds = new ArrayList<>();
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(portNumber);

            while (true) {
                Socket client = serverSocket.accept();
                String clientId = UUID.randomUUID().toString();
                clientIds.add(clientId);
                BattleCityServerThread battleCityServerThread = new BattleCityServerThread(client, clientList, clientId, clientIds);
                clientList.add(battleCityServerThread);
                battleCityServerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
