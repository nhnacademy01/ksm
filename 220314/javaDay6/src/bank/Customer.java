package bank;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    public List<Account> accounts = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void takeAccountAt(String nickName, Bank bank, Money money) {
        accounts.add(bank.openAccount(this, money, nickName));
    }

    public void depositTo(String accountName, Money money) {
        for (Account account : accounts) {
            if (!account.getNickName().equals(accountName)) continue;
            account.deposit(money);
        }
    }

    public void withdrawalTo(String accountName, Money money) {
        for (Account account : accounts) {
            if (!account.getNickName().equals(accountName)) continue;
            account.withdrawal(money);
        }
    }

    public void checkAccountBalance(Bank bank, String accountName) {
        for (Account account : accounts) {
            if (!account.getNickName().equals(accountName)) continue;
            bank.printAccountBalance(account);
        }
    }

    public String getName() {
        return name;
    }
}
