package game.server;

class Slime extends Monster {
    protected Slime(Enemy enemy, Sender sender) {
        super("슬라임", 1, 30, 4, enemy, sender);
    }
}