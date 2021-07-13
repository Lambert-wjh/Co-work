package menu;

import java.util.Scanner;
import entity.staff.Superuser;

public class SuperuserMenu {
    public void start(Superuser user) {
        this.firstMenu(user);
    }

    private void firstMenu(Superuser user) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        do {
            MainMenu.clearScreen();
            System.out.println("1. Staff management");
            System.out.println("2. Team management");
            System.out.println("3. Project management");
            System.out.println("0. Exit");
            System.out.print("Enter selection: ");
            selection = input.nextInt();
            secondMenu(user, selection);
        } while (selection != 0);
        System.out.println("Thank you for using");
    }

    private void secondMenu(Superuser user, int first_selection) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        if (first_selection == 1) {
            do {
                MainMenu.clearScreen();
                System.out.println("1. Change account password");
                System.out.println("2. Update staff information");
                System.out.println("3. Create new staff");
                System.out.println("0. Back to the parent menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.changePassword();
                    }
                    case 2 -> {
                    }
                    case 3 -> {
                    }
                }
                if (selection != 0) {
                    MainMenu.postSwitch();
                }
            } while (selection != 0);
        } else if (first_selection == 2) {
            do {
                MainMenu.clearScreen();
                System.out.println("1. Create new team");
                System.out.println("2. Transfer of staff within the team");
                System.out.println("3. Transfer of staff between two team");
                System.out.println("0. Back to the parent menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                    case 3 -> {
                    }
                }
                if (selection != 0) {
                    MainMenu.postSwitch();
                }
            } while (selection != 0);
        } else if (first_selection == 3) {
            do {
                MainMenu.clearScreen();
                System.out.println("1. Create new project");
                System.out.println("2. Check all project information");
                System.out.println("3. Check specified project information");
                System.out.println("4. Update specified project information");
                System.out.println("5. Update specified project status");
                System.out.println("0. Back to the parent menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                    }
                    case 2 -> {
                        user.checkProjectInfo();
                    }
                    case 3 -> {
                    }
                    case 4 -> {
                    }
                    case 5 -> {
                    }
                }
                if (selection != 0) {
                    MainMenu.postSwitch();
                }
            } while (selection != 0);
        }
    }
}
