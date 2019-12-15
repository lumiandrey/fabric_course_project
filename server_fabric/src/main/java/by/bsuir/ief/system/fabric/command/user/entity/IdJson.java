package by.bsuir.ief.system.fabric.command.user.entity;

import com.google.gson.annotations.SerializedName;

public class IdJson {

    @SerializedName("id")
    private int idDelete;

    public int getIdDelete() {
        return idDelete;
    }

    public void setIdDelete(int idDelete) {
        this.idDelete = idDelete;
    }
}
