package by.bsuir.ief.system.fabric.model.entity.user;

import java.util.Objects;


public class UserEntity {
    private int idUser;
    private String login;
    private String password;
    private Integer status;
    private RoleApplicationEntity roleApplication;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return idUser == that.idUser &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, login, password, status, roleApplication);
    }

    public RoleApplicationEntity getRoleApplication() {
        return roleApplication;
    }

    public void setRoleApplication(RoleApplicationEntity roleApplication) {
        this.roleApplication = roleApplication;
    }
}
