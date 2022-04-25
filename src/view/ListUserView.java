package view;

import model.Role;
import model.User;
import service.user.UserServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ListUserView {
    Scanner scanner = new Scanner(System.in);
    List<User> users = UserServiceIMPL.userList;
//

    public ListUserView() {
//        System.out.println(userServiceIMPL.findAll());
//        for (User user: userList) {
//            System.out.println(user.toString());
//        }


        String leftAlignFormat = "| %-7d | %-15s | %-15s | %-15s | %-6s | %-3d | %-12s | %-17s | %-19s |%n";

        System.out.format("+---------+-----------------+-----------------+-----------------+--------+-----+--------------+-------------------+---------------------+%n");
        System.out.format("| User ID |       Name      |    Username     |     Password    |  Role  | Age | Phone Number |      Address      |        Email        |%n");
        System.out.format("+---------+-----------------+-----------------+-----------------+--------+-----+--------------+-------------------+---------------------+%n");

        for (int i = 0; i < users.size(); i++) {
            Set<Role> roleSet = users.get(i).getRoleSet();
//        System.out.println(roleSet);
            List<Role> roleList = new ArrayList<>(roleSet);

            System.out.format(leftAlignFormat, users.get(i).getId(), users.get(i).getName(), users.get(i).getUsername(), users.get(i).getPassword(), roleList.get(0).getName(), users.get(i).getAge(), users.get(i).getPhoneNumber(), users.get(i).getAddress(), users.get(i).getEmail());
        }
        System.out.format("+---------+-----------------+-----------------+-----------------+--------+-----+--------------+-------------------+---------------------+%n");

//        System.out.println(UserServiceIMPL.userList);
        System.out.println("Enter 1 to back to MENU: ");
        String backMenu = scanner.nextLine();
        if (backMenu.equalsIgnoreCase("1")) {
            new Main();
        }
    }
}

