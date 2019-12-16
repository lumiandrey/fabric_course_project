package by.bsuir.ief.system.fabric.model.entity.fabric;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingEntityWithConsumer extends BookingEntity {

    @SerializedName("consumer")
    private ConsumerEntity consumerEntity;

    @SerializedName("productions")
    private List<ProductionEntity> productionEntities;

    public BookingEntityWithConsumer(int id, String name, double coefMultiply, int idConsumer) {
        super(id, name, coefMultiply, idConsumer);
    }

    public BookingEntityWithConsumer() {
    }

    public BookingEntityWithConsumer(int id, String name, double coefMultiply, int idConsumer, ConsumerEntity consumerEntity) {
        super(id, name, coefMultiply, idConsumer);
        this.consumerEntity = consumerEntity;
    }

    public ConsumerEntity getConsumerEntity() {
        return consumerEntity;
    }

    public void setConsumerEntity(ConsumerEntity consumerEntity) {
        this.consumerEntity = consumerEntity;
    }

    public List<ProductionEntity> getProductionEntities() {
        return productionEntities;
    }

    public void setProductionEntities(List<ProductionEntity> productionEntities) {
        this.productionEntities = productionEntities;
    }
}
