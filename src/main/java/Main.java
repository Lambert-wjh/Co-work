import Staff.Employee;
import Tools.Account;
import Tools.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Employee user = Account.Login();
        menu.Start(user);
    }
}
