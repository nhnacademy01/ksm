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
        super(adminInfo.id, adminInfo.password, adminInfo.name, adminInfo.authority);
    }
}

class User extends Account {
    User(Account userInfo) {
        super(userInfo.id, userInfo.password, userInfo.name, userInfo.authority);
    }
}