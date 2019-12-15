package by.bsuir.ief.system.fabric.command.fabric.component_parts;

import by.bsuir.ief.system.fabric.command.GenericReadByIdEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;

public class ComponentPartReadByIdCommand extends GenericReadByIdEntityCommand<ComponentPartEntity> {
    @Override
    public Class<ComponentPartEntity> getType() {
        return ComponentPartEntity.class;
    }
}
