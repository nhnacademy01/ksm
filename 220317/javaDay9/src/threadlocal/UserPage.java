package threadlocal;

class UserPage {
    private DeleteUserInfo deleteUserInfo = new DeleteUserInfo();
    public void editInfo() {
        LoginUserInfo loginUserInfo = ThreadLocalField.local.get();
        System.out.printf("%s 님이 사용자 정보를 변경하려고 합니다.%n",loginUserInfo.name);
        deleteUserInfo.deleteUser();
    }
}
