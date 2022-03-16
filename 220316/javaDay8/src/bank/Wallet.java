package bank;

public class Wallet {
    private final Money money;

    public Wallet(Money money) {
        this.money = money;
    }

    public void expenditure(Money money, Account account) {
        try {
            this.money.substract(money);
            account.deposit(money);
        } catch (LackMoneyException e) {
            System.out.println("[고객 지갑 이슈]");
            System.out.println("Message : " + e.getMessage());
        } catch (InvalidCurrencyException e) {
            System.out.println("[입금 - 통화 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }

    public void income(Money money, Account account) {
        try {
            account.withdrawal(money);
            this.money.add(money);
        } catch (InvalidCurrencyException e) {
            System.out.println("[출금 - 통화 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }
}
