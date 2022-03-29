package game.server;

class Dual {
    private String winner;

    public void warriorVsMonster(Warrior warriorR, Monster monsterR) {
        Thread warrior = new Thread(warriorR);
        Thread monster = new Thread(monsterR);

        warrior.start();
        monster.start();

        try {
            warrior.join();
            monster.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public String whoIsWinner(Warrior warriorR, Monster monsterR) {
        if (warriorR.getHealth() > monsterR.getHealth()) {
            winner = warriorR.getId();
            isMonsterOak(warriorR, monsterR);
        } else if (warriorR.getHealth() < monsterR.getHealth()) {
            winner = monsterR.getName();
        }
        return winner;
    }

    private void isMonsterOak(Warrior warriorR, Monster monsterR) {
        if (monsterR instanceof Oak) {
            warriorR.levelUp();
        }
    }
}