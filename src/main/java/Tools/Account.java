package Tools;

public class Account {
    private String account;
    private String password;

    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void SetPassword() {
    }

    public boolean Verify(String password) {
        return this.password.equals(password);
    }
}
