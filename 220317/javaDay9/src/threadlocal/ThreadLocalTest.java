package threadlocal;

public class ThreadLocalTest {
    /*
    Login.login 에서 ThreadLocal 변수 local에 set() 을 사용하여 할당한 객체를
    UserPage.editInfo()와 DeleteUserInfo.deleteUser() 에서 파라미터로 인자를 전달받지 않았음에도
    ThreadLocal 변수 local을 통해 get() 하여 객체를 사용할 수 있는 것을 확인 할 수 있다.
     */
    public static void main(String[] args) {
        Login.login(new LoginUserInfo("semi","semi","1234"));
    }
}