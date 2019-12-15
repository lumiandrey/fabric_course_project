package by.bsuir.ief.system.fabric.command.fabric.production;


import by.bsuir.ief.system.fabric.command.GenericDeleteEntityByIdCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;

public class ProductionDeleteByIdCommand extends GenericDeleteEntityByIdCommand<ProductionEntity> {
    @Override
    protected Class<ProductionEntity> getType() {
        return ProductionEntity.class;
    }
}
