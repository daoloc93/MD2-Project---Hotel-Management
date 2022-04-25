package view;

import controller.UserController;
import dto.SignInDTO;

import java.util.Scanner;

public class LoginView {
    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    public LoginView(){
        System.out.println("LOGIN FORM");
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        SignInDTO signInDTO = new SignInDTO(username,password);
        if(userController.login(signInDTO)){
            new Main();
        } else {
            System.err.println("Login failed! Please check username or password! ");
            new LoginView();
        }
    }
}

