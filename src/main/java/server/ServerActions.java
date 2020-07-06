package server;

import message.Message;
import server.message.SendProtocol;
import server.utils.ClientChecker;

import java.io.ObjectOutputStream;
import java.util.List;

public class ServerActions {
    public static void onDisconnect(List<BattleCityServerThread> clientList, List<String> clientIds, String clientId) {
        ClientChecker.removeFromClientList(clientId, clientList, clientIds);

        Message message = new Message();
        message.setClientId(clientId);
        message.setAction("DISCONNECT");

        SendProtocol.sendToConnectedClients(clientList, message);
    }

    public static void onMoveTank(List<BattleCityServerThread> clientList, Message message) {
        SendProtocol.sendToConnectedClients(clientList, message);
    }
}
