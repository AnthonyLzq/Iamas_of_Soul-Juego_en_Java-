package client.message;

import message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SendProtocol {
    public static void sendToServer(Socket socket, Message message) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
