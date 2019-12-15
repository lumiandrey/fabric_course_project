package by.bsuir.ief.system.fabric.command.fabric.production;

import by.bsuir.ief.system.fabric.command.GenericReadByIdEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;

public class ProductionReadByIdCommand extends GenericReadByIdEntityCommand<ProductionEntity> {
    @Override
    public Class<ProductionEntity> getType() {
        return ProductionEntity.class;
    }
}
