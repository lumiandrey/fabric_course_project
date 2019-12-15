package by.bsuir.ief.system.fabric.model.entity.fabric.has;

public class ProductionHasBookingEntity {
    private int id;
    private int idProduction;
    private int idBooking;

    public ProductionHasBookingEntity(int id, int idProduction, int idBooking) {
        this.id = id;
        this.idProduction = idProduction;
        this.idBooking = idBooking;
    }

    public ProductionHasBookingEntity() {
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

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }
}
