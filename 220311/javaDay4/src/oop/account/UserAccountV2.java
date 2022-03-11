package oop.account;

import java.util.Scanner;

public class UserAccountV2 {
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

class RegisterProgram {
    private final int MAX_USERS = 100;
    Account[] users = new Account[MAX_USERS];

    Account registerInfo; //회원가입시 입력한 내용
    String checkPassword; //비밀번호 재입력 확인
    int userCounts = 0; //가입자수

    public void join() {
        registerInfo = new Account();
        Scanner scanner = new Scanner(System.in);
        System.out.println("회원가입을 해주세요.");
        System.out.print("아이디 > ");
        registerInfo.id = scanner.next();
        System.out.print("비밀번호 > ");
        registerInfo.password = scanner.next();
        System.out.print("비밀번호 재입력 > ");
        checkPassword = scanner.next();
        System.out.print("이름 > ");
        registerInfo.name = scanner.next();
        System.out.print("권한(1:관리자, 2:일반) > ");
        registerInfo.authority = scanner.nextInt();

        if (!isOverlapId(registerInfo) && isSamePassword(registerInfo)) {
            users[userCounts] = checkAuthority(registerInfo);
            userCounts++;
        } else {
            join();
        }
    }

    public Account checkAuthority(Account registerInfo) {
        if (registerInfo.authority == 1) {
            return new Admin(registerInfo);
        } else {
            return new User(registerInfo);
        }
    }

    public boolean isOverlapId(Account registerInfo) {
        for (int i = 0; i < userCounts; i++) {
            if (users[i] == null) {
                break;
            } else if (users[i].id.equals(registerInfo.id)) {
                System.out.println("if fail > '아이디 중복' 때문에 회원가입에 실패했습니다.");
                System.out.println("----------------------------------------------------");
                return true;
            }
        }
        return false;
    }

    public boolean isSamePassword(Account registerInfo) {
        if (!this.checkPassword.equals(registerInfo.password)) {
            System.out.println("if fail > '비번 안맞음' 때문에 회원가입에 실패했습니다.");
            System.out.println("----------------------------------------------------");
            return false;
        }
        return true;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("로그인 해주세요.");
        System.out.print("아이디 > ");
        String id = scanner.next();
        System.out.print("비밀번호 > ");
        String password = scanner.next();

        if (!isLoginSuccess(id, password)) {
            login();
        }
    }

    private boolean isLoginSuccess(String id, String password) {
        return findByUserId(id, password);
    }

    private boolean findByUserId(String id, String password) {
        for (int i = 0; i < userCounts; i++) {
            if (isUserId(id, i)) {
                if (isUserPassword(password, i) && !isLockAccount(i)) {
                    users[i].loginTryCounts = 0;
                    System.out.printf("if success > [%s] 님 환영합니다.%n", users[i].name);
                    System.out.println("----------------------------------------------------");
                    return true;
                }
                return lock(users[i]);
            }
        }
        printLoginFail();
        return false;
    }

    private boolean isUserId(String id, int i) {
        return users[i].id.equals(id);
    }

    private boolean isUserPassword(String password, int i) {
        return users[i].password.equals(password);
    }

    private boolean lock(Account user) {
        if (user.loginTryCounts >= 4) {
            System.out.println("if fail > 4번 이상 로그인 실패");
            System.out.println("if fail > 해당 계정은 잠겼습니다. (최초 메뉴로 이동)");
            System.out.println("----------------------------------------------------");
            return true;
        }
        if (!(user instanceof Admin)) {
            user.loginTryCounts++;
        }
        printLoginFail();
        return false;
    }

    private boolean isLockAccount(int i) {
        return users[i].loginTryCounts >= 4;
    }

    private void printLoginFail(){
        System.out.println("if fail > 아이디 혹은 비밀번호가 틀렸습니다.(다시 로그인 해주세요)");
        System.out.println("----------------------------------------------------");
    }
}

class Account {
    String id;
    String password;
    String name;
    int authority;
    int loginTryCounts;

    Account() {
        this(null, null, null, 2);
    }

    Account(String id, String password, String name, int authority) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.authority = authority;
        this.loginTryCounts = 0;
    }
}

class User extends Account {
    User(Account userInfo) {
        this.id = userInfo.id;
        this.password = userInfo.password;
        this.name = userInfo.name;
        this.authority = userInfo.authority;
        this.loginTryCounts = userInfo.loginTryCounts;
    }
}

class Admin extends Account {
    Admin(Account adminInfo) {
        this.id = adminInfo.id;
        this.password = adminInfo.password;
        this.name = adminInfo.name;
        this.authority = adminInfo.authority;
        this.loginTryCounts = adminInfo.loginTryCounts;
    }
}
