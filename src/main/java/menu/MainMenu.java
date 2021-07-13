package menu;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import database.dao.StaffDAO;
import entity.StaffFactory;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;

public class MainMenu {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void postSwitch() {
        Console console = System.console();
        Reader reader = console.reader();

        try {
            System.out.print("Enter any key back to menu...");
            int single_char = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Employee user = Login();
        if (user == null) {
            return;
        }

        switch (user.getPosition()) {
            case EMPLOYEE -> {
                EmployeeMenu menu = new EmployeeMenu();
                menu.start(user);
            }
            case LEADER -> {
                LeaderMenu menu = new LeaderMenu();
                menu.start((Leader) user);
            }
            case SUPERUSER -> {
                SuperuserMenu menu = new SuperuserMenu();
                menu.start((Superuser) user);
            }
        }
    }

    private Employee Login() {
        Scanner input = new Scanner(System.in);
        Console console = System.console();

        MainMenu.clearScreen();
        System.out.println("Welcome to Headhunter Management System");
        System.out.print("Account: ");
        String input_account = input.nextLine();
        System.out.print("Password: ");
        String input_password = new String(console.readPassword());

        StaffDAO staff_dao = new StaffDAO();
        StaffFactory staff_factory = new StaffFactory(staff_dao);
        Employee user = staff_factory.getStaff(input_account);

        if (!user.getAccount().verifyPassword(input_password)) {
            System.err.println("Password is not correct");
            return null;
        }

        return user;
    }
}
