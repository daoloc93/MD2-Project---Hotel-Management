package view;

import controller.UserController;
import dto.SignUpDTO;
import service.user.UserServiceIMPL;

import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class RegisterView {
    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    public  RegisterView(){
        System.out.println("============ REGISTER FORM ============");
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username;
        boolean checkUsername;
        while (true){
            username = scanner.nextLine();
            checkUsername = Pattern.matches("[a-z0-9_-]{6,}",username);
            if(!checkUsername){
                System.err.print("Username Failed! Please try again!");
                new RegisterView();
            }else if(userServiceIMPL.existedByUsername(username)){
                System.err.print("Username is existed! Please try again!");
                new RegisterView();
            } else {
                break;
            }
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your role: ");
        String role = scanner.nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
//        System.out.print("Enter age: ");
//        int age = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter phone number: ");
//        int phoneNumber = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter address: ");
//        String address = scanner.nextLine();
//        System.out.print("Enter email address: ");
//        String email = scanner.nextLine();

        SignUpDTO signUpDTO = new SignUpDTO(name, username, password, strRole);
//        SignUpDTO signUpDTO = new SignUpDTO(name, username, password, strRole, age, phoneNumber, address, email);
        userController.register(signUpDTO);
        new LoginView();
    }
}

