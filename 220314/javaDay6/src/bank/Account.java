package bank;

public class Account {
    private String nickName;
    private Money balance;
    private float interestRate;
    private String holder;

    public Account(String nickName, Money balance, float interestRate, String holder) {
        this.nickName = nickName;
        this.balance = balance;
        this.interestRate = interestRate;
        this.holder = holder;
    }

    // 입금
    public void deposit(Money amount) {
        // 제약 조건
        this.balance.add(amount);
    }

    // 출금
    public void withdrawal(Money amount) {
        try {
            this.balance.substract(amount);
        }catch(InvalidMoneyException e){
            System.out.println("[잔액 부족]");
            System.out.println("Message : " + e.getMessage());
        }
    }

    public String getNickName() {
        return this.nickName;
    }

    public long getBalance() {
        return this.balance.amount;
    }

    public String getHolder() {
        return holder;
    }

    private Money calculateInterest() {
        return new Money((long) (this.balance.amount*interestRate),"WON");
    }

    public void payInterest() {
        Money interest = calculateInterest();
        System.out.printf("%s 님의 %s 계좌의 이번달 이자는 %d 입니다.%n",holder,nickName,interest.amount);
        this.balance.add(interest);
    }
}
