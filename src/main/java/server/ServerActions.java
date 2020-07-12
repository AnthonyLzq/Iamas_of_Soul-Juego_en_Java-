package server;

import message.Message;
import server.message.SendProtocol;
import server.utils.ClientChecker;

import java.io.ObjectOutputStream;
import java.util.List;

public class ServerActions {
    public static void onDisconnect(String clientId, List<BattleCityServerThread> clientList, List<String> clientIds) {
        int index = ClientChecker.removeFromClientList(clientId, clientList, clientIds);

        Message message = new Message();
        message.setClientId(clientId);
        message.setAction("DISCONNECT");
        message.setPlayerIndex(index);
        SendProtocol.sendToConnectedClients(message, clientList);
    }

    public static void onMoveTank(Message message, List<BattleCityServerThread> clientList) {
        SendProtocol.sendToConnectedClients(message, clientList);
    }

    public static void onShootTank(Message message, List<BattleCityServerThread> clientList) {
        SendProtocol.sendToConnectedClients(message, clientList);
    }

}
