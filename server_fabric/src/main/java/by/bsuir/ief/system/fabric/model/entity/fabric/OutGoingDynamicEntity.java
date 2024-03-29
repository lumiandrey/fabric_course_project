package by.bsuir.ief.system.fabric.model.entity.fabric;

public class OutGoingDynamicEntity {
    private int id;
    private String name;
    private double cost;

    public OutGoingDynamicEntity(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public OutGoingDynamicEntity() {
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
