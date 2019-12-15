package by.bsuir.ief.system.fabric.model.entity.fabric.has;

public class ComponentPartHasProductionEntity {
    private int id;
    private int idProduction;
    private int idComponentParts;

    public ComponentPartHasProductionEntity(int id, int idProduction, int idComponentParts) {
        this.id = id;
        this.idProduction = idProduction;
        this.idComponentParts = idComponentParts;
    }

    public ComponentPartHasProductionEntity() {
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

    public int getIdComponentParts() {
        return idComponentParts;
    }

    public void setIdComponentParts(int idComponentParts) {
        this.idComponentParts = idComponentParts;
    }
}
