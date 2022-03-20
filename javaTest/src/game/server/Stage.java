package game.server;

import java.io.IOException;

public class Stage {
    private final GameServer.ClientSession clientSession;
    private final Warrior warrior;
    private final Sender sender;
    private Monster monster;
    private boolean end = false;

    public Stage(Warrior warrior, GameServer.ClientSession clientSession, Sender sender) {
        this.warrior = warrior;
        this.clientSession = clientSession;
        this.sender = sender;
    }

    public void start(int level) {
        setMonster(level);
        startStage();
    }

    private void setMonster(int level) {
        switch (level) {
            case 1:
                monster = new Slime(warrior, sender);
                break;
            case 2:
                monster = new Oak(warrior, sender);
                break;
            case 3:
                monster = new Dragon(warrior, sender);
                break;
        }
    }

    private void startStage() {
        sender.sendToClient("야생의 " + monster.getName() + "이 나타났다.\n" +
                "1. 공격\n" +
                "2. 도망간다. (게임 종료)", clientSession.getOut());
        warrior.setEnemy(monster);
        decideFight();
    }

    private void decideFight() {
        try {
            switch (Integer.parseInt(clientSession.getIn().readUTF())) {
                case 1:
                    fight();
                    break;
                case 2:
                    runAway();
                    break;
                default:
                    end = true;
            }
        } catch (IOException e) {
            System.out.println("message : " + e.getMessage());
        }
    }

    private void fight() {
        Dual dual = new Dual();
        dual.warriorVsMonster(warrior, monster);
        if (dual.whoIsWinner(warrior, monster).equals(warrior.getId())) {
            warriorWin();
        } else {
            monsterWin();
        }
    }

    private void runAway() {
        sender.sendToClient(warrior.getId() + "님이 도망치셨습니다.", clientSession.getOut());
        end = true;
    }

    private void monsterWin() {
        sender.sendToClient(monster.getName() + "이 승리했다.\n" +
                "--게임을 종료합니다.--", clientSession.getOut());
        end = true;
    }

    private void warriorWin() {
        if (monster instanceof Dragon) {
            sender.sendToAll("[외침] 용사 " + warrior.getId() + "가 드래곤을 물리쳤다!");
            end = true;
        } else {
            sender.sendToClient(monster.getName() + "을 물리쳤다.", clientSession.getOut());
            if (monster instanceof Oak) {
                sender.sendToClient("--빠밤!!레벨업 하셨습니다!!빠밤--\n" +
                        "(현재 레벨 : 2 현재 체력 : 150 현재 공격력 : 20)", clientSession.getOut());
            }
            sender.sendToClient("-- 계속 하려면 엔터를 입력해주세요. --", clientSession.getOut());
        }
    }

    public boolean getRunaway() {
        return end;
    }
}
