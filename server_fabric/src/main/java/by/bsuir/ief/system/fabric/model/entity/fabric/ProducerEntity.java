package by.bsuir.ief.system.fabric.model.entity.fabric;

public class ProducerEntity {
    private int id;
    private String name;

    public ProducerEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProducerEntity() {
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
