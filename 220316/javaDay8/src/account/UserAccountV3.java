package account;

import java.util.Scanner;

public class UserAccountV3 {
    public static void main(String[] args) {
        RegisterProgram registerProgram = new RegisterProgram();
        while (true) {
            System.out.println("nhn academy 에 오신 것을 환영합니다. 아래에서 메뉴를 선택하세요.");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("0. 종료");
            select(inputSelect(), registerProgram);
        }
    }

    private static int inputSelect() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextInt();
    }

    private static void select(int select, RegisterProgram registerProgram) {
        switch (select) {
            case 1:
                registerProgram.join();
                break;
            case 2:
                registerProgram.login();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("제공하지 않는 기능입니다.");
        }
    }
}

