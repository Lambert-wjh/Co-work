package Tools;

public class Account {
    private String account;
    private String password;

    public Account(String number, String password) {
        this.account = number;
        this.password = password;
    }

    public void SetPassword() {
    }

    // For superuser to manage passwords.
    public void SetPassword(Account account) {
    }

    public boolean Verify(String password) {
        return this.password.equals(password);
    }
}
