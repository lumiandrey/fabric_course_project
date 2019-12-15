package by.bsuir.ief.system.fabric.command.fabric.component_parts;

import by.bsuir.ief.system.fabric.command.GenericUpdateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;

public class ComponentPartUpdateCommand extends GenericUpdateEntityCommand<ComponentPartEntity> {

    @Override
    public Class<ComponentPartEntity> getType() {
        return ComponentPartEntity.class;
    }
}
