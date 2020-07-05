package client.graphics.window;

import client.graphics.bullet.Bullet;
import client.graphics.bullet.BulletContainer;
import client.graphics.tank.Tank;
import client.message.SendProtocol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;


public class MainWindow extends JFrame implements ActionListener, KeyListener {
    private final Socket SOCKET;
    private final Dimension WINDOW_SIZE = new Dimension(500, 500);
    private JLabel tankContainer;
    private Tank newTank;
    private JButton startButton;
    private BulletContainer bulletContainer;
    private final AtomicBoolean playerShoot = new AtomicBoolean(false);
    private String bulletDirection;


    public MainWindow(Socket socket){
        this.SOCKET = socket;
    }

    public void start() {
        setProperties();
        initStartView();
        initGame();
    }

    private void setProperties() {
        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize = screen.getScreenSize();
        setTitle("Battle City");
        setSize(WINDOW_SIZE.width, WINDOW_SIZE.height);
        setLocation((screenSize.width - WINDOW_SIZE.width)/2, (screenSize.height - WINDOW_SIZE.height)/2 + 1);
        addKeyListener(this);
        setFocusable(true);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initStartView() {
        Dimension ButtonSize = new Dimension(100, 30);
        startButton = new JButton("Start");
        startButton.setBounds(
                (WINDOW_SIZE.width - ButtonSize.width)/2 + 1,
                WINDOW_SIZE.height / 2,
                ButtonSize.width,
                ButtonSize.height
        );
        add(startButton);
        startButton.addActionListener(this);
    }

    public void initGame() {
        newTank = new Tank();
        tankContainer = new JLabel();
        tankContainer.setBounds(50, 50, newTank.getWidth(), newTank.getHeight());
        add(tankContainer);
        Bullet newBullet = new Bullet();
        bulletContainer = new BulletContainer(playerShoot, tankContainer);
        add(bulletContainer);
    }

    private void movement(int keyCode) {
        switch(keyCode) {
            case(java.awt.event.KeyEvent.VK_UP):
            case(java.awt.event.KeyEvent.VK_W):
                movement("up");
                break;
            case(java.awt.event.KeyEvent.VK_RIGHT):
            case(java.awt.event.KeyEvent.VK_D):
                movement("right");
                break;
            case(java.awt.event.KeyEvent.VK_DOWN):
            case(java.awt.event.KeyEvent.VK_S):
                movement("down");
                break;
            case(java.awt.event.KeyEvent.VK_LEFT):
            case(java.awt.event.KeyEvent.VK_A):
                movement("left");
        }
    }

    private void movement(String orientation) {
        Point newPosition;
        int SPEED = 3;
        bulletDirection = orientation;
        if(orientation.equals("up") && tankContainer.getY() > 0) {
            newPosition = new Point(tankContainer.getX(), tankContainer.getY() - SPEED);
            sendMessage(newPosition, orientation);
        } else if(orientation.equals("right") &&
                tankContainer.getX() + tankContainer.getWidth() < getContentPane().getWidth()) {
            newPosition = new Point(tankContainer.getX() + SPEED, tankContainer.getY());
            sendMessage(newPosition, orientation);
        } else if(orientation.equals("down") &&
                tankContainer.getY() + tankContainer.getHeight() < getContentPane().getHeight()) {
            newPosition = new Point(tankContainer.getX(), tankContainer.getY() + SPEED);
            sendMessage(newPosition, orientation);
        } else if(orientation.equals("left") && tankContainer.getX() > 0) {
            newPosition = new Point(tankContainer.getX() - SPEED, tankContainer.getY());
            sendMessage(newPosition, orientation);
        }
    }

    private void setOrientation(String orientation) {
        newTank.setImage(orientation);
        tankContainer.setIcon(newTank.getImage());
    }

    public void setTankLocation(String newStringPositionAndOrientation) {
        /*
        * info:
        * - info[0]: X position
        * - info[1]: Y position
        * - info[2]: orientation
        *
        */
        String[] info = newStringPositionAndOrientation.split(" ");
        Point newPosition = new Point(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
        tankContainer.setLocation(newPosition);
        setOrientation(info[2]);
    }


    public void shoot(){
        playerShoot.set(true);
        switch (bulletDirection) {
            case "up":
                bulletContainer.shoot(bulletDirection, tankContainer.getX() + 21, tankContainer.getY() - 10);
                break;
            case "right":
                bulletContainer.shoot(bulletDirection,tankContainer.getX() + 50, tankContainer.getY() + 20);

                break;
            case "down":
                bulletContainer.shoot(bulletDirection,tankContainer.getX() + 21, tankContainer.getY() + 50);
                break;
            case "left":
                bulletContainer.shoot(bulletDirection,tankContainer.getX() - 10, tankContainer.getY() + 20);
                break;
        }
    }


    private void sendMessage(Point newPosition, String orientation) {
        String message = String.join(
                " ",
                String.valueOf((int) newPosition.getX()),
                String.valueOf((int) newPosition.getY()),
                orientation
        );
        SendProtocol.sendToServer(SOCKET, message);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) handleInitGame();
    }

    public void keyTyped(java.awt.event.KeyEvent e) {
        if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) handleInitGame();
        else this.movement(e.getKeyCode());
    }

    public void keyPressed(java.awt.event.KeyEvent e) {
        if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) handleInitGame();
        else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE && !playerShoot.get())shoot();
        else this.movement(e.getKeyCode());
    }

    public void keyReleased(java.awt.event.KeyEvent e) { }

    private void handleInitGame() {
        startButton.setVisible(false);
        tankContainer.setIcon(newTank.getImage());
    }
}
