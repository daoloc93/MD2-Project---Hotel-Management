package view;

import dto.SignInDTO;
import model.Role;
import model.User;
import model.UserPrincipal;
import service.user.UserServiceIMPL;
import service.user_principal.UserPrincipalServiceIMPL;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class ProfileView {
    Scanner scanner = new Scanner(System.in);
    List<UserPrincipal> userPrincipalList = UserPrincipalServiceIMPL.userPrincipalList;
    UserPrincipalServiceIMPL userPrincipalServiceIMPL = new UserPrincipalServiceIMPL();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    List<User> userList = UserServiceIMPL.userList;

    public ProfileView() {
        if (userPrincipalList.size() != 0) {
            System.out.println("Welcome to Profile: " + userPrincipalList.get(0).getName());

            for (int i = 0; i < userList.size(); i++) {
                if (userPrincipalList.get(0).getId() == userList.get(i).getId()) {
                    String leftAlignFormat = "| %-7d | %-15s | %-15s | %-15s | %-6s | %-3d | %-12s | %-17s | %-19s |%n";

                    System.out.format("+---------+-----------------+-----------------+-----------------+--------+-----+--------------+-------------------+---------------------+%n");
                    System.out.format("| User ID |       Name      |    Username     |     Password    |  Role  | Age | Phone Number |      Address      |        Email        |%n");
                    System.out.format("+---------+-----------------+-----------------+-----------------+--------+-----+--------------+-------------------+---------------------+%n");


                    Set<Role> roleSet = userList.get(i).getRoleSet();
//        System.out.println(roleSet);
                    List<Role> roleList = new ArrayList<>(roleSet);

                    System.out.format(leftAlignFormat, userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUsername(), userList.get(i).getPassword(), roleList.get(0).getName(), userList.get(i).getAge(), userList.get(i).getPhoneNumber(), userList.get(i).getAddress(), userList.get(i).getEmail());

                    System.out.format("+---------+-----------------+-----------------+-----------------+--------+-----+--------------+-------------------+---------------------+%n");
                }
                ;
            }

            System.out.println(userPrincipalList.get(0).getRoleSet());
            System.out.println("1. EDIT LOGIN INFORMATION");
            System.out.println("2. EDIT USER INFORMATION");
            System.out.println("3. BACK TO MENU");
        } else {
            System.out.println("Please Login! ");
        }
        System.out.println("4. LOGOUT");
        int chooseProfile = scanner.nextInt();
        scanner.nextLine();
        switch (chooseProfile) {
            case 1:
                System.out.print("Enter new username: ");
                String newUsername = scanner.nextLine();

                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();

                for (User user : userList) {
                    if (user.getId() == userPrincipalList.get(0).getId()) {
                        user.setUsername(newUsername);
                        user.setPassword(newPassword);
                    }
                }
                userServiceIMPL.findAll();
                System.out.println("LOGIN INFORMATION WAS EDITED SUCCESSFULLY!");
                new Main();
                break;
            case 2:
                editUserInformation();
                break;
            case 3:
                new Main();
                break;
            case 4:
                logOut();
                new Main();
                break;
        }

        String backMenu = scanner.nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new Main();
        }
    }

    public void logOut() {
        UserPrincipalServiceIMPL.userPrincipalList.clear();
        userPrincipalServiceIMPL.findAll();
    }

    public void editUserInformation() {
        UserPrincipal currentUser = userPrincipalList.get(0);
        Set<Role> roleSet = currentUser.getRoleSet();
        List<Role> roleList = new ArrayList<>(roleSet);

        System.out.println("============ EDIT USER INFORMATION ============");

        boolean checkPhoneNumber;
        boolean checkAge;
        boolean checkEmail;
        int age = 0;

        System.out.print("Enter your age (must be >=18): ");
        String ageString;
        while (true) {
            ageString = scanner.nextLine();
            checkAge = Pattern.matches("[0-9_-]{2,2}", ageString);
            if (!(checkAge && (Integer.parseInt(ageString) >= 18))) {
                System.err.println("Age must be 18 or over. Please try again!");
                editUserInformation();
            } else {
                age = Integer.parseInt(ageString);
                break;
            }
        }

        System.out.print("Enter your phone number (10 digits): ");
        String phoneNumber;
        while (true) {
            phoneNumber = scanner.nextLine();
            checkPhoneNumber = Pattern.matches("[0-9_-]{10,10}", phoneNumber);
            if (!checkPhoneNumber) {
                System.err.println("Phone number must contain 10 digits. Please try again!");
                editUserInformation();
            } else {
                break;
            }
        }
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email;
        while (true) {
            email = scanner.nextLine();
            if (!email.endsWith("@gmail.com")) {
                System.err.println("Email must be in \"@gmail.com\" format. Please try again!");
                editUserInformation();
            } else {
                break;
            }
        }


        User newUser = new User(currentUser.getId(), currentUser.getName(), currentUser.getUsername(), currentUser.getPassword(), age, phoneNumber, address, email, currentUser.getRoleSet());

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == newUser.getId()) {
                userList.set(i, newUser);
            }
        }
        System.out.println("USER INFORMATION WAS EDITED SUCCESSFULLY!");
        System.out.print("Press 1 to Back to Menu ");
        int press1 = scanner.nextInt();
        if (press1 == 1) {
            new Main();
        }
    }
}

