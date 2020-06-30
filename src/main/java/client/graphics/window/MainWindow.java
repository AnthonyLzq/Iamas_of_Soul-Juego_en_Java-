package client.graphics.window;

import client.graphics.tank.Tank;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class MainWindow extends JFrame implements ActionListener, KeyListener {
    private final Point2D WINDOW_SIZE = new Point2D.Double(500, 500);
    private final int SPEED = 2;
    private JLabel tankContainer;
    private Tank newTank;
    private JButton startButton;

    public void start() {
        setProperties();
        initStartView();
        initGame();
    }

    private void setProperties() {
        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize = screen.getScreenSize();
        setTitle("Battle City");
        setSize((int) WINDOW_SIZE.getX(), (int) WINDOW_SIZE.getY());
        setLocation((screenSize.width - 500)/2, (screenSize.height - 500)/2 + 1);
        addKeyListener(this);
        setFocusable(true);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initStartView() {
        startButton = new JButton("Start");
        startButton.setBounds(
                (int) (WINDOW_SIZE.getX() - 100) / 2 + 1,
                (int) WINDOW_SIZE.getY() / 2,
                100,
                30);
        add(startButton);
        startButton.addActionListener(this);
    }

    public void initGame() {
        newTank = new Tank();
        tankContainer = new JLabel();
        tankContainer.setBounds(50, 50, newTank.getWidth(), newTank.getHeight());
        add(tankContainer);
    }

    private void movement(int keyCode) {
        switch(keyCode) {
            case(java.awt.event.KeyEvent.VK_UP):
            case(java.awt.event.KeyEvent.VK_W):
                up();
                break;
            case(java.awt.event.KeyEvent.VK_RIGHT):
            case(java.awt.event.KeyEvent.VK_D):
                right();
                break;
            case(java.awt.event.KeyEvent.VK_DOWN):
            case(java.awt.event.KeyEvent.VK_S):
                down();
                break;
            case(java.awt.event.KeyEvent.VK_LEFT):
            case(java.awt.event.KeyEvent.VK_A):
                left();
        }
    }

    private void up(){
        if(tankContainer.getY() > 0) {
            tankContainer.setLocation(tankContainer.getX(), tankContainer.getY() - SPEED);
            handleOrientation("up");
        }
    }

    private void right() {
        if(tankContainer.getX() + tankContainer.getWidth() < getContentPane().getWidth()){
            tankContainer.setLocation(tankContainer.getX() + SPEED, tankContainer.getY());
            handleOrientation("right");
        }
    }

    private void down() {
        if(tankContainer.getY() + tankContainer.getHeight() < getContentPane().getHeight()) {
            tankContainer.setLocation(tankContainer.getX(), tankContainer.getY() + SPEED);
            handleOrientation("down");
        }
    }

    private void left() {
        if(tankContainer.getX() > 0) {
            tankContainer.setLocation(tankContainer.getX() - SPEED, tankContainer.getY());
            handleOrientation("left");
        }
    }

    private void handleOrientation(String orientation) {
        newTank.setImage(orientation);
        tankContainer.setIcon(newTank.getImage());
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
        else this.movement(e.getKeyCode());
    }

    public void keyReleased(java.awt.event.KeyEvent e) { }

    private void handleInitGame() {
        startButton.setVisible(false);
        tankContainer.setIcon(newTank.getImage());
    }
}
