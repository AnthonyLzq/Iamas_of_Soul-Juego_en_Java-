package client.graphics.tank;

import client.message.SendProtocol;
import message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.Socket;

public class TankContainer extends JLabel {
    private final Socket SOCKET;
    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;

    private Tank TANK;
    private String orientation;
    private String id;

    public TankContainer(int frameWidth, int frameHeight, Socket socket) {
        setVisible(false);
        this.FRAME_WIDTH = frameWidth;
        this.FRAME_HEIGHT = frameHeight;
        this.SOCKET = socket;
        this.orientation = "up";
    }

    public void setTank(int code, String id) {
        this.id = id;
        TANK = new Tank(code);
        switch(code) {
            case 0:
                setBounds(50, 50, TANK.getWidth(), TANK.getHeight());
                break;
            case 1:
                setBounds(150, 50, TANK.getWidth(), TANK.getHeight());
                break;
            case 2:
                setBounds(50, 150, TANK.getWidth(), TANK.getHeight());
                break;
            case 3:
                setBounds(150, 150, TANK.getWidth(), TANK.getHeight());
        }
        setIcon(TANK.getImage());
    }

    public void movement(int keyCode) {
        int SPEED = 3;
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
                if (getX() + getWidth() < FRAME_WIDTH) {
                    orientation = "right";
                    newPosition = new Point(getX() + SPEED, getY());
                }
                break;
            case (KeyEvent.VK_DOWN):
            case (KeyEvent.VK_S):
                if (getY() + getHeight() < FRAME_HEIGHT) {
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
        Message message = new Message(id, newPosition, orientation);
        message.setAction("MOVE");
        SendProtocol.sendToServer(SOCKET, message);
    }

    private void setOrientation(String orientation) {
        TANK.setImage(orientation);
        setIcon(TANK.getImage());
    }

    public void setTankLocation(int x, int y, String orientation) {
        setLocation(x, y);
        setOrientation(orientation);
    }

    public String getOrientation() {
        return orientation;
    }

    public String getId() { return id; }
}
