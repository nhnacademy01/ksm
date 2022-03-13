package test.oop.mart;

public class Money {
    private int amounts;
    private final String won = "원";

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amounts +
                ", won='" + won + '\'' +
                '}';
    }

    public Money(int amounts) {
        this.amounts = amounts;
    }

    public void minus(Price price){
        amounts-=price.value;
    }

    public int getAmounts() {
        return amounts;
    }
}
