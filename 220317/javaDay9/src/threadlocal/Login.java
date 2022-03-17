package threadlocal;

class Login{
    public static void login(LoginUserInfo loginUser){
        ThreadLocalField.local.set(loginUser);
        System.out.printf("%s 님이 로그인하셨습니다.%n",ThreadLocalField.local.get().name);
        UserPage userPage = new UserPage();
        userPage.editInfo();
        ThreadLocalField.local.remove();
    }
}