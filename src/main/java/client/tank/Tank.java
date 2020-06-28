package client.tank;

import javax.swing.ImageIcon;

public class Tank {
    private ImageIcon image;

    public Tank() {
        this.image = new ImageIcon("./src/main/resources/sprites/tankUp.png");
    }

    public ImageIcon getImage() {
        return this.image;
    }
}
