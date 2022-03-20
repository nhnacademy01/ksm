package game.server;

import java.util.Random;

abstract class Monster extends Enemy implements Runnable {
    protected Sender sender;
    protected final int offensePower;
    private final int level;
    protected Enemy enemy;

    protected Monster(String name, int level, int health, int offensePower, Enemy enemy, Sender sender) {
        super.name = name;
        this.level = level;
        this.health = health;
        this.offensePower = offensePower;
        this.enemy = enemy;
        this.sender = sender;
    }

    public void attack(Warrior warrior) {
        Random random = new Random();
        warrior.damaged(random.nextInt(this.offensePower) + 1);
        sender.sendToClient(this.name+"의 👾 공격,"+warrior.getName()+"님의 현재 체력 : "+warrior.getHealth(),warrior.getName());
    }

    @Override
    public void run() {
        while (!stop()) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("message : "+e.getMessage());
            }
            attack((Warrior) this.enemy);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("message : "+e.getMessage());
            }
        }
    }

    public boolean stop() {
        return this.enemy.health < 0 || this.health < 0;
    }

    public String getName() {
        return this.name;
    }
}

class Slime extends Monster {
    protected Slime(Enemy enemy, Sender sender) {
        super("슬라임", 1, 30, 4, enemy, sender);
    }
}

class Oak extends Monster {
    protected Oak(Enemy enemy, Sender sender) {
        super("오크", 2, 40, 6, enemy, sender);
    }
}

class Dragon extends Monster {
    protected Dragon(Enemy enemy, Sender sender) {
        super("*보스* 드래곤", 3, 100, 10, enemy, sender);
    }

    @Override
    public void attack(Warrior warrior) {
        Random random = new Random();
        // 10% 확률로 브레스 : 난수를 1에서 100까지 뽑는데 만약 1에서 10사이의 값이 나오면 브레스를 뿜는다.
        int percentage = random.nextInt(100) + 1;
        if (percentage <= 10) {
            warrior.damaged(15);
            sender.sendToClient(
                    "               ___====-_  _-====___\n" +
                    "        _-^########// (    ) \\\\########^-_\n" +
                    "       -##########//  |\\^^/|  \\\\##########-\n" +
                    "     _/##########//   (@::@)   \\\\##########\\_\n" +
                    "    /###########((     \\\\//     ))###########\\\n" +
                    "   -#############\\\\    (oo)    //#############-\n" +
                    "  -###############\\\\  / VV \\  //###############-\n" +
                    "_#/|########/\\######(   /\\   )######/\\########|\\#_\n" +
                    "|/ |#/\\#/\\#/\\/  \\#/\\##\\  |  |  /##/\\#/  \\/\\#/\\#/\\#| \\|\n" +
                    "`  |/  V  V`   V  \\#\\| |  | |/#/  V   '  V  V  \\|  '\n" +
                    "   `   `  `    `   / | |  | | \\ '      '  '   '\n" +
                    "                 __\\ | |  | | /__\n" +
                    "                (vvv(VVV)(VVV)vvv)\n"+
            this.name+"의 브레스 공격,"+warrior.getName()+"님의 현재 체력 : "+warrior.getHealth(),enemy.getName());
        } else {
            warrior.damaged(random.nextInt(this.offensePower) + 1);
            sender.sendToClient(this.name+"의 🐉 공격,"+warrior.getName()+"님의 현재 체력 : "+warrior.getHealth(),enemy.getName());
        }
    }
}