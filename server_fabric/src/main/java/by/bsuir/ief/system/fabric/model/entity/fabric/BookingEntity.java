package by.bsuir.ief.system.fabric.model.entity.fabric;

import com.google.gson.annotations.SerializedName;

public class BookingEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("id")
    private String name;
    @SerializedName("id")
    private double coefMultiply;
    @SerializedName("id")
    private int idConsumer;

    public BookingEntity(int id, String name, double coefMultiply, int idConsumer) {
        this.id = id;
        this.name = name;
        this.coefMultiply = coefMultiply;
        this.idConsumer = idConsumer;
    }

    public BookingEntity() {
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

    public double getCoefMultiply() {
        return coefMultiply;
    }

    public void setCoefMultiply(double coefMultiply) {
        this.coefMultiply = coefMultiply;
    }

    public int getIdConsumer() {
        return idConsumer;
    }

    public void setIdConsumer(int idConsumer) {
        this.idConsumer = idConsumer;
    }
}
