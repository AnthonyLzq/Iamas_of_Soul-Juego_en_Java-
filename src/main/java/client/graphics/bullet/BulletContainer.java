package client.graphics.bullet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class BulletContainer extends JLabel implements ActionListener {
    private final AtomicBoolean PLAYER_SHOOT;
    private final JLabel TANK_CONTAINER;
    private Timer timer;
    private String bulletDirection;

    public BulletContainer(AtomicBoolean playerShoot, JLabel tankContainer){
        Bullet bullet = new Bullet();
        setSize(bullet.getWidth(), bullet.getHeight());
        setIcon(bullet.getImage());
        setVisible(false);
        this.PLAYER_SHOOT = playerShoot;
        this.TANK_CONTAINER = tankContainer;
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
        Rectangle container = new Rectangle(
                TANK_CONTAINER.getX(),
                TANK_CONTAINER.getY(),
                TANK_CONTAINER.getWidth(),
                TANK_CONTAINER.getHeight()
        );
        if(bullet.intersects(container)){
            setVisible(false);
            TANK_CONTAINER.setVisible(false);
            PLAYER_SHOOT.set(false);
            timer.stop();
        }
        if(getX() < 1 || getX() > 500 || getY() < 1 || getY() > 500){
            setVisible(false);
            PLAYER_SHOOT.set(false);
            timer.stop();
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
