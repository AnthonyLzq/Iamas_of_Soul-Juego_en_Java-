package client.graphics.tank;

import client.message.SendProtocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

public class TankContainer extends JLabel{
    int SPEED = 3;
    int frameWidth;
    int frameHeight;
    Socket socket;

    public String getOrientation() {
        return orientation;
    }

    String orientation;

    public TankContainer(int frameWidth, int frameHeight, Socket socket) {
        Tank tank = new Tank();
        setBounds(50, 50, tank.getWidth(), tank.getHeight());
        setIcon(tank.getImage());
        setVisible(false);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.socket = socket;
        this.orientation = "up";
    }

    public void movement(int keyCode) {
        Point newPosition = null;
        switch (keyCode) {
            case (KeyEvent.VK_UP):
            case (KeyEvent.VK_W):
                if (getY() > 0) {
                    orientation = "up";
                    newPosition = new Point(getX(), getY() - SPEED);
                }
                break;
            case (KeyEvent.VK_RIGHT):
            case (KeyEvent.VK_D):
                if (getX() + getWidth() < frameWidth) {
                    orientation = "right";
                    newPosition = new Point(getX() + SPEED, getY());
                }
                break;
            case (KeyEvent.VK_DOWN):
            case (KeyEvent.VK_S):
                if (getY() + getHeight() < frameHeight) {
                    orientation = "down";
                    newPosition = new Point(getX(), getY() + SPEED);
                }
                break;
            case (KeyEvent.VK_LEFT):
            case (KeyEvent.VK_A):
                if (getX() > 0) {
                    orientation = "left";
                    newPosition = new Point(getX() - SPEED, getY());
                }
        }
        if(newPosition != null) sendMessage(newPosition, orientation);
    }

    private void sendMessage(Point newPosition, String orientation) {
        String message = String.join(
                " ",
                String.valueOf((int) newPosition.getX()),
                String.valueOf((int) newPosition.getY()),
                orientation
        );
        SendProtocol.sendToServer(socket, message);
    }

}
