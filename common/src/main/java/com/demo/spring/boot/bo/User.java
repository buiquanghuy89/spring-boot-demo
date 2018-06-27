package com.demo.spring.boot.bo;

/**
 * Created by bqhuy on 3/19/2018.
 */
public class User {
    private String userName;
    private String email;
    private String address;
    private String password;
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return new StringBuilder(User.class.getSimpleName())
                .append("= {")
                .append("userName= ").append(userName)
                .append(", email= ").append(email)
                .append(", address= ").append(address)
                .append(", password= ").append(password)
                .append(", role= ").append(role)
                .append("}").toString();
    }
}
