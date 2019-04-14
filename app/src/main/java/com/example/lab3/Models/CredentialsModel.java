package com.example.lab3.Models;

public class CredentialsModel {
    private String login;
    private String pswd;

    public CredentialsModel() {
    }

    public CredentialsModel(String login, String pswd) {
        this.login = login;
        this.pswd = pswd;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPswd() {
        return this.pswd;
    }
}
