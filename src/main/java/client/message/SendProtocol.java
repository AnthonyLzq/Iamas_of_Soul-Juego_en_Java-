package client.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendProtocol {
    public static void sendToServer(Socket socket, String message) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
