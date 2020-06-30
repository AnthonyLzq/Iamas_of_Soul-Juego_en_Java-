package client.message;

import client.graphics.window.MainWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            BufferedReader in = new BufferedReader(new InputStreamReader((this.SOCKET.getInputStream())));
            String messageFromServer;

            // Send to position to the tank
            while ((messageFromServer = in.readLine()) != null) MAIN_WINDOW.setTankLocation(messageFromServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
