package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BattleCityServer {

    public static void main(String[] args) {
        int portNumber = Integer.parseInt("3000");
        boolean listening = true;

        ServerSocket serverSocket;
        List<BattleCityServerThread> clientList = new ArrayList<BattleCityServerThread>();

        try {
            serverSocket = new ServerSocket(portNumber);

            while (listening) {
                Socket client = serverSocket.accept();
                BattleCityServerThread battleCityServerThread = new BattleCityServerThread(client, clientList);
                clientList.add(battleCityServerThread);
                battleCityServerThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
