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

    // 예금 이자 지급
    public void payInterestOnAllAccounts() {
        for(Account account : accounts) {
            account.payInterest();
        }
    }

    public void printAccountBalance(Account account) {
        System.out.printf("%s 님 계좌의 잔액은 %d 원 입니다.%n",account.getHolder(), account.getBalance());
    }
}
