package by.bsuir.ief.system.fabric.model.entity.fabric.has;

public class ProductionHasOutGoingDynamicEntity {
    private int id;
    private int idProduction;
    private int idOutGoingDynamic;

    public ProductionHasOutGoingDynamicEntity(int id, int idProduction, int idOutGoingDynamic) {
        this.id = id;
        this.idProduction = idProduction;
        this.idOutGoingDynamic = idOutGoingDynamic;
    }

    public ProductionHasOutGoingDynamicEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduction() {
        return idProduction;
    }

    public void setIdProduction(int idProduction) {
        this.idProduction = idProduction;
    }

    public int getIdOutGoingDynamic() {
        return idOutGoingDynamic;
    }

    public void setIdOutGoingDynamic(int idOutGoingDynamic) {
        this.idOutGoingDynamic = idOutGoingDynamic;
    }
}
