package game.server;

import java.util.Random;

abstract class Monster extends Enemy implements Runnable {
    protected Sender sender;
    protected final int offensePower;
    private final int level;
    protected Enemy enemy;
    private final Random random = new Random();
    protected static final String HEALTH_TEMPLATE
        = "님의 현재 체력 : ";

    protected Monster(String name, int level, int health,
                      int offensePower, Enemy enemy, Sender sender) {
        super.name = name;
        this.level = level;
        setHealth(health);
        this.offensePower = offensePower;
        this.enemy = enemy;
        this.sender = sender;
    }

    public void attack(Warrior warrior) {
        warrior.damaged(random.nextInt(this.offensePower) + 1);
        sender.sendToClient(this.name + "의 👾 공격," + warrior.getName() + HEALTH_TEMPLATE
            + warrior.getHealth(), warrior.getName());
    }

    @Override
    public void run() {
        while (!stop()) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("message : " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            attack((Warrior) this.enemy);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("message : " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean stop() {
        return this.enemy.getHealth() < 0 || this.getHealth() < 0;
    }

    @Override
    public String getName() {
        return this.name;
    }
}