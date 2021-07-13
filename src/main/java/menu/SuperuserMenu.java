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
            System.out.println("1. 人员管理");
            System.out.println("2. 团队管理");
            System.out.println("3. 项目管理");
            System.out.println("0. 退出");
            System.out.print("输入选择: ");
            selection = input.nextInt();
            secondMenu(user, selection);
        } while (selection != 0);
        System.out.println("感谢您的使用");
    }

    private void secondMenu(Superuser user, int first_selection) {
        Scanner input = new Scanner(System.in);
        int selection = 0;

        if (first_selection == 1) {
            do {
                MainMenu.clearScreen();
                System.out.println("1. 修改账户密码");
                System.out.println("2. 更新员工信息");
                System.out.println("3. 新建员工信息");
                System.out.println("0. 返回上级菜单");
                System.out.print("输入选择: ");
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
                System.out.println("1. 新建团队");
                System.out.println("2. 团队内人员调动");
                System.out.println("3. 团队间人员调动");
                System.out.println("0. 返回上级菜单");
                System.out.print("输入选择: ");
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
                System.out.println("1. 新建项目");
                System.out.println("2. 查看所有项目信息");
                System.out.println("3. 查看指定项目信息");
                System.out.println("4. 更新指定项目信息");
                System.out.println("5. 更新指定项目状态");
                System.out.println("0. 返回上级菜单");
                System.out.print("输入选择: ");
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
