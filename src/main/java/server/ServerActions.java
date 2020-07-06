package server;

import server.message.Message;
import server.message.SendProtocol;
import server.utils.ClientChecker;

import java.util.List;

public class ServerActions {
    public static void onDisconnect(String clientId, List<BattleCityServerThread> clientList, List<String> clientIds) {
        ClientChecker.removeFromClientList(clientId, clientList, clientIds);

        Message message = new Message();
        message.setClientId(clientId);
        message.setAction("DISCONNECT");

        SendProtocol.sendToConnectedClients(message, clientList);
    }

    public static void onMoveTank(Message message, List<BattleCityServerThread> clientList) {
        SendProtocol.sendToConnectedClients(message, clientList);
    }
}
