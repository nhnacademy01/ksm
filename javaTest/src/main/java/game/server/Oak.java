package game.server;

class Oak extends Monster {
    protected Oak(Enemy enemy, Sender sender) {
        super("오크", 2, 40, 6, enemy, sender);
    }
}