package game.server;

import java.util.Random;

public class Warrior extends Enemy implements Runnable {
    private final String id;
    protected Sender sender;
    private int level;
    private int offensePower;
    private Enemy enemy;

    public Warrior(String id, Sender sender) {
        this.id = id;
        this.level = 1;
        this.health = 100;
        this.offensePower = 10;
        super.name = id;
        this.sender = sender;
    }

    public void attack(Monster monster) {
        Random random = new Random();
        monster.damaged(random.nextInt(this.offensePower) + 1);
        sender.sendToClient(this.id + "님의 \uD83D\uDDE1 공격 ," + monster.getName() + "의 현재 체력 : " + monster.getHealth(), this.id);
    }

    @Override
    public void run() {
        while (!stop()) {
            attack((Monster) this.enemy);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("message : " + e.getMessage());
            }
        }
    }

    private boolean stop() {
        return this.enemy.health < 0 || this.health < 0;
    }

    public String getId() {
        return this.id;
    }

    public void setEnemy(Monster monster) {
        this.enemy = monster;
    }

    public void levelUP() {
        this.level = 2;
        this.health = 150;
        this.offensePower = 20;
    }
}
