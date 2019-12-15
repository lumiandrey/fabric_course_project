package by.bsuir.ief.system.fabric.model;

public enum RoleApp {
    ADMIN(10),
    USER(100);

    private int level;

    RoleApp(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
