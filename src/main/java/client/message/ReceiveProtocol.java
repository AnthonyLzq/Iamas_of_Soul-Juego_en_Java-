package client.message;

import client.graphics.window.MainWindow;
import message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiveProtocol extends Thread {
    private final MainWindow MAIN_WINDOW;
    private final Socket SOCKET;

    public ReceiveProtocol(Socket socket, MainWindow mw) {
        super("Receive protocol");
        this.MAIN_WINDOW = mw;
        this.SOCKET = socket;
    }

    public void run() {
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
            String inputLine;
            while ((inputLine = is.readLine()) != null) {
                Message messageFromServer = Message.parser(inputLine);
                switch (messageFromServer.getAction()) {
                    case "ASSIGN":
                        MAIN_WINDOW.setClientId(messageFromServer.getClientId());
                        break;
                    case "NEW":
                        MAIN_WINDOW.newPlayer(messageFromServer.getClientsIds().size(), messageFromServer.getClientsIds());
                        break;
                    case "START":
                        MAIN_WINDOW.newPlayer(2, messageFromServer.getClientsIds());
                        break;
                    case "MOVE":
                        MAIN_WINDOW.setPosition(
                                messageFromServer.getClientId(),
                                messageFromServer.getOrientation(),
                                messageFromServer.getPosX(),
                                messageFromServer.getPosY()
                        );
                        break;
                    case "SHOOT":
                        MAIN_WINDOW.shootBulletInAllClients(messageFromServer.getClientId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
