package Tools;

import java.util.Scanner;

import Staff.Employee;
import Staff.Leader;
import Staff.Superuser;

public class Menu {
    public void EmployeeMenu(Employee employee) {
        Scanner input = new Scanner(System.in);
        int first_selection = 0;
        int second_selection = 0;

        do {
            System.out.println("1. Person related");
            System.out.println("2. Project related");
            System.out.println("0. Quit");

            System.out.print("Enter selection: ");
            first_selection = input.nextInt();
            switch (first_selection) {
                case 1 -> {
                    do {
                        System.out.println("1. Change password");
                        System.out.println("2. Check personal information");
                        System.out.println("0. Quit");

                        System.out.print("Enter selection: ");
                        second_selection = input.nextInt();
                        switch (second_selection) {
                            case 1 -> {
                                employee.ChangePassword();
                            }
                            case 2 -> {
                                employee.CheckEmployeeInfo();
                            }
                        }
                    } while (second_selection != 0);
                }
                case 2 -> {
                    do {
                        System.out.println("1. Check project's information");
                        System.out.println("0. Quit");

                        System.out.print("Enter selection: ");
                        second_selection = input.nextInt();
                        switch (second_selection) {
                            case 1 -> {
                                employee.CheckProjectInfo();
                            }
                        }
                    } while (second_selection != 0);
                }
            }
        } while (first_selection != 0);
        System.out.println("Thank you for using");
    }

    public void LeaderMenu(Leader leader) {
        Scanner input = new Scanner(System.in);
        int first_selection = 0;
        int second_selection = 0;

        do {
            System.out.println("1. Person related");
            System.out.println("2. Project related");
            System.out.println("3. Team related");
            System.out.println("0. Quit");

            System.out.print("Enter selection: ");
            first_selection = input.nextInt();
            switch (first_selection) {
                case 1 -> {
                    do {
                        System.out.println("1. Change password");
                        System.out.println("2. Check personal information");
                        System.out.println("0. Quit");

                        System.out.print("Enter selection: ");
                        second_selection = input.nextInt();
                        switch (second_selection) {
                            case 1 -> {
                                leader.ChangePassword();
                            }
                            case 2 -> {
                                leader.CheckEmployeeInfo();
                            }
                        }
                    } while (second_selection != 0);
                }
                case 2 -> {
                    do {
                        System.out.println("1. Check project's information");
                        System.out.println("2. Update project's status");
                        System.out.println("0. Quit");

                        System.out.print("Enter selection: ");
                        second_selection = input.nextInt();
                        switch (second_selection) {
                            case 1 -> {
                                leader.CheckProjectInfo();
                            }
                            case 2 -> {
                                leader.UpdateProjectStatus();
                            }
                        }
                    } while (second_selection != 0);
                }
                case 3 -> {
                    do {
                        System.out.println("1. Check members' information");
                        System.out.println("0. Quit");

                        System.out.print("Enter selection: ");
                        second_selection = input.nextInt();
                        switch (second_selection) {
                            case 1 -> {
                                leader.CheckMembersInfo();
                            }
                        }
                    } while (second_selection != 0);
                }
            }
        } while (first_selection != 0);
        System.out.println("Thank you for using");
    }

    public void SuperuserMenu(Superuser superuser) {
        System.out.println("Thank you for using");
    }
}
