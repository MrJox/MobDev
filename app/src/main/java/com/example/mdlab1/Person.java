package com.example.mdlab1;

public class Person {
    private static int _Id;
    private int Id;
    private String Firstname;
    private String Lastname;
    private String Email;
    private String Login;
    private String Password;

    Person(String firstName, String lastName, String email, String login, String pswd) {
        this.Id = ++Person._Id;
        this.Firstname = firstName;
        this.Lastname = lastName;
        this.Email = email;
        this.Login = login;
        this.Password = pswd;
    }

    void setFirstName(String name) {
        this.Firstname = name;
    }

    void setLastName(String name) {
        this.Lastname = name;
    }

    void setEmail(String email) {
        this.Email = email;
    }

    void setLogin(String login) {
        this.Login = login;
    }

    void setPassword(String pswd) {
        this.Password = pswd;
    }

    int getId() {
        return this.Id;
    }

    String getFirstName() {
        return this.Firstname;
    }

    String getLastName() {
        return this.Lastname;
    }

    String getEmail() {
        return this.Email;
    }

    String getLogin() {
        return this.Login;
    }

    String getPassword() {
        return this.Password;
    }
}
