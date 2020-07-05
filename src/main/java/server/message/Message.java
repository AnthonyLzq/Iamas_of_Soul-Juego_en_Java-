package server.message;

import java.util.List;

public class Message {
    String clientId;
    int posX;
    int posY;
    String orientation;
    String action;
    List<String> clientsIds;

    @Override
    public String toString() {
        return "Message{" +
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
