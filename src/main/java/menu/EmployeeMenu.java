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
            System.out.println("1. 个人相关");
            System.out.println("2. 项目相关");
            System.out.println("0. 退出");
            System.out.print("输入选择: ");
            selection = input.nextInt();
            secondMenu(user, selection);
        } while (selection != 0);
        System.out.println("感谢您的使用");

    }

    private void secondMenu(Employee user, int first_selection) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        if (first_selection == 1) {
            do {
                MainMenu.clearScreen();
                System.out.println("1. 修改账户密码");
                System.out.println("2. 查看个人信息");
                System.out.println("0. 返回上级菜单");
                System.out.print("输入选择: ");
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
                System.out.println("1. 查看项目信息");
                System.out.println("0. 返回上级菜单");
                System.out.print("输入选择: ");
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
