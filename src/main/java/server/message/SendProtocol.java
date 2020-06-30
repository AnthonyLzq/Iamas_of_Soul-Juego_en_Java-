package server.message;

import server.BattleCityServerThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SendProtocol {

    public static void sendToConnectedClients(String message, List<BattleCityServerThread> clientList) {
        for (BattleCityServerThread client : clientList) {
            try {
                PrintWriter out = new PrintWriter(client.getSocket().getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
