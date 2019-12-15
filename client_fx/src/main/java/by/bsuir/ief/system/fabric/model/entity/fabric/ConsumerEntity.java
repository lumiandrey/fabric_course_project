package by.bsuir.ief.system.fabric.model.entity.fabric;

public class ConsumerEntity {

    private int id;
    private String name;

    public ConsumerEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ConsumerEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
