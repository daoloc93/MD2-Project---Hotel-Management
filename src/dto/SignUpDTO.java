package dto;

import java.util.Set;

public class SignUpDTO {
    private String name;
    private String username;
    private String password;
    private Set<String> strRole;
    private int age;
    private int phoneNumber;
    private String address;
    private String email;

    public SignUpDTO() {
    }

    public SignUpDTO(String name, String username, String password, Set<String> strRole, int age, int phoneNumber, String address, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.strRole = strRole;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
    public SignUpDTO(String name, String username, String password, Set<String> strRole) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.strRole = strRole;
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

    public Set<String> getStrRole() {
        return strRole;
    }

    public void setStrRole(Set<String> strRole) {
        this.strRole = strRole;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", strRole=" + strRole +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}