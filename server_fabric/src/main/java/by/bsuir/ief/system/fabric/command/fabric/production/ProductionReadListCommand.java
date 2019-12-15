package by.bsuir.ief.system.fabric.command.fabric.production;

import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;

public class ProductionReadListCommand extends GenericReadListEntityCommand<ProductionEntity> {
    @Override
    public Class<ProductionEntity> getType() {
        return ProductionEntity.class;
    }
}
