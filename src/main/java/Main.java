import java.util.Scanner;

import Staff.*;
import Tools.*;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Superuser user = Login();
        switch (user.GetPosition()) {
            case EMPLOYEE:
                menu.EmployeeMenu(user);
                break;
            case LEADER:
                menu.LeaderMenu(user);
                break;
            case SUPERUSER:
                menu.SuperuserMenu(user);
                break;
        }
    }

    public static Superuser Login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Headhunter Management System");
        System.out.print("Account: ");
        String input_account = input.nextLine();
        System.out.print("Password: ");
        String input_password = input.nextLine();
        StaffFactory staff_factory = new StaffFactory();
        Superuser user = staff_factory.CreateSuperuser(input_account);
        if (user.GetAccount().Verify(input_password)) {
            System.out.println("Login success!");
        } else {
            throw new IllegalAccessError("Login failed");
        }
        return user;
    }
}
