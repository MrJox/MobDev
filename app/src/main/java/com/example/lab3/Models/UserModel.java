package com.example.lab3.Models;

public class UserModel {
    private CredentialsModel credentials;

    public UserModel() {
        this.credentials = new CredentialsModel();
    }

    public UserModel(CredentialsModel credentials) {
        this.credentials = credentials;
    }

    public void setCredentials(CredentialsModel credentials) {
        this.credentials = credentials;
    }

    public CredentialsModel getCredentials() {
        return credentials;
    }
}
