package game.server;

import java.util.Random;

class Warrior extends Enemy implements Runnable {
    private final String id;
    protected Sender sender;
    // warrior 에서 사용은 안되지만 level 이라는 속성을 넣어주기 위해서 제거하지 않았습니다.
    private int level;
    private int offensePower;
    private Enemy enemy;
    private final Random random = new Random();

    public Warrior(String id, Sender sender) {
        this.id = id;
        this.level = 1;
        setHealth(100);
        this.offensePower = 10;
        super.name = id;
        this.sender = sender;
    }

    public void attack(Monster monster) {
        monster.damaged(random.nextInt(this.offensePower) + 1);
        sender.sendToClient(this.id + "님의 공격 ,"
            + monster.getName() + "의 현재 체력 : " + monster.getHealth(), this.id);
    }

    @Override
    public void run() {
        while (!stop()) {
            attack((Monster) this.enemy);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("message : " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private boolean stop() {
        return this.enemy.getHealth() < 0 || this.getHealth() < 0;
    }

    public String getId() {
        return this.id;
    }

    public void setEnemy(Monster monster) {
        this.enemy = monster;
    }

    public void levelUp() {
        this.level = 2;
        this.setHealth(150);
        this.offensePower = 20;
    }
}
