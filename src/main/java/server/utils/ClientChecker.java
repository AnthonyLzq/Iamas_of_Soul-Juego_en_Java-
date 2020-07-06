package server.utils;

import server.BattleCityServerThread;

import java.util.Iterator;
import java.util.List;

public class ClientChecker {
    public static void removeFromClientList(String id, List<BattleCityServerThread> clientList, List<String> clientIds) {
        Iterator<BattleCityServerThread> iterator = clientList.iterator();

        while (iterator.hasNext()) {
            BattleCityServerThread client = iterator.next();

            if (client.getClientId().equals(id)) {
                iterator.remove();
                break;
            }
        }

        Iterator<String> iterator1 = clientIds.iterator();

        while (iterator1.hasNext()) {
            String clientId = iterator1.next();

            if (clientId.equals(id)) {
                iterator1.remove();
                break;
            }
        }
    }
}
