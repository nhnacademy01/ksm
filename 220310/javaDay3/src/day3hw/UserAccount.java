package day3hw;

import java.util.Scanner;

public class UserAccount {
    String companyName;

    public UserAccount(String companyName) {
        this.companyName = companyName;
    }

    public static void main(String[] args) {
        UserAccount userAccount = new UserAccount("nhn academy");
        Register nhnRegister = new Register();

        while (true) {
            System.out.printf("%s 에 오신 것을 환영합니다. 아래에서 메뉴를 선택하세요.%n", userAccount.companyName);
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("0. 종료");
            doSelection(inputSelect(), nhnRegister);
        }
    }

    public static int inputSelect() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextInt();
    }

    public static void doSelection(int select, Register register) {
        switch (select) {
            case 1:
                register.guideSignUp();
                break;
            case 2:
                Login.guideSingIn();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("제공하지 않는 기능입니다.");
        }
    }
}

class Register {
    private static String[][] users;
    private static int[] usersTryCounts;
    private static int allUsers;
    int subscriberNumber;

    public Register() {
        this(100);
    }

    public Register(int subscriberNumber) {
        this.subscriberNumber = 0;
        users = new String[subscriberNumber][4];
        usersTryCounts = new int[subscriberNumber];
    }

    private static boolean checkPwdEqual(int subscriberNumber, String checkPw) {
        if (!users[subscriberNumber][1].equals(checkPw)) {
            System.out.println("if fail > '비번 안맞음' 때문에 회원가입에 실패했습니다.");
            return false;
        }
        return true;
    }

    private static boolean checkOverlapId(int subscriberNumber) {
        for (int i = 0; i < subscriberNumber; i++) {
            if (users[i][0].equals(users[subscriberNumber][0])) {
                System.out.println("if fail > '아이디 중복' 때문에 회원가입에 실패했습니다.");
                return false;
            }
        }
        return true;
    }

    public static boolean userCheck(String inputId, String inputPw) {
        int memberNumber = 0;
        for (int i = 0; i < allUsers; i++) {
            if (users[i][0].equals(inputId)) {
                memberNumber = i;
            }
            if (users[i][0].equals(inputId) && users[i][1].equals(inputPw)) {
                if (usersTryCounts[i] < 4) {
                    System.out.printf("if success > [%s] 님 환영합니다.%n", users[i][2]);
                    System.out.println();
                    usersTryCounts[i] = 0;
                    return true;
                } else break;
            }
        }
        if (lock(memberNumber)) {
            return true;
        }
        System.out.println("if fail > 아이디 혹은 비밀번호가 틀렸습니다.(다시 로그인 해주세요)");
        return false;
    }

    private static boolean lock(int i) {
        usersTryCounts[i]++;
        if (usersTryCounts[i] >= 4) {
            System.out.println("if fail > 4번 이상 로그인 실패");
            System.out.println("if fail > 해당 계정은 잠겼습니다. (최초 메뉴로 이동)");
            return true;
        }
        return false;
    }

    public void guideSignUp() {
        String checkPw;
        Scanner scanner = new Scanner(System.in);

        System.out.println("회원가입을 해주세요.");
        System.out.print("아이디 > ");
        users[this.subscriberNumber][0] = scanner.next();
        System.out.print("비밀번호 > ");
        users[this.subscriberNumber][1] = scanner.next();
        System.out.print("비밀번호 재입력 > ");
        checkPw = scanner.next();
        System.out.print("이름 > ");
        users[this.subscriberNumber][2] = scanner.next();

        if (checkOverlapId(subscriberNumber) && checkPwdEqual(subscriberNumber, checkPw)) {
            System.out.println("if success > 회원가입에 성공했습니다. 이전 메뉴로 돌아갑니다.");
            System.out.println();
            this.subscriberNumber++;
            allUsers = this.subscriberNumber;
        } else {
            guideSignUp();
        }
    }
}

class Login {
    static String inputId, inputPw;

    public static void guideSingIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("로그인 해주세요.");
        System.out.print("아이디 > ");
        inputId = scanner.next();
        System.out.print("비밀번호 > ");
        inputPw = scanner.next();

        if (!Register.userCheck(inputId, inputPw)) {
            guideSingIn();
        }
    }
}