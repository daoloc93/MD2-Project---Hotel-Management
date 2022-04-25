package view;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Role;
import model.RoleName;
import model.User;
import model.UserPrincipal;
import service.role.RoleServiceIMPL;
import service.user.UserServiceIMPL;
import service.user_principal.UserPrincipalServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public Main() {
        Scanner scanner = new Scanner(System.in);
        UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
        List<User> userList = userServiceIMPL.userList;
        RoleServiceIMPL roleServiceIMPL = new RoleServiceIMPL();
        roleServiceIMPL.findAll();
        if (UserPrincipalServiceIMPL.userPrincipalList.size() == 0) {
            System.out.println("WELCOME TO HOTEL MANAGEMENT SERVICE!" +
                    "\nSELECT 1 TO REGISTER OR 2 TO LOGIN:");
            int selection = scanner.nextInt();
            switch (selection) {
                case 1:
                    new RegisterView();
                case 2:
                    new LoginView();
            }
        }

//        if(userPrincipalList.size()==0){
//            new Main();
//        }
        List<UserPrincipal> userPrincipalList = UserPrincipalServiceIMPL.userPrincipalList;
        boolean checkLogin = false;
        Set<Role> roleSet = userPrincipalList.get(0).getRoleSet();
//        System.out.println(roleSet);
        List<Role> roleList = new ArrayList<>(roleSet);
        System.out.println("============ HOTEL MANAGEMENT SERVICE ============");
        System.out.println("Role ===> " + roleList.get(0));
        System.out.println("Welcome, " + userPrincipalList.get(0).getName());
        boolean checkAdmin = (roleList.get(0).getName() == RoleName.ADMIN);

        System.out.println("==================== MY NAVBAR ==========================");
        if (userPrincipalList.size() != 0) {
            checkLogin = true;
        } else {
            checkLogin = false;
        }
        if (checkLogin) {
            System.out.println("1. DISPLAY ACCOUNT INFORMATION");
            System.out.println("2. ROOM MANAGEMENT");
            if (checkAdmin) {
                System.out.println("3. RECEIPT MANAGEMENT");
                System.out.println("4. LIST ALL USERS");
            }
        } else {
            System.out.println("1. REGISTER");
            System.out.println("2. LOGIN");
        }
        System.out.println("5. LOG OUT");
        int chooseMenu = scanner.nextInt();
        switch (chooseMenu) {
            case 1:
                new ProfileView();
                break;
            case 2:
                new RoomView();
                break;
            case 3:
                if (checkAdmin) {
                    new ReceiptView();
                    break;
                } else {
                    System.err.println("UNAUTHORIZED ACCESS, BACK TO MAIN MENU");
                    new Main();
                }
            case 4:
                if (checkAdmin) {
                    new ListUserView();
                    break;
                } else {
                    System.err.println("UNAUTHORIZED ACCESS, BACK TO MAIN MENU");
                    new Main();
                }
            case 5:
                new LogOutView();
                break;
        }

    }


    public static void main(String[] args) {
        // write your code here
        new Main();
    }
}
