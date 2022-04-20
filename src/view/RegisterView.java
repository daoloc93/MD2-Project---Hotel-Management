package view;

import controller.UserController;
import dto.SignUpDTO;
import service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class RegisterView {
    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    public  RegisterView(){
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();
        System.out.println("Enter username: ");
        String username;
        boolean checkUsername;
        while (true){
            username = scanner.nextLine();
            checkUsername = Pattern.matches("[a-z0-9_-]{6,}",username);
            if(!checkUsername){
                System.err.println("Username Failed! Please try again!");
            }else if(userServiceIMPL.existedByUsername(username)){
                System.err.println("Username is existed! Please try again!");
            } else {
                break;
            }
        }
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your role");
        String role = scanner.nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(name,username,password,strRole);
        userController.register(signUpDTO);
        new LoginView();
    }
}

