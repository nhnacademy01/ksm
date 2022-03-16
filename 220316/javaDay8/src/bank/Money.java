package bank;

public class Money {
    long amount;
    String currency;

    public Money(long amount, String currency) {
        if (amount < 0L) {   // 돈이 음수인 경우
            throw new InvalidMoneyException("Money is not negative. " + amount);
        }
        if (!isSupportedCurrency(currency)) {   // 지원되지 않는 통화의 경우.
            throw new InvalidMoneyException("Not supported currency. " + currency);
        }
        this.amount = amount;
        this.currency = currency;
    }

    private boolean isSupportedCurrency(String currency) {
        return "WON".equals(currency) || "DOLOR".equals(currency);
    }

    public boolean isSameCurrency(String currency) {
        return this.currency.equals(currency);
    }

    public void add(Money amount) {
        if (isSameCurrency(amount.currency)) {
            this.amount += amount.amount;
        }else throw new InvalidMoneyException("현재 통화와 다른 통화입니다. 현재 통화 : " + this.currency);
    }

    public void substract(Money amount) {
        this.amount -= amount.amount;
        if (this.amount < 0L) {
            this.amount += amount.amount;
            throw new LackMoneyException("잔액이 부족합니다. 현재 잔액 : " + this.amount);
        }
    }
}
