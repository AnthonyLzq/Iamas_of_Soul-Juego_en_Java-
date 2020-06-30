package client;

import client.message.ReceiveProtocol;
import client.message.SendProtocol;

import java.io.IOException;
import java.net.Socket;

public class BattleCityClient {
    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT_NUMBER = 4444;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST_NAME, PORT_NUMBER);

            new SendProtocol(socket).start();
            new ReceiveProtocol(socket).start();
            new Game().start();// New graphic instance

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
