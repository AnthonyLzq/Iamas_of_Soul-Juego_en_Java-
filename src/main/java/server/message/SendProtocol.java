package server.message;

import message.Message;
import server.BattleCityServerThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class SendProtocol {
    public static void sendToConnectedClients(List<BattleCityServerThread> clientList, Message message) {
        for (BattleCityServerThread client: clientList) {
            try {
                PrintWriter pw = new PrintWriter(client.getSocket().getOutputStream(), true);
                pw.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
