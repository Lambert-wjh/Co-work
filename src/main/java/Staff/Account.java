package Staff;

public class Account {
    private String account;
    private String password;
    private final String DEFAULT_PASSWORD = "123456";

    public Account(String number) {
        this.account = number;
        this.password = DEFAULT_PASSWORD;
    }

    public void SetPassword() {
    }

    // For superuser to manage passwords.
    public void SetPassword(Account account) {
    }
}
