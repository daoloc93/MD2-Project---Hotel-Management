package controller;

import dto.SignInDTO;
import dto.SignUpDTO;
import model.Role;
import model.RoleName;
import model.User;
import model.UserPrincipal;
import service.role.RoleServiceIMPL;
import service.user.UserServiceIMPL;
import service.user_principal.UserPrincipalServiceIMPL;

import java.util.HashSet;
import java.util.Set;

public class UserController {
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    RoleServiceIMPL roleServiceIMPL = new RoleServiceIMPL();
    UserPrincipalServiceIMPL userPrincipalServiceIMPL = new UserPrincipalServiceIMPL();
    public void register(SignUpDTO signUpDTO){
        int id;
        if(UserServiceIMPL.userList.size()==0){
            id = 1;
        } else {
            id = UserServiceIMPL.userList.get(UserServiceIMPL.userList.size()-1).getId()+1;
        }
        Set<String> strRole = signUpDTO.getStrRole();
        Set<Role> roleSet = new HashSet<>();
        strRole.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleServiceIMPL.findByName(RoleName.ADMIN);
                    roleSet.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleServiceIMPL.findByName(RoleName.PM);
                    roleSet.add(pmRole);
                    break;
                case "user":
                    Role userRole = roleServiceIMPL.findByName(RoleName.USER);
                    roleSet.add(userRole);
                    break;
            }
        });
        User user = new User(id, signUpDTO.getName(), signUpDTO.getUsername(), signUpDTO.getPassword(),roleSet);
        userServiceIMPL.save(user);
        userServiceIMPL.findAll();
    }
    public boolean login(SignInDTO signInDTO){
        if(userServiceIMPL.checkLogin(signInDTO.getUsername(),signInDTO.getPassword())){
            User user = userServiceIMPL.findByUsername(signInDTO.getUsername());
            UserPrincipal userPrincipal = new UserPrincipal(user.getId(),user.getName(),user.getUsername(),user.getPassword(),user.getRoleSet());
            userPrincipalServiceIMPL.save(userPrincipal);
            userPrincipalServiceIMPL.findAll();
            return true;
        } else {
            return false;
        }
    }
}

