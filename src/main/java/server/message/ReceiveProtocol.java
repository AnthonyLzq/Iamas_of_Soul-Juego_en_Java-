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

    private final Socket socket;
    private final List<BattleCityServerThread> clientList;
    private final String clientId;
    private final List<String> clientIds;

    public ReceiveProtocol(Socket socket, List<BattleCityServerThread> clientList, String clientId, List<String> clientIds) {
        this.socket = socket;
        this.clientList = clientList;
        this.clientId = clientId;
        this.clientIds = clientIds;
    }

    public void run() {
        try {
            PrintWriter os = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            Message messageId = new Message();
            messageId.setClientId(clientId);
            messageId.setAction("ASSIGN");
            os.println(messageId.toString());

            while ((inputLine = is.readLine()) != null) {
                Message message = Message.parser(inputLine);
                if (message.getAction().equals("DISCONNECT")) {
                    ServerActions.onDisconnect(message.getClientId(), clientList, clientIds);
                }

                if (message.getAction().equals("MOVE")) {
                    ServerActions.onMoveTank(message, clientList);
                }

                if (message.getAction().equals("ON_START")) {
                    Message initMessage = new Message();
                    initMessage.setClientsIds(clientIds);
                    initMessage.setAction("NEW");

                    SendProtocol.sendToConnectedClients(initMessage, clientList);


                    if (clientList.size() == 4) {
                        Message startMessage = new Message();
                        startMessage.setAction("START");
                        startMessage.setClientsIds(clientIds);

                        SendProtocol.sendToConnectedClients(startMessage, clientList);
                    }
                }

                if (message.getAction().equals("SHOOT")) {
                    ServerActions.onShootTank(message, clientList);
                }
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
