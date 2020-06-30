package client;

import client.graphics.window.MainWindow;
import client.message.ReceiveProtocol;

import java.net.Socket;

public class Game extends Thread {
    private final Socket SOCKET;

    public Game(Socket socket) {
        this.SOCKET = socket;
    }

    public void run() {
        MainWindow mainWindow = new MainWindow(SOCKET);
        mainWindow.start();
        new ReceiveProtocol(SOCKET, mainWindow).start();
    }
}
