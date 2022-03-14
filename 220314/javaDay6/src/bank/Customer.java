package bank;

public class Customer {
    private final String name;
    public Account account;
    private Money money;

    public Customer(String name, long amount, String currency) {
        this.name = name;
        try {
            this.money = new Money(amount, currency);
        } catch (InvalidMoneyException e) {
            System.out.println("[고객 잔액 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }

    public void takeAccount(Account account) {
        this.account = account;
    }

    public void depositAccount(long amount, String currency) {
        try {
            Money amounts = new Money(amount, currency);
            this.money.substract(amounts);
            account.deposit(amounts);
        } catch (LackMoneyException e) {
            System.out.println("[고객 지갑 이슈]");
            System.out.println("Message : " + e.getMessage());
        } catch (InvalidCurrencyException e) {
            System.out.println("[입금 - 통화 이슈]");
            System.out.println("Message : " + e.getMessage());
        } catch (InvalidMoneyException e) {
            System.out.println("[입금 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }

    public void withdrawalAccount(long amount, String currency) {
        try {
            Money amounts = new Money(amount, currency);
            account.withdrawal(amounts);
            this.money.add(amounts);
        } catch (InvalidCurrencyException e) {
            System.out.println("[출금 - 통화 이슈]");
            System.out.println("Message : " + e.getMessage());
        } catch (InvalidMoneyException e) {
            System.out.println("[출금 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }

    public void checkAccountBalance(Bank bank) {
        try {
            bank.printAccountBalance(account);
        } catch (NullPointerException e) {
            System.out.println(this.name + "의 계좌가 생성되지 않았습니다.");
        }

    }

    public String getName() {
        return name;
    }
}
