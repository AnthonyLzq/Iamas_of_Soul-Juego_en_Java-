package server.message;

import server.BattleCityServerThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SendProtocol {

    public static void sendToConnectedClients(Message message, List<BattleCityServerThread> clientList) {
        for (BattleCityServerThread client : clientList) {
            try {
                ObjectOutputStream os = new ObjectOutputStream(client.getSocket().getOutputStream());
                os.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
