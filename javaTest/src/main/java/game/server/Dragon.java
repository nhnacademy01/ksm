package game.server;

import java.util.Random;

class Dragon extends Monster {
    private final Random random = new Random();

    protected Dragon(Enemy enemy, Sender sender) {
        super("*보스* 드래곤", 3, 100, 10, enemy, sender);
    }

    @Override
    public void attack(Warrior warrior) {
        // 10% 확률로 브레스 : 난수를 1에서 100까지 뽑는데 만약 1에서 10사이의 값이 나오면 브레스를 뿜는다.
        int percentage = random.nextInt(100) + 1;
        if (percentage <= 10) {
            warrior.damaged(15);
            sender.sendToClient(
                "               ___====-_  _-====___\n"
                    + "        _-^########// (    ) \\\\########^-_\n"
                    + "       -##########//  |\\^^/|  \\\\##########-\n"
                    + "     _/##########//   (@::@)   \\\\##########\\_\n"
                    + "    /###########((     \\\\//     ))###########\\\n"
                    + "   -#############\\\\    (oo)    //#############-\n"
                    + "  -###############\\\\  / VV \\  //###############-\n"
                    + "_#/|########/\\######(   /\\   )######/\\########|\\#_\n"
                    + "|/ |#/\\#/\\#/\\/  \\#/\\##\\  |  |  /##/\\#/  \\/\\#/\\#/\\#| \\|\n"
                    + "`  |/  V  V`   V  \\#\\| |  | |/#/  V   '  V  V  \\|  '\n"
                    + "   `   `  `    `   / | |  | | \\ '      '  '   '\n"
                    + "                 __\\ | |  | | /__\n"
                    + "                (vvv(VVV)(VVV)vvv)\n"
                    + this.name + "의 브레스 공격," + warrior.getName()
                    + HEALTH_TEMPLATE + warrior.getHealth(), enemy.getName());
        } else {
            warrior.damaged(random.nextInt(this.offensePower) + 1);
            sender.sendToClient(this.name + "의 🐉 공격," + warrior.getName()
                + HEALTH_TEMPLATE + warrior.getHealth(), enemy.getName());
        }
    }
}