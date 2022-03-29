package game.server;

abstract class Enemy {
    protected String name;
    private int health;

    public void damaged(int damage) {
        this.health -= damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
