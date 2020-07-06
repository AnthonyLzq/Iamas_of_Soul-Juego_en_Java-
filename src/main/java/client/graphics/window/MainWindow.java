package client.graphics.window;

import client.graphics.bullet.BulletContainer;
import client.graphics.tank.TankContainer;
import client.message.ReceiveProtocol;
import client.message.SendProtocol;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.Toolkit;

import java.net.Socket;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame implements ActionListener, KeyListener {
    private final Dimension WINDOW_SIZE = new Dimension(500, 500);
    private final AtomicBoolean PLAYER_SHOOT = new AtomicBoolean(false);
    private AtomicBoolean[] PLAYERS_SHOT = {
            new AtomicBoolean(false),
            new AtomicBoolean(false),
            new AtomicBoolean(false),
            new AtomicBoolean(false),
    };
    private Socket SOCKET;
    private List<TankContainer> tankContainers = new ArrayList<TankContainer>();
    private List<BulletContainer> bulletContainers = new ArrayList<BulletContainer>();
    private JButton startButton;
    private JLabel wait;
    private BulletContainer bulletContainer;

    public MainWindow(Socket socket){
        SOCKET = socket;

    }

    public void newPlayer(int nPlayers, List<String> ids) {
        int TOTAL_PLAYERS = 4;
        if (nPlayers == TOTAL_PLAYERS) {
            wait.setVisible(false);
            initGame(ids);
        } else waitScreen(nPlayers);
    }

    public void start() {
        createTankContainers(tankContainers);
        setProperties();
        startScreen();
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

    private void startScreen() {
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

    private void waitScreen(int nPlayers) {
        wait = new JLabel("Waiting for players: " + nPlayers +"/4");
        wait.setBounds(50, 50, 150, 30);
        wait.setVisible(true);
        add(wait);
    }

    private void createTankContainers(List<TankContainer> tankContainers){
        tankContainers.add(new TankContainer(getContentPane().getWidth(), getContentPane().getHeight(), SOCKET));
        tankContainers.add(new TankContainer(getContentPane().getWidth(), getContentPane().getHeight(), SOCKET));
        tankContainers.add(new TankContainer(getContentPane().getWidth(), getContentPane().getHeight(), SOCKET));
        tankContainers.add(new TankContainer(getContentPane().getWidth(), getContentPane().getHeight(), SOCKET));

        for(TankContainer tc: tankContainers) {
            add(tc);
        }
    }

    public void initGame(List<String> ids) {
        // TODO: IMPLEMENT BULLETS
        int i = 0;
        for(TankContainer tc: tankContainers) {
            tc.setTank(i++, ids.get(i++));
        }
    }

    public void setPosition(String id, String orientation, int x, int y) {}

    public void shootBulletInAllClients(String id) {}
/*
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
*/
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) handleInitGame();
    }

    public void keyTyped(java.awt.event.KeyEvent e) {
        if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) handleInitGame();
        // else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE && !PLAYER_SHOOT.get()) shoot(tankContainer.getOrientation());
        // else tankContainer.movement(e.getKeyCode());
    }

    public void keyPressed(java.awt.event.KeyEvent e) {
        if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) handleInitGame();
        // else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE && !PLAYER_SHOOT.get()) shoot(tankContainer.getOrientation());
        // else tankContainer.movement(e.getKeyCode());
    }

    public void keyReleased(java.awt.event.KeyEvent e) { }

    private void handleInitGame() {
        startButton.setVisible(false);
        for(TankContainer tc: tankContainers) {
            tc.setVisible(true);
        }
    }

    // public TankContainer getTankContainer() { return tankContainer; }
}
