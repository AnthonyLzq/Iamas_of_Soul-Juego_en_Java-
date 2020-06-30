package client.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveProtocol extends Thread {
    private final Socket SOCKET;

    public ReceiveProtocol(Socket socket) {
        super("Receive protocol");
        this.SOCKET = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader((this.SOCKET.getInputStream())));
            String messageFromServer;

            while ((messageFromServer = in.readLine()) != null) System.out.println(messageFromServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
