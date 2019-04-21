package com.company.Entities;

public class CredentialsEntity {
    private String login;
    private String pswd;

    public CredentialsEntity(String login, String pswd) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!CredentialsEntity.class.isAssignableFrom(obj.getClass()))
            return false;

        final CredentialsEntity other = (CredentialsEntity) obj;
        return other.login != null && this.login.equals(other.login) &&
                other.pswd != null && this.pswd.equals(other.pswd);
    }
}
