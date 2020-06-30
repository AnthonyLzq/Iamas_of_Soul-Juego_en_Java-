package server.message;

import server.BattleCityServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ReceiveProtocol extends Thread {

    private final int[][] griMap = {{1, 0, 1}, {1, 0, 0}, {0, 1, 1}};
    private final Socket socket;
    private final List<BattleCityServerThread> clientList;

    public ReceiveProtocol(Socket socket, List<BattleCityServerThread> clientList) {
        this.socket = socket;
        this.clientList = clientList;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;

            //out.println(Arrays.deepToString(this.griMap));

            while ((inputLine = in.readLine()) != null) {
                SendProtocol.sendToConnectedClients(inputLine, clientList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

