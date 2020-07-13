package client.graphics.bullet;

import client.graphics.tank.TankContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;

public class BulletContainer extends JLabel implements ActionListener {
    private final AtomicBoolean PLAYER_SHOOT;
    private final List<TankContainer> TANK_CONTAINERS;
    private Timer timer;
    private String bulletDirection;
    private JFrame MAIN_WINDOW;

    public BulletContainer(AtomicBoolean playerShoot, List<TankContainer> tankContainers, JFrame MainWindow){
        Bullet bullet = new Bullet();
        setSize(bullet.getWidth(), bullet.getHeight());
        setIcon(bullet.getImage());
        setVisible(false);
        this.PLAYER_SHOOT = playerShoot;
        this.TANK_CONTAINERS = tankContainers;
        this.MAIN_WINDOW = MainWindow;
    }

    public void shoot(String bulletDirection, int x, int y){
        this.bulletDirection = bulletDirection;
        setLocation(x, y);
        setVisible(true);
        timer = new Timer(20, this);
        timer.start();
    }

    private void checkCollision(){
        Rectangle bullet = new Rectangle(getX(), getY(), getWidth(), getHeight());
        for (TankContainer tc : TANK_CONTAINERS){
            if(tc.isVisible()){
                Rectangle container = new Rectangle(
                        tc.getX(),
                        tc.getY(),
                        tc.getWidth(),
                        tc.getHeight()
                );
                if(bullet.intersects(container)){
                    setVisible(false);
                    tc.setVisible(false);
                    PLAYER_SHOOT.set(false);
                    MAIN_WINDOW.remove(tc);
                    timer.stop();
                }
                if(getX() < 1 || getX() > 500 || getY() < 1 || getY() > 500){
                    setVisible(false);
                    PLAYER_SHOOT.set(false);
                    timer.stop();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int SPEED_BULLET = 4;
        switch (bulletDirection){
            case "up":
                setLocation(getX(), getY() - SPEED_BULLET);
                break;
            case "right":
                setLocation(getX() + SPEED_BULLET, getY());
                break;
            case "down":
                setLocation(getX(), getY() + SPEED_BULLET);
                break;
            case "left":
                setLocation(getX() - SPEED_BULLET, getY());
                break;
        }
        checkCollision();
    }
}
