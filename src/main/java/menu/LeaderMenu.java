package menu;

import java.util.Scanner;
import entity.MethodSet;
import entity.staff.Leader;

public class LeaderMenu {
    public void start(Leader user) {
        this.firstMenu(user);
    }

    private void firstMenu(Leader user) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        do {
            MethodSet.clearScreen();
            System.out.println("1. Personal related operations");
            System.out.println("2. Project-related operations");
            System.out.println("3. Team-related operations");
            System.out.println("0. Exit");
            System.out.print("Enter selection: ");
            selection = input.nextInt();
            secondMenu(user, selection);
        } while (selection != 0);
        System.out.println("Thank you for using");
    }

    private void secondMenu(Leader user, int first_selection) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        if (first_selection == 1) {
            do {
                MethodSet.clearScreen();
                System.out.println("1. Change account password");
                System.out.println("2. Check personal information");
                System.out.println("0. Back to the parent menu");
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
                System.out.println("1. Check project information");
                System.out.println("2. Update the project status");
                System.out.println("0. Back to the parent menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.checkProjectInfo();
                    }
                    case 2 -> {
                        user.updateTheProjectStatus();
                    }
                }
                if (selection != 0) {
                    MethodSet.postSwitch();
                }
            } while (selection != 0);
        } else if (first_selection == 3) {
            do {
                MethodSet.clearScreen();
                System.out.println("1. Check team information");
                System.out.println("0. Back to the parent menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.checkTeamInfo();
                    }
                }
                if (selection != 0) {
                    MethodSet.postSwitch();
                }
            } while (selection != 0);
        }
    }
}
