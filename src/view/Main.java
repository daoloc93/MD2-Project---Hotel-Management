package view;

import model.Role;
import model.RoleName;
import model.UserPrincipal;
import service.user_principal.UserPrincipalServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public Main() {
        if(UserPrincipalServiceIMPL.userPrincipalList.size()==0){
            new RegisterView();
        }

//        if(userPrincipalList.size()==0){
//            new Main();
//        }
        List<UserPrincipal> userPrincipalList = UserPrincipalServiceIMPL.userPrincipalList;
        boolean checkLogin = false;
        Set<Role> roleSet = userPrincipalList.get(0).getRoleSet();
        List<Role> roleList = new ArrayList<>(roleSet);
        System.out.println("roleList ===> "+roleList.get(0));
        boolean checkAdmin = (roleList.get(0).getName() == RoleName.ADMIN);
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================== MY NAVBAR ==========================");
        if (userPrincipalList.size() != 0) {
            checkLogin = true;
        } else {
            checkLogin = false;
        }
        if (checkLogin) {
            System.out.println("3. SHOW LIST ROLE");
            System.out.println("5. MY PROFILE");
            if (checkAdmin) {
                System.out.println("4. LIST USER");
            }
        } else {
            System.out.println("1. REGISTER");
            System.out.println("2. LOGIN");
        }
        int chooseMenu = scanner.nextInt();
        switch (chooseMenu) {
            case 1:
                new RegisterView();
                break;
            case 2:
                new LoginView();
                break;
            case 3:
                new RoleView().showListRole();
                break;
            case 4:
                if(checkAdmin){
                    new ListUserView();
                    break;
                } else {
                    System.err.println("MÀY ĐÉO CÓ QUYỀN");
                    new Main();
                }

            case 5:
                new ProfileView();
                break;
        }
    }

    public static void main(String[] args) {
        // write your code here
        new Main();
    }
}
