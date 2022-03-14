package bank;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    // 은행에 생성된 계좌 목록
    List<Account> accounts = new ArrayList<>();

    //고객 계좌 생성
    Account openAccount(Customer customer, Money initMoney, String nickName) {
        Account account = new Account(nickName, initMoney, 0.027f,customer.getName());
        accounts.add(account);
        return account;
    }

    // 예금 이자 지급(구현 필수!)
    public void payInterestOnAllAccounts() {
        for(Account account : accounts) {
            account.payInterest();
        }
    }

    public void printAccountBalance(Account account) {
        System.out.printf("%s 님의 %s 계좌의 잔액은 %d 원 입니다.%n",account.getHolder(), account.getNickName(), account.getBalance());
    }
}
