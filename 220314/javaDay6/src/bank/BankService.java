package bank;

public class BankService {
    public static void main(String[] args) {
        Bank nhnBank = new Bank();
        //정상 계좌1
        Customer semi = new Customer("semi", 20_000_000, "WON");
        nhnBank.openAccount(semi,5_000_000,"WON");
        semi.depositAccount(100_000,"WON");
        semi.depositAccount(-100_000,"WON"); // 예외 발생 (잘못된 금액 입력)
        semi.depositAccount(100_000,"YEN"); // 예외 발생 (지원하지 않는 통화 입금)
        semi.depositAccount(100_000,"DOLOR"); // 예외 발생 (계좌의 허용 통화와 다른 통화 입금)
        semi.depositAccount(2_000_000_000,"WON"); // 예외 발생 (고객 지갑 잔액보다 큰 돈을 입금하려는 경우)
        semi.checkAccountBalance(nhnBank);
        semi.withdrawalAccount(50_000,"WON");
        semi.withdrawalAccount(50_000,"YEN"); // 예외 발생 (지원하지 않는 통화 출금)
        semi.withdrawalAccount(100_000,"DOLOR"); // 예외 발생 (계좌의 허용 통화와 다른 통화 출금)
        semi.withdrawalAccount(100_000_000,"WON"); // 예외 발생 (계좌의 잔액보다 많은 금액 출금)
        semi.checkAccountBalance(nhnBank);
        System.out.println("-----------");
        //정상 계좌2
        Customer soon = new Customer("soon", 10_000_000, "DOLOR");
        nhnBank.openAccount(soon,500_000,"DOLOR");
        soon.depositAccount(100_000,"WON"); // 예외 발생 (계좌의 허용 통화와 다른 통화 입금)
        soon.withdrawalAccount(400_000,"DOLOR");
        soon.checkAccountBalance(nhnBank);
        System.out.println("-----------");
        //정상 계좌3
        Customer sem = new Customer("sem", 100_000, "WON");
        nhnBank.openAccount(sem,50_000,"WON");
        sem.checkAccountBalance(nhnBank);
        System.out.println("-----------");
        //예외 발생(엔화로 계좌 생성)
        Customer soo = new Customer("soo", 3_000_000, "WON");
        nhnBank.openAccount(soo,800_000,"YEN");
        System.out.println("-----------");
        //예외 발생(- 음수 돈으로 계좌 생성)
        Customer joon = new Customer("joon", 50_000, "WON");
        nhnBank.openAccount(joon,-10_000,"WON");
        System.out.println("-----------");
        nhnBank.payInterestOnAllAccounts();
        System.out.println("-----------");
        semi.checkAccountBalance(nhnBank);
        soon.checkAccountBalance(nhnBank);
        sem.checkAccountBalance(nhnBank);
        soo.checkAccountBalance(nhnBank);
        joon.checkAccountBalance(nhnBank);
    }
}
