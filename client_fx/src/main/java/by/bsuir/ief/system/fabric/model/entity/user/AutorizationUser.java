package by.bsuir.ief.system.fabric.model.entity.user;

import com.google.gson.annotations.SerializedName;

public class AutorizationUser {

    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;



    public AutorizationUser() {
    }

    public AutorizationUser(String login, String password) {
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
