package message;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Message {
    private List<String> clientsIds;
    private String clientId;
    private String orientation;
    private String action;
    private int posX;
    private int posY;
    private int playerIndex;

    public Message() {
    }

    public Message(String action) {
        this.action = action;
    }

    public Message(String clientId, Point dimension, String orientation) {
        this.clientId = clientId;
        this.posX = (int) dimension.getX();
        this.posY = (int) dimension.getY();
        this.orientation = orientation;
    }

    public static Message parser(String message) {
        List<String> attributes = Arrays.asList(message.split("/"));
        Message messageAsObject = new Message();

        messageAsObject.setClientId(attributes.get(0));
        messageAsObject.setPosX(Integer.parseInt(attributes.get(1)));
        messageAsObject.setPosY(Integer.parseInt(attributes.get(2)));
        messageAsObject.setAction(attributes.get(3));
        messageAsObject.setOrientation(attributes.get(4));
        messageAsObject.setPlayerIndex(Integer.parseInt(attributes.get(5)));
        messageAsObject.setClientsIds(
                Arrays.asList(
                        attributes
                                .get(6)
                                .replace("[", "")
                                .replace("]", "")
                                .replace(",", "")
                                .trim()
                                .split(" ")
                )
        );

        return messageAsObject;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    public String toString() {
        return clientId + "/" + posX + "/" + posY + "/" + action + "/" + orientation + "/" + playerIndex + "/" + clientsIds;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getClientsIds() {
        return clientsIds;
    }

    public void setClientsIds(List<String> clientsIds) {
        this.clientsIds = clientsIds;
    }
}