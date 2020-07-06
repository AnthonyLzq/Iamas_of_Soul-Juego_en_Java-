package client.message;

import client.graphics.window.MainWindow;

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
            ObjectInputStream is = new ObjectInputStream(this.SOCKET.getInputStream());
            while (true) {
                try {
                    Message messageFromServer = (Message) is.readObject();
                    switch (messageFromServer.getAction()) {
                        case "NEW":
                            MAIN_WINDOW.newPlayer(messageFromServer.getClientsIds().size(), null);
                            break;
                        case "START":
                            MAIN_WINDOW.newPlayer(4, messageFromServer.getClientsIds());
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
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
