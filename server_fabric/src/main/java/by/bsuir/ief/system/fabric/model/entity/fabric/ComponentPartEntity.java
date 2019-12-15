package by.bsuir.ief.system.fabric.model.entity.fabric;

import com.google.gson.annotations.SerializedName;

public class ComponentPartEntity {
    @SerializedName("id")
    private int id = -1;

    @SerializedName("name")
    private String name = "";

    @SerializedName("cost")
    private double cost = 0.0;

    @SerializedName("idProducer")
    private int idProducer = -1;

    public ComponentPartEntity(int id, String name, double cost, int id_producer) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.idProducer = id_producer;
    }

    public ComponentPartEntity() {
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

    public int getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(int idProducer) {
        this.idProducer = idProducer;
    }
}
