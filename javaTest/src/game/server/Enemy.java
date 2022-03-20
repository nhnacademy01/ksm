package game.server;

abstract public class Enemy {
    protected String name;
    public int health;

    public void damaged(int damage) {
        this.health -= damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return this.health;
    }
}
