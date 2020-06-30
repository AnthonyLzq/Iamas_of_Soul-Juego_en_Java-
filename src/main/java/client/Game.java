package client;

import client.graphics.window.MainWindow;

public class Game extends Thread {
    public void run() {
        MainWindow mainWindow = new MainWindow();
        mainWindow.start();
    }
}
