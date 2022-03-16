package bank;

class Account {
    private Money balance;
    private float interestRate;
    private String holder;

    public Account(Money balance, float interestRate, String holder) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.holder = holder;
    }

    // 입금
    void deposit(Money amount) {
        // 제약 조건
        if (this.balance.isSameCurrency(amount.currency)) {
            this.balance.add(amount);
        } else {
            throw new InvalidCurrencyException("현재 계좌 허용 통화 : " + this.balance.currency + "\n" + "입금하려는 통화 : " + amount.currency);
        }
    }

    // 출금
    void withdrawal(Money amount) {
        // 제약 조건
        if (this.balance.isSameCurrency(amount.currency)) {
            try {
                this.balance.substract(amount);
            } catch (InvalidMoneyException e) {
                System.out.println("[잔액 부족]");
                System.out.println("Message : " + e.getMessage());
            }
        } else {
            throw new InvalidCurrencyException("현재 계좌 허용 통화 : " + this.balance.currency + "\n" + "입금하려는 통화 : " + amount.currency);
        }
    }

    void payInterest() {
        Money interest = calculateInterest();
        System.out.printf("%s 님 계좌의 이번달 이자는 %d 입니다.%n", holder, interest.amount);
        this.balance.add(interest);
    }

    private Money calculateInterest() {
        return new Money((long) (this.balance.amount * interestRate), balance.currency);
    }

    public long getBalance() {
        return this.balance.amount;
    }

    public String getHolder() {
        return holder;
    }
}
