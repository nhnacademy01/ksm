package threadlocal;

class DeleteUserInfo {
    public void deleteUser() {
        LoginUserInfo loginUserInfo = ThreadLocalField.local.get();
        System.out.printf("%s 님이 회원정보를 삭제하려고 합니다.%n",loginUserInfo.name);
    }
}
