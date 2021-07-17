/*
 * This file is part of the menu in the HeadhunterMS project. It provides two-level menu for
 * leaders, contains entry points to each function.
 */
package ssdut.cli.headhunterms.menu;

import java.util.List;
import java.util.Scanner;
import ssdut.cli.headhunterms.MethodSet;
import ssdut.cli.headhunterms.staff.Leader;

public class LeaderMenu {
    // Leader's menu start from here.
    public void start(Leader user) {
        this.firstMenu(user);
    }

    // First level menu
    private void firstMenu(Leader user) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        do {
            MethodSet.clearScreen();
            List<String> menu =
                    List.of(
                            "1. Personal related operations",
                            "2. Project-related operations",
                            "3. Team-related operations",
                            "0. Exit");
            System.out.print(MethodSet.formatMenu(menu));
            System.out.println();
            System.out.println();
            System.out.print("Enter selection: ");
            selection = input.nextInt();
            secondMenu(user, selection);
        } while (selection != 0);
        System.out.println("Thank you for using");
    }

    // Second level menu
    private void secondMenu(Leader user, int first_selection) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        if (first_selection == 1) {
            do {
                MethodSet.clearScreen();
                List<String> menu =
                        List.of(
                                "1. Change account password",
                                "2. Check personal information",
                                "0. Back to the parent menu");
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
                        List.of(
                                "1. Check project information",
                                "2. Update the project status",
                                "0. Back to the parent menu");
                System.out.print(MethodSet.formatMenu(menu));
                System.out.println();
                System.out.println();
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
                List<String> menu =
                        List.of("1. Check team information", "0. Back to the parent menu");
                System.out.print(MethodSet.formatMenu(menu));
                System.out.println();
                System.out.println();
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
