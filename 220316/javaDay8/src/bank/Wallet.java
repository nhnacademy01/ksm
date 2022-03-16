package bank;

public class Wallet {
    private final Money money;

    public Wallet(Money money) {
        this.money = money;
    }

    public void expenditure(Money money) {
        this.money.substract(money);
    }

    public void income(Money money) {
        this.money.add(money);
    }
}
