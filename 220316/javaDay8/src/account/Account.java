package account;

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
class Admin extends Account {
    Admin(Account adminInfo) {
        this.id = adminInfo.id;
        this.password = adminInfo.password;
        this.name = adminInfo.name;
        this.authority = adminInfo.authority;
        this.loginTryCounts = adminInfo.loginTryCounts;
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