import java.io.Console;
import java.util.Scanner;

import Staff.Employee;
import Tools.Account;
import Tools.Menu;
import Tools.StaffFactory;
import Tools.Enums.Position;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Employee user = Login();
        menu.Start(user);
    }

    public static Employee Login() {
        Scanner input = new Scanner(System.in);
        Console console = System.console();
        System.out.println("Welcome to Headhunter Management System");
        System.out.print("Account: ");
        String input_account = input.nextLine();
        System.out.print("Password: ");
        String input_password = new String(console.readPassword());

        Position position = Account.Verify(input_account, input_password);
        StaffFactory staff_factory = new StaffFactory();
        Employee user = null;
        switch (position) {
            case EMPLOYEE -> {
                user = staff_factory.GetEmployee(input_account);
            }
            case LEADER -> {
                user = staff_factory.GetLeader(input_account);
            }
            case SUPERUSER -> {
                user = staff_factory.GetSuperuser(input_account);
            }
        }

        return user;
    }
}
