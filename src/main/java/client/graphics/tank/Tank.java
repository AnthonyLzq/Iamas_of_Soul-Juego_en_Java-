package client.graphics.tank;

import javax.swing.ImageIcon;

public class Tank {
    private String spriteRoute = "./src/main/resources/sprites/player";
    private final int width;
    private final int height;
    private ImageIcon image;

    public Tank(int code) {
        spriteRoute += String.valueOf(++code);
        image = new ImageIcon(spriteRoute + "/tankUp.png");
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
            this.image = new ImageIcon(spriteRoute + "/tankUp.png");
        else if ("right".equals(orientation))
            this.image = new ImageIcon(spriteRoute + "/tankRight.png");
        else if ("down".equals(orientation))
            this.image = new ImageIcon(spriteRoute + "/tankDown.png");
        else if ("left".equals(orientation))
            this.image = new ImageIcon(spriteRoute + "/tankLeft.png");
    }
}
