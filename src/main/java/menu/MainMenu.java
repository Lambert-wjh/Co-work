/*
 * This file is part of the menu in the HeadhunterMS project. It provides function to the login menu
 * and access to the menu for employees as three different positions.
 */
package menu;

import java.io.Console;
import java.util.Scanner;
import entity.Factory;
import entity.MethodSet;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;

public class MainMenu {
    /*
     * Start all menus from here. Login and then access to the corresponding menu for different
     * positions of employees.
     *
     * @EMPLOYEE -> EmployeeMenu
     *
     * @LEADER -> LeaderMenu
     *
     * @SUPERUSER -> SuperuserMenu
     *
     * @return void
     */
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

    /*
     * The login menu
     *
     * @return an Employee of the corresponding account in the Database.
     */
    private Employee Login() {
        Scanner input = new Scanner(System.in);
        Console console = System.console();

        MethodSet.clearScreen();
        System.out.println("Welcome to use headhunter management system");
        System.out.print("Account: ");
        String input_account = input.nextLine();
        System.out.print("Password: ");
        String input_password = new String(console.readPassword());

        Factory factory = new Factory();
        Employee user = factory.getStaff(input_account);

        if (!user.getAccount().verifyPassword(input_password)) {
            System.err.println("The password is wrong");
            return null;
        }

        return user;
    }
}
