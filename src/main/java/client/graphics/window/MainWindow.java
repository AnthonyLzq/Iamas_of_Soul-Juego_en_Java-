package client.graphics.window;

import client.graphics.bullet.BulletContainer;
import client.graphics.tank.TankContainer;

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
    private final AtomicBoolean PLAYER_SHOOT = new AtomicBoolean(false);
    private TankContainer tankContainer;
    private JButton startButton;
    private BulletContainer bulletContainer;

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
        tankContainer = new TankContainer(getContentPane().getWidth(), getContentPane().getHeight(), SOCKET);
        add(tankContainer);
        bulletContainer = new BulletContainer(PLAYER_SHOOT, tankContainer);
        add(bulletContainer);
    }

    public void shoot(String bulletDirection){
        if(tankContainer.isVisible()) {
            PLAYER_SHOOT.set(true);
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
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) handleInitGame();
    }

    public void keyTyped(java.awt.event.KeyEvent e) {
        if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) handleInitGame();
        else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE && !PLAYER_SHOOT.get()) shoot(tankContainer.getOrientation());
        else tankContainer.movement(e.getKeyCode());
    }

    public void keyPressed(java.awt.event.KeyEvent e) {
        if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) handleInitGame();
        else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE && !PLAYER_SHOOT.get()) shoot(tankContainer.getOrientation());
        else tankContainer.movement(e.getKeyCode());
    }

    public void keyReleased(java.awt.event.KeyEvent e) { }

    private void handleInitGame() {
        startButton.setVisible(false);
        tankContainer.setVisible(true);
    }

    public TankContainer getTankContainer() { return tankContainer; }
}
