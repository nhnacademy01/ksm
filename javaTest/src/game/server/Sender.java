package game.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Sender {
    private final ConcurrentHashMap<String, DataOutputStream> clientOutMap;

    public Sender(ConcurrentHashMap<String, DataOutputStream> clientOutMap) {
        this.clientOutMap = clientOutMap;
    }

    // sendToAll() : 접속자 전체에 메시지 보내기
    void sendToAll(String message) {
        for (DataOutputStream out : clientOutMap.values()) {
            send(message, out);
        }
    }

    // sendToAllExceptOne() : 당사자빼고 전체에 매시지 보내기
    void sendToAllExceptOne(String message, DataOutputStream exceptOut) {
        for (DataOutputStream out : clientOutMap.values()) {
            if (!isExceptClient(out, exceptOut)) {
                send(message, out);
            }
        }
    }

    // sendToClient() : 접속자 특정 한명에게 메시지 보내기
    void sendToClient(String message, DataOutputStream out) {
        send(message, out);
    }

    void sendToClient(String message, String id) {
        send(message, clientOutMap.get(id));
    }

    private boolean isExceptClient(DataOutputStream out, DataOutputStream exceptOut) {
        return out.equals(exceptOut);
    }

    private void send(String message, DataOutputStream out) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            System.out.println("[메시지 전송 실패]");
            System.out.println("message : " + e.getMessage());
        }
    }
}
