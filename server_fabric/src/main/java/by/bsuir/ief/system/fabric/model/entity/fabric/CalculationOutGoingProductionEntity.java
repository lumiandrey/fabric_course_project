package by.bsuir.ief.system.fabric.model.entity.fabric;

import com.google.gson.annotations.SerializedName;

public class CalculationOutGoingProductionEntity {

    public CalculationOutGoingProductionEntity(ProductionEntity productionEntity, double totalOutGoing) {
        this.productionEntity = productionEntity;
        this.totalOutGoing = totalOutGoing;
    }

    public CalculationOutGoingProductionEntity() {
    }

    @SerializedName("production")
    private ProductionEntity productionEntity;

    @SerializedName("totalOutGoing")
    private double totalOutGoing = 0;

    public ProductionEntity getProductionEntity() {
        return productionEntity;
    }

    public void setProductionEntity(ProductionEntity productionEntity) {
        this.productionEntity = productionEntity;
    }

    public double getTotalOutGoing() {
        return totalOutGoing;
    }

    public void setTotalOutGoing(double totalOutGoing) {
        this.totalOutGoing = totalOutGoing;
    }
}
