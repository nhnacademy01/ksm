package account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RegisterProgram {
    private final int MAX_USERS = 100;
    // CollectionFramework - 수정부분
    // Account[] users = new Account[MAX_USERS]; // 기존
    List<Account> users = new ArrayList<>(); // 수정

    Account registerInfo; //회원가입시 입력한 내용
    String checkPassword; //비밀번호 재입력 확인
    // CollectionFramework - 수정부분
    //int userCounts = 0; //기존 -> List에서 size()를 사용할 수 있으므로 필요 없다고 판단

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

        // CollectionFramework - 수정부분
        if (!isOverlapId(registerInfo) && isSamePassword(registerInfo)) {
            // 기존
//            users[userCounts] = checkAuthority(registerInfo);
//            userCounts++;
            users.add(checkAuthority(registerInfo)); // 수정
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
        // CollectionFramework - 수정부분
        // 기존
//        for (int i = 0; i < users.size(); i++) {
//            if (users[i] == null) {
//                break;
//            } else if (users[i].id.equals(registerInfo.id)) {
//                System.out.println("if fail > '아이디 중복' 때문에 회원가입에 실패했습니다.");
//                System.out.println("----------------------------------------------------");
//                return true;
//            }
//        }
        // 수정
        for (Account user : this.users) {
            if (user == null) {
                break;
            } else if (user.id.equals(registerInfo.id)) {
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
        // CollectionFramework - 수정부분
        // FIXME: foreach로 바꾸고 isUserId도 수정 가능할듯
        for (int i = 0; i < users.size(); i++) { //userCounts -> users.size()
            if (isUserId(id, i)) {
                if (isUserPassword(password, i) && !isLockAccount(i)) {
                    users.get(i).loginTryCounts = 0; // users[i] -> users.get(i)
                    System.out.printf("if success > [%s] 님 환영합니다.%n", users.get(i).name); // users[i] -> users.get(i)
                    System.out.println("----------------------------------------------------");
                    return true;
                }
                return lock(users.get(i)); // users[i] -> users.get(i)
            }
        }
        printLoginFail();
        return false;
    }

    private boolean isUserId(String id, int i) {
        return users.get(i).id.equals(id);
    }

    private boolean isUserPassword(String password, int i) {
        return users.get(i).password.equals(password);
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
        return users.get(i).loginTryCounts >= 4;
    }

    private void printLoginFail() {
        System.out.println("if fail > 아이디 혹은 비밀번호가 틀렸습니다.(다시 로그인 해주세요)");
        System.out.println("----------------------------------------------------");
    }
}
