package client.message;

import java.awt.*;
import java.util.List;

public class Message {
    private final String clientId;
    private final int posX;
    private final int posY;
    private String orientation;
    private String action;
    private List<String> clientsIds;

    public Message(String clientId, Point dimension, String orientation) {
        this.clientId = clientId;
        this.posX = (int) dimension.getX();
        this.posY = (int) dimension.getY();
    }

    @Override
    public String toString() {
        return "Message {" +
                "clientId='" + clientId + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                ", orientation='" + orientation + '\'' +
                ", action='" + action + '\'' +
                ", clientsIds=" + clientsIds +
                '}';
    }

    public String getClientId() {
        return clientId;
    }

    public int getPosX() {
        return posX;
    }


    public int getPosY() {
        return posY;
    }

    public String getOrientation() {
        return orientation;
    }


    public String getAction() {
        return action;
    }

    public List<String> getClientsIds() {
        return clientsIds;
    }
}