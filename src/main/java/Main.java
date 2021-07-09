import java.sql.SQLException;
import java.util.Scanner;

import Staff.*;
import Tools.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Superuser user = Login();
        switch(user.GetPosition()) {
        case EMPLOYEE:
            EmployeeMenu(user);
            break;
        case LEADER:
            LeaderMenu(user);
            break;
        case SUPERUSER:
            SuperuserMenu(user);
            break;
        }
    }

    public static Superuser Login() throws SQLException {
        Scanner input = new Scanner(System.in);
        String input_account = null;
        String input_password = null;
        System.out.println("Welcome to Headhunter Management System");
        System.out.print("Account: ");
        input_account = input.nextLine();
        System.out.print("Password: ");
        input_password = input.nextLine();
        StaffFactory staff_factory = new StaffFactory();
        Superuser user = staff_factory.CreateSuperuser(input_account);
        if (user.GetAccount().Verify(input_password)) {
            System.out.println("Login success!");
        } else {
            throw new IllegalAccessError("Login failed");
        }
        return user;
    }

    public static void EmployeeMenu(Employee employee) {
    }

    public static void LeaderMenu(Leader leader) {
    }

    public static void SuperuserMenu(Superuser superuser) {
    }
}
