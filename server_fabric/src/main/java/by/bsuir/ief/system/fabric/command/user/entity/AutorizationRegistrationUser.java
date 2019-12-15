package by.bsuir.ief.system.fabric.command.user.entity;

import com.google.gson.annotations.SerializedName;

public class AutorizationRegistrationUser {

    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;

    public AutorizationRegistrationUser() {
    }

    public AutorizationRegistrationUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
