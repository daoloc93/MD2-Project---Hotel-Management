package view;

import model.UserPrincipal;
import service.user_principal.UserPrincipalServiceIMPL;

import java.util.List;
import java.util.Scanner;

public class LogOutView {

    List<UserPrincipal> userPrincipalList = UserPrincipalServiceIMPL.userPrincipalList;
    UserPrincipalServiceIMPL userPrincipalServiceIMPL = new UserPrincipalServiceIMPL();

    public LogOutView(){
        UserPrincipalServiceIMPL.userPrincipalList.clear();
        userPrincipalServiceIMPL.findAll();
        new Main();
    }
}
