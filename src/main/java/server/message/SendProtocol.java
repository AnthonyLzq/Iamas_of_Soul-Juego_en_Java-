package server.message;

import message.Message;
import server.BattleCityServerThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class SendProtocol {
    public static void sendToConnectedClients(Message message, List<BattleCityServerThread> clientList) {
        for (BattleCityServerThread client: clientList) {
            try {
                PrintWriter os = new PrintWriter(client.getSocket().getOutputStream(), true);
                os.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
