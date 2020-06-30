package client.graphics.tank;

import javax.swing.ImageIcon;

public class Tank {
    private ImageIcon image;
    private int width;
    private int height;

    public Tank() {
        image = new ImageIcon("./src/main/resources/sprites/tankUp.png");
        width = image.getIconWidth();
        height = image.getIconHeight();
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public void setImage(String orientation) {
        switch(orientation) {
            case "up":
                this.image = new ImageIcon("./src/main/resources/sprites/tankUp.png");
                break;
            case "right":
                this.image = new ImageIcon("./src/main/resources/sprites/tankRight.png");
                break;
            case "down":
                this.image = new ImageIcon("./src/main/resources/sprites/tankDown.png");
                break;
            case "left":
                this.image = new ImageIcon("./src/main/resources/sprites/tankLeft.png");
                break;
        }
    }
}
