package threadlocal;

class ThreadLocalField {
    public static ThreadLocal<LoginUserInfo> local = new ThreadLocal<>();
}