package client.graphics.bullet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class BulletContainer extends JLabel implements ActionListener {
    Timer timer;
    Bullet bullet;
    int SPEED_BULLET = 1;
    String bulletDirection;
    AtomicBoolean playerShoot;
    JLabel tankContainer;

    public BulletContainer(AtomicBoolean playerShoot, JLabel tankContainer){
        this.bullet = new Bullet();
        setSize(bullet.getWidth(), bullet.getHeight());
        setIcon(bullet.getImage());
        setVisible(false);
        this.playerShoot = playerShoot;
        this.tankContainer = tankContainer;
    }

    public void shoot(String bulletDirection, int x, int y){
        this.bulletDirection = bulletDirection;
        setLocation(x, y);
        setVisible(true);
        timer = new Timer(20, this);
        timer.start();
    }

    private void checkCollision(){
        if(new Rectangle(getX(), getY(), getWidth(), getHeight())
                .intersects(new Rectangle(tankContainer.getX(), tankContainer.getY(), tankContainer.getWidth(), tankContainer.getHeight()))){
            setVisible(false);
            tankContainer.setVisible(false);
            playerShoot.set(false);
            timer.stop();
        }
        if(getX() < 1 || getX() > 500 || getY() < 1 || getY() > 500){
            setVisible(false);
            playerShoot.set(false);
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
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
        System.out.println(getX() + " " + getY());
        checkCollision();
    }
}
