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
                System.out.println(inputLine);
                Message messageFromServer = Message.parser(inputLine);
                System.out.println(messageFromServer.getClientsIds());
                switch (messageFromServer.getAction()) {
                    case "NEW":
                        System.out.println("ids: " + messageFromServer.getClientsIds());
                        MAIN_WINDOW.newPlayer(messageFromServer.getClientsIds().size(), null);
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
