package bank;

public class Customer {
    private final String name;
    private Account account; // [수정] 접근 제어자 public -> private 으로 변경
    // private Money money;
    private Wallet wallet; // [수정] Wallet 클래스 생성

    public Customer(String name, long amount, String currency) {
        this.name = name;
        try {
            this.wallet = new Wallet(new Money(amount, currency));
        } catch (InvalidMoneyException e) {
            System.out.println("[고객 잔액 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }

    public void takeAccount(Account account) {
        this.account = account;
    }

    public void depositAccount(Money money) {
        // 기존
//        try {
//            Money amounts = new Money(amount, currency);
//             this.money.substract(amounts);
//             account.deposit(amounts);
//        } catch (LackMoneyException e) {
//            System.out.println("[고객 지갑 이슈]");
//            System.out.println("Message : " + e.getMessage());
//        } catch (InvalidCurrencyException e) {
//            System.out.println("[입금 - 통화 이슈]");
//            System.out.println("Message : " + e.getMessage());
//        } catch (InvalidMoneyException e) {
//            System.out.println("[입금 이슈]");
//            System.out.println("Message : " + e.getMessage());
//        }
        // [수정]
        this.wallet.expenditure(money, this.account);
    }

    public void withdrawalAccount(Money money) {
        // 기존
//        try {
//            Money amounts = new Money(amount, currency);
//            this.account.withdrawal(amounts);
//            this.money.add(amounts);
//        } catch (InvalidCurrencyException e) {
//            System.out.println("[출금 - 통화 이슈]");
//            System.out.println("Message : " + e.getMessage());
//        } catch (InvalidMoneyException e) {
//            System.out.println("[출금 이슈]");
//            System.out.println("Message : " + e.getMessage());
//        }
        // [수정]
        this.wallet.income(money, this.account);
    }


    public long getAccountBalance() {
        // 기존
//        try {
//            bank.printAccountBalance(account);
//        } catch (NullPointerException e) {
//            System.out.println(this.name + "의 계좌가 생성되지 않았습니다.");
//        }
        // [수정]
        return this.account.getBalance();
    }

    public String getName() {
        return name;
    }
}
