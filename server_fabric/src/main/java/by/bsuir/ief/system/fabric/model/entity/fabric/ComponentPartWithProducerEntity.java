package by.bsuir.ief.system.fabric.model.entity.fabric;

import com.google.gson.annotations.SerializedName;

public class ComponentPartWithProducerEntity {
    @SerializedName("id")
    private int id = -1;

    @SerializedName("name")
    private String name = "";

    @SerializedName("cost")
    private double cost = 0.0;

    @SerializedName("producer")
    private ProducerEntity producer;

    public ComponentPartWithProducerEntity(int id, String name, double cost, ProducerEntity producer) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.producer = producer;
    }

    public ComponentPartWithProducerEntity() {
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

    public ProducerEntity getIdProducer() {
        return producer;
    }

    public void setIdProducer(ProducerEntity producer) {
        this.producer = producer;
    }
}
