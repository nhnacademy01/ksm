package bank;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    // 은행에 생성된 계좌 목록
    List<Account> accounts = new ArrayList<>();

    //고객 계좌 생성
    void openAccount(Customer customer, long amount, String currency) {
        try{
            Money initMoney = new Money(amount,currency);
            Account account = new Account(initMoney, 0.027f,customer.getName());
            customer.takeAccount(account);
            accounts.add(account);
        }catch(InvalidMoneyException e){
            System.out.println("[계좌 생성 - 초기 금액 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }

    // [수정] - 추가
    void deposit(Customer customer, long amount, String currency){
        try {
            Money money = new Money(amount, currency);
            customer.depositAccount(money);
        } catch (InvalidMoneyException e) {
            System.out.println("[입금 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }
    // [수정] - 추가
    void withdrawal(Customer customer, long amount, String currency){
        try {
            Money money = new Money(amount, currency);
            customer.withdrawalAccount(money);
        } catch (InvalidMoneyException e) {
            System.out.println("[출금 이슈]");
            System.out.println("Message : " + e.getMessage());
        }
    }

    // 예금 이자 지급
    public void payInterestOnAllAccounts() {
        for(Account account : accounts) {
            account.payInterest();
        }
    }

    // [수정]
    public void printAccountBalance(Customer customer) {
        try {
            System.out.printf("%s 님 계좌의 잔액은 %d 원 입니다.%n",customer.getName(), customer.getAccountBalance());
        } catch (NullPointerException e) {
            System.out.println(customer.getName() + "의 계좌가 생성되지 않았습니다.");
        }
    }
}
