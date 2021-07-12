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
            System.out.println("0. Quit");
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
                System.out.println("1. Change password");
                System.out.println("2. Update staff's information");
                System.out.println("3. Create new staff");
                System.out.println("0. Back to previous menu");
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
            } while (selection != 0);
        } else if (first_selection == 2) {
            do {
                System.out.println("1. Create new team");
                System.out.println("2. Intra-team staff transfer");
                System.out.println("3. Inter-team staff transfer");
                System.out.println("0. Back to previous menu");
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
            } while (selection != 0);
        } else if (first_selection == 3) {
            do {
                System.out.println("1. Check projects' information");
                System.out.println("2. Update project's information");
                System.out.println("3. Create new project");
                System.out.println("0. Back to previous menu");
                System.out.print("Enter selection: ");
                selection = input.nextInt();
                switch (selection) {
                    case 1 -> {
                        user.checkProjectInfo();
                    }
                    case 2 -> {
                    }
                    case 3 -> {
                    }
                }
            } while (selection != 0);
        }
    }
}
