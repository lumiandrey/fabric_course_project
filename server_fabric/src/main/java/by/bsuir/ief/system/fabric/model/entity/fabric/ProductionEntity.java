package by.bsuir.ief.system.fabric.model.entity.fabric;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProductionEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("describe")
    private String describe;

    @Nullable
    @SerializedName("outGoingConstEntities")
    private List<OutGoingConstEntity> outGoingConstEntities = null;

    @Nullable
    @SerializedName("outGoingDynamicEntities")
    private List<OutGoingDynamicEntity> outGoingDynamicEntities = null;

    @Nullable
    @SerializedName("componentPartEntities")
    private List<ComponentPartEntity> componentPartEntities = null;

    public ProductionEntity(int id, String name, String describe) {
        this.id = id;
        this.name = name;
        this.describe = describe;
    }

    public ProductionEntity() {
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<OutGoingConstEntity> getOutGoingConstEntities() {
        return outGoingConstEntities;
    }

    public void setOutGoingConstEntities(List<OutGoingConstEntity> outGoingConstEntities) {
        this.outGoingConstEntities = outGoingConstEntities;
    }

    public List<OutGoingDynamicEntity> getOutGoingDynamicEntities() {
        return outGoingDynamicEntities;
    }

    public void setOutGoingDynamicEntities(List<OutGoingDynamicEntity> outGoingDynamicEntities) {
        this.outGoingDynamicEntities = outGoingDynamicEntities;
    }

    public List<ComponentPartEntity> getComponentPartEntities() {
        return componentPartEntities;
    }

    public void setComponentPartEntities(List<ComponentPartEntity> componentPartEntities) {
        this.componentPartEntities = componentPartEntities;
    }
}
