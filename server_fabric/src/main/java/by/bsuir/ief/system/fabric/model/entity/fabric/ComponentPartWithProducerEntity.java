package by.bsuir.ief.system.fabric.model.entity.fabric;

import com.google.gson.annotations.SerializedName;

public class ComponentPartWithProducerEntity extends ComponentPartEntity{

    @SerializedName("producer")
    private ProducerEntity producer;

    public ComponentPartWithProducerEntity(int id, String name, double cost, ProducerEntity producer) {
        super(id, name, cost, -1);
        this.producer = producer;
    }

    public ComponentPartWithProducerEntity(int id, String name, double cost, int id_producer, ProducerEntity producer) {
        super(id, name, cost, id_producer);
        this.producer = producer;
    }

    public ComponentPartWithProducerEntity() {
    }


    public ProducerEntity getProducer() {
        return producer;
    }

    public void setProducer(ProducerEntity producer) {
        this.producer = producer;
    }
}
