package view;

import model.UserPrincipal;
import service.user_principal.UserPrincipalServiceIMPL;

import java.util.List;
import java.util.Scanner;

public class ProfileView {
    Scanner scanner = new Scanner(System.in);
    List<UserPrincipal> userPrincipalList = UserPrincipalServiceIMPL.userPrincipalList;
    UserPrincipalServiceIMPL userPrincipalServiceIMPL = new UserPrincipalServiceIMPL();
    public ProfileView(){
        if(userPrincipalList.size()!=0){
            System.out.println("Welcome Profile: "+userPrincipalList.get(0).getName());
            System.out.println("1. LOG OUT");
        } else {
            System.out.println("Please Login! ");

        }
        System.out.println("2. Back Menu");
        int chooseProfile = scanner.nextInt();
        switch (chooseProfile){
            case 1:
                logOut();
                new Main();
            case 2:
                new Main();
        }
        String backMenu = scanner.nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new Main();
        }
    }

    public  void logOut(){
        UserPrincipalServiceIMPL.userPrincipalList.clear();
        userPrincipalServiceIMPL.findAll();
    }
}

