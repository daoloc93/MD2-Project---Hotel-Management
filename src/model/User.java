package model;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String username;
    private String password;
    private Set<Role> roleSet;
    private int age;
    private String phoneNumber;
    private String address;
    private String email;



    public User() {
    }
    public User(int id, String name, String username, String password,  Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roleSet = roleSet;
    }

    public User(int id, String name, String username, String password, int age, String phoneNumber, String address, String email, Set<Role> roleSet) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.roleSet = roleSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleSet=" + roleSet +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}