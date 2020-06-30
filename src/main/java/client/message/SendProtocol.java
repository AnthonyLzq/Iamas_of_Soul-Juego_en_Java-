package client.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendProtocol extends Thread {
    private final Socket SOCKET;

    public SendProtocol(Socket socket) {
        super("Send protocol");
        this.SOCKET = socket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(this.SOCKET.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String messageFromUser;

            while((messageFromUser = stdIn.readLine()) != null) out.println(messageFromUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
