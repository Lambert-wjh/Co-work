package menu;

import java.util.Scanner;
import entity.staff.Employee;

public class EmployeeMenu {
    public void start(Employee user) {
        this.firstMenu(user);
    }

    private void firstMenu(Employee user) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        do {
            MainMenu.clearScreen();
            System.out.println("1. Person related");
            System.out.println("2. Project related");
            System.out.println("0. Quit");
            System.out.print("Enter selection: ");
            selection = input.nextInt();
            secondMenu(user, selection);
        } while (selection != 0);
        System.out.println("Thank you for using");

    }

    private void secondMenu(Employee user, int first_selection) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        if (first_selection == 1) {
            do {
                MainMenu.clearScreen();
                System.out.println("1. Change password");
                System.out.println("2. Check personal information");
                System.out.println("0. Back to previous menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.changePassword();
                    }
                    case 2 -> {
                        user.checkStaffInfo();
                    }
                }
                if (selection != 0) {
                    MainMenu.postSwitch();
                }
            } while (selection != 0);
        } else if (first_selection == 2) {
            do {
                MainMenu.clearScreen();
                System.out.println("1. Check project's information");
                System.out.println("0. Back to previous menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.checkProjectInfo();
                    }
                }
                if (selection != 0) {
                    MainMenu.postSwitch();
                }
            } while (selection != 0);
        }
    }
}
