package by.bsuir.ief.system.fabric.model.entity.user;

import java.util.Objects;

public class RoleApplicationEntity {
    private int idRoleApplication;
    private String name;
    private int level;

    public int getIdRoleApplication() {
        return idRoleApplication;
    }

    public void setIdRoleApplication(int idRoleApplication) {
        this.idRoleApplication = idRoleApplication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleApplicationEntity that = (RoleApplicationEntity) o;
        return idRoleApplication == that.idRoleApplication &&
                level == that.level &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idRoleApplication, name, level);
    }
}
