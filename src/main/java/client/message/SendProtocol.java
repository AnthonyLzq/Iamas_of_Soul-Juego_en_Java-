package client.message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendProtocol {
    public static void sendToServer(Socket socket, Message message) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
