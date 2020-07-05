package client.graphics.bullet;

import javax.swing.ImageIcon;

public class Bullet {
    private ImageIcon image;
    private final int width;
    private final int height;

    public Bullet() {
        image = new ImageIcon("./src/main/resources/sprites/bullet.png");
        width = image.getIconWidth();
        height = image.getIconHeight();
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

}
