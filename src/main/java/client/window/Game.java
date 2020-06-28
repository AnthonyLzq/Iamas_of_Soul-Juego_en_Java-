package client.window;

import client.tank.Tank;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private JLabel tankContainer;
    private Tank newTank;

    public Game() {
        this.newTank = new Tank();
        this.showTank();
        this.setProperties();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setProperties() {
        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize = screen.getScreenSize();
        setTitle("Battle City");
        setSize(500, 500);
        setLocation((screenSize.width - 500)/2, (screenSize.height - 500)/2 + 1);
        setResizable(false);
        setLayout(null);
    }

    private void showTank() {
        tankContainer = new JLabel();
        tankContainer.setBounds(50, 50, 70, 70);
        tankContainer.setIcon(newTank.getImage());
        add(tankContainer);
    }
}
