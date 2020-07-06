package server.message;

import message.Message;
import server.BattleCityServerThread;
import server.ServerActions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ReceiveProtocol extends Thread {
    private final List<BattleCityServerThread> CLIENT_LIST;
    private final List<String> CLIENT_IDS;

    private final String CLIENT_ID;
    private final Socket SOCKET;

    public ReceiveProtocol(List<BattleCityServerThread> clientList, List<String> clientIds, String clientId, Socket socket) {
        this.CLIENT_LIST = clientList;
        this.CLIENT_IDS = clientIds;
        this.CLIENT_ID = clientId;
        this.SOCKET = socket;
    }

    public void run() {
        try {
            String inputLine;
            PrintWriter pw = new PrintWriter(SOCKET.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
            Message messageId = new Message();

            messageId.setClientId(CLIENT_ID);
            messageId.setAction("ASSIGN");
            pw.println(messageId.toString());

            while ((inputLine = br.readLine()) != null) {
                Message message = Message.parser(inputLine);

                if (message.getAction().equals("ON_START")) {
                    Message initMessage = new Message();
                    initMessage.setClientsIds(CLIENT_IDS);
                    initMessage.setAction("NEW");

                    SendProtocol.sendToConnectedClients(CLIENT_LIST, initMessage);

                    if (CLIENT_LIST.size() == 2) {
                        Message startMessage = new Message();
                        startMessage.setAction("START");
                        startMessage.setClientsIds(CLIENT_IDS);

                        SendProtocol.sendToConnectedClients(CLIENT_LIST, startMessage);
                    }
                }
                if (message.getAction().equals("MOVE")) {
                    ServerActions.onMoveTank(CLIENT_LIST, message);
                }
                if (message.getAction().equals("DISCONNECT")) {
                    ServerActions.onDisconnect(CLIENT_LIST, CLIENT_IDS, message.getClientId());
                }
            }
            br.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
