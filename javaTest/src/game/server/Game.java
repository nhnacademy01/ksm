package game.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.ConcurrentHashMap;

public class Game {
    private final GameServer.ClientSession clientSession;
    private final Sender sender;
    private boolean endGame = false;
    private int level;

    public Game(ConcurrentHashMap<String, DataOutputStream> clientOutMap, GameServer.ClientSession clientSession) {
        this.clientSession = clientSession;
        this.sender = new Sender(clientOutMap);
        this.level = 1;
    }

    public void start() {
        try {
            if (clientSession.getIn().readUTF().isEmpty()) {
                sender.sendToClient("용사 " + clientSession.getClientId() + "님 던전에 있는 드래곤을 물리쳐주세요!!!\n" +
                        "1. 던전으로 들어간다.\n" +
                        "2. 도망간다. (게임 종료)", clientSession.getOut());
                if (Integer.parseInt(clientSession.getIn().readUTF()) == 1) {
                    startStage(clientSession);
                } else {
                    sender.sendToAll(clientSession.getClientId() + "님이 도망쳤습니다.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("[클라이언트 입력 이슈]");
            System.out.println("message : " + e.getMessage());
        } catch (SocketException e) {
            System.out.println("[클라이언트 강제 종료]");
            System.out.println("message : " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startStage(GameServer.ClientSession clientSession) {
        Warrior warrior = new Warrior(clientSession.getClientId(), sender);
        Stage stage = new Stage(warrior, clientSession, sender);
        while (!endGame) {
            try {
                if (level == 1) {
                    stage.start(level);
                    level++;
                } else if (clientSession.getIn().readUTF().isEmpty()) {
                    stage.start(level);
                    level++;
                }
            } catch (SocketException e) {
                System.out.println("[클라이언트 강제 종료]");
                System.out.println("message : " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            endGame = stage.getRunaway();
            if (level > 3) {
                endGame = true;
            }
        }
    }
}
