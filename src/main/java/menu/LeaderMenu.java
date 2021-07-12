package menu;

import java.util.Scanner;
import entity.staff.Leader;

public class LeaderMenu {
    public void start(Leader user) {
        this.firstMenu(user);
    }

    private void firstMenu(Leader user) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        do {
            MainMenu.clearScreen();
            System.out.println("1. Person related");
            System.out.println("2. Project related");
            System.out.println("3. Team related");
            System.out.println("0. Quit");
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
            } while (selection != 0);
        } else if (first_selection == 2) {
            do {
                System.out.println("1. Check project's information");
                System.out.println("2. Update project's status");
                System.out.println("0. Back to previous menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.checkProjectInfo();
                    }
                    case 2 -> {
                    }
                }
            } while (selection != 0);
        } else if (first_selection == 3) {
            do {
                System.out.println("1. Check members' information");
                System.out.println("0. Back to previous menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                    }
                }
            } while (selection != 0);
        }
    }
}
