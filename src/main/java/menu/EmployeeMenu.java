package menu;

import java.util.List;
import java.util.Scanner;
import entity.MethodSet;
import entity.staff.Employee;

public class EmployeeMenu {
    public void start(Employee user) {
        this.firstMenu(user);
    }

    private void firstMenu(Employee user) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        do {
            MethodSet.clearScreen();
            List<String> menu = List.of("1. Personal related operations",
                    "2. Project-related operations", "0. Exit");
            System.out.print(MethodSet.formatMenu(menu));
            System.out.println();
            System.out.println();
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
                MethodSet.clearScreen();
                List<String> menu = List.of("1. Change account password",
                        "2. Check personal information", "0. Back to the parent menu");
                System.out.print(MethodSet.formatMenu(menu));
                System.out.println();
                System.out.println();
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
                    MethodSet.postSwitch();
                }
            } while (selection != 0);
        } else if (first_selection == 2) {
            do {
                MethodSet.clearScreen();
                List<String> menu =
                        List.of("1. Check project information", "0. Back to the parent menu");
                System.out.print(MethodSet.formatMenu(menu));
                System.out.println();
                System.out.println();
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.checkProjectInfo();
                    }
                }
                if (selection != 0) {
                    MethodSet.postSwitch();
                }
            } while (selection != 0);
        }
    }
}
