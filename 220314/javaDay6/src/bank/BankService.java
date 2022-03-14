package bank;

public class BankService {
    public static void main(String[] args) {
        Bank nhnBank = new Bank();

        Customer semi = new Customer("semi");
        try {
            semi.takeAccountAt("first", nhnBank, new Money(1_000_000, "WON")); //currency가 en 일때랑 amount가 음수일때 예외처리
            semi.takeAccountAt("second", nhnBank, new Money(1_000_000, "WON"));
            semi.takeAccountAt("third", nhnBank, new Money(1_000_000, "WON"));
            semi.takeAccountAt("forth", nhnBank, new Money(1_000_000, "YEN"));
            semi.takeAccountAt("fifth", nhnBank, new Money(-1_000_000, "WON"));
        } catch (InvalidMoneyException e) {
            System.out.println("[초기 금액 오류]");
            System.out.println("Message : " + e.getMessage());
        }

        try {
            //semi.depositTo("first", new Money(-1_000, "WON"));
            //semi.depositTo("first", new Money(1_000, "YEN"));
            semi.depositTo("first", new Money(1_000, "DOLOR"));
        }catch (InvalidMoneyException e){
            System.out.println("[입금 금액 오류]");
            System.out.println("Message : " + e.getMessage());
        }

        try{
            semi.withdrawalTo("second", new Money(1_000_000_0, "WON"));
        }catch (InvalidMoneyException e){
            System.out.println("[출금 금액 오류]");
            System.out.println("Message : " + e.getMessage());
        }

        System.out.println("----------------------------------");
        semi.checkAccountBalance(nhnBank, "first");
        semi.checkAccountBalance(nhnBank, "second");
        System.out.println("----------------------------------");
        nhnBank.payInterestOnAllAccounts();
        System.out.println("----------------------------------");
        semi.checkAccountBalance(nhnBank, "first");
        semi.checkAccountBalance(nhnBank, "second");
        System.out.println("----------------------------------");

    }
}
