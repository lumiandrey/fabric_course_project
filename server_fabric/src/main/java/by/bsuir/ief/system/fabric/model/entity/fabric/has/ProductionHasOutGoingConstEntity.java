package by.bsuir.ief.system.fabric.model.entity.fabric.has;

public class ProductionHasOutGoingConstEntity {
    private int id;
    private int idProduction;
    private int idOutGoingConst;

    public ProductionHasOutGoingConstEntity(int id, int idProduction, int idOutGoingConst) {
        this.id = id;
        this.idProduction = idProduction;
        this.idOutGoingConst = idOutGoingConst;
    }

    public ProductionHasOutGoingConstEntity() {
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

    public int getIdOutGoingConst() {
        return idOutGoingConst;
    }

    public void setIdOutGoingConst(int idOutGoingConst) {
        this.idOutGoingConst = idOutGoingConst;
    }
}
