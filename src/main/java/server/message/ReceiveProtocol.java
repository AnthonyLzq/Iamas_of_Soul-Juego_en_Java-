package server.message;

import server.BattleCityServerThread;
import server.ServerActions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

            Message initMessage = new Message();

            initMessage.setClientId(clientId);
            initMessage.setAction("NEW");

            os.writeObject(initMessage);

            if (clientList.size() == 2) {
                Message startMessage = new Message();
                startMessage.setAction("START");

                os.writeObject(startMessage);

                Message updateMessage = new Message();
                updateMessage.setClientsIds(clientIds);
                updateMessage.setAction("UPDATE_LIST");

                os.writeObject(updateMessage);
            }

            while (clientList.size() != 0) {
                Message message = (Message) is.readObject();

                if (message.getAction().equals("DISCONNECT")) {
                    ServerActions.onDisconnect(message.getClientId(), clientList, clientIds);
                }

                if (message.getAction().equals("MOVE")) {
                    ServerActions.onMoveTank(message, clientList);
                }

                if (message.getAction().equals("UPDATE_IDS")) {
                    ServerActions.onUpdateIdArray(message, clientIds);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

