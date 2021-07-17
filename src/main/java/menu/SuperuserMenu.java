/*
 * This file is part of the menu in the HeadhunterMS project. It provides two-level menu for
 * superuser, contains entry points to each function.
 */
package menu;

import java.util.List;
import java.util.Scanner;
import entity.MethodSet;
import entity.staff.Superuser;

public class SuperuserMenu {
    // Superuser's menu start from here.
    public void start(Superuser user) {
        this.firstMenu(user);
    }

    // First level menu
    private void firstMenu(Superuser user) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        do {
            MethodSet.clearScreen();
            List<String> menu = List.of("1. Staff management", "2. Team management",
                    "3. Project management", "0. Exit");
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
    private void secondMenu(Superuser user, int first_selection) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        if (first_selection == 1) {
            do {
                MethodSet.clearScreen();
                List<String> menu =
                        List.of("1. Change account password", "2. Modify staff information",
                                "3. Update all staff sales", "4. Create new staff",
                                "5. Delete staff record", "0. Back to the parent menu");
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
                        user.modifyStaffInfo();
                    }
                    case 3 -> {
                        user.updateSales();
                    }
                    case 4 -> {
                        user.createNewStaff();
                    }
                    case 5 -> {
                        user.deleteStaffRecord();
                    }
                }
                if (selection != 0) {
                    MethodSet.postSwitch();
                }
            } while (selection != 0);
        } else if (first_selection == 2) {
            do {
                MethodSet.clearScreen();
                List<String> menu = List.of("1. Create new team", "2. Change position in the team",
                        "3. Transfer of staff in the team", "4. Delete a team record",
                        "0. Back to the parent menu");
                System.out.print(MethodSet.formatMenu(menu));
                System.out.println();
                System.out.println();
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.createNewTeam();
                    }
                    case 2 -> {
                        user.changeTeamPosition();
                    }
                    case 3 -> {
                        user.transferEmployee();
                    }
                    case 4 -> {
                        user.deleteTeamRecord();
                    }
                }
                if (selection != 0) {
                    MethodSet.postSwitch();
                }
            } while (selection != 0);
        } else if (first_selection == 3) {
            do {
                MethodSet.clearScreen();
                List<String> menu = List.of("1. Create new project",
                        "2. Delete specified project record", "3. Check all project information",
                        "4. Check specified project information",
                        "5. Update specified project information",
                        "6. Update specified project status", "0. Back to the parent menu");
                System.out.print(MethodSet.formatMenu(menu));
                System.out.println();
                System.out.println();
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.createNewProject();
                    }
                    case 2 -> {
                        user.deleteProjectRecord();
                    }
                    case 3 -> {
                        user.checkProjectInfo();
                    }
                    case 4 -> {
                        user.checkTheProjectInfo();
                    }
                    case 5 -> {
                        user.updateTheProjectInfo();
                    }
                    case 6 -> {
                        user.updateTheProjectStatus();
                    }
                }
                if (selection != 0) {
                    MethodSet.postSwitch();
                }
            } while (selection != 0);
        }
    }
}
