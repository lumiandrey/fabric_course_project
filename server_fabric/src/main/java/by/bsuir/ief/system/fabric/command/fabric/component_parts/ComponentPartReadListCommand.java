package by.bsuir.ief.system.fabric.command.fabric.component_parts;

import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;

public class ComponentPartReadListCommand extends GenericReadListEntityCommand<ComponentPartEntity> {
    @Override
    public Class<ComponentPartEntity> getType() {
        return ComponentPartEntity.class;
    }
}
