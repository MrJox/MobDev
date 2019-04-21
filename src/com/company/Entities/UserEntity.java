package com.company.Entities;

public class UserEntity {
    private boolean isAuth;
    private CredentialsEntity credentials;
    private BasketEntity basket;

    public UserEntity(CredentialsEntity credentials) {
        this.isAuth = false;
        this.credentials = credentials;
        this.basket = new BasketEntity();
    }

    public void setCredentials(CredentialsEntity credentials) {
        this.credentials = credentials;
    }

    public void setBasket(BasketEntity basket) {
        this.basket = basket;
    }

    public CredentialsEntity getCredentials() {
        return this.credentials;
    }

    public BasketEntity getBasket() {
        return this.basket;
    }

    public boolean authorize() {
//        if (this.isAuth) {
//            return false;
//        }

        // todo: start session timer
        this.isAuth = true;
        return true;
    }

    public boolean logout() {
        if (this.isAuth) {
            // todo: kill session
            this.isAuth = false;
            return true;
        }
        return false;
    }
}
