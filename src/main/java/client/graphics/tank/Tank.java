package client.graphics.tank;

import javax.swing.ImageIcon;

public class Tank {
    private final String spriteRoute = "./src/main/resources/sprites/player";
    private ImageIcon image;
    private final int width;
    private final int height;

    public Tank() {
        image = new ImageIcon("./src/main/resources/sprites/player1/tankUp.png");
        width = image.getIconWidth();
        height = image.getIconHeight();
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public void setImage(String orientation) {
        if ("up".equals(orientation))
            this.image = new ImageIcon("./src/main/resources/sprites/player1/tankUp.png");
        else if ("right".equals(orientation))
            this.image = new ImageIcon("./src/main/resources/sprites/player1/tankRight.png");
        else if ("down".equals(orientation))
            this.image = new ImageIcon("./src/main/resources/sprites/player1/tankDown.png");
        else if ("left".equals(orientation))
            this.image = new ImageIcon("./src/main/resources/sprites/player1/tankLeft.png");
    }
}
