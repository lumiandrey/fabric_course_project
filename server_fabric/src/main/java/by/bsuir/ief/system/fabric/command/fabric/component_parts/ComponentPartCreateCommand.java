package by.bsuir.ief.system.fabric.command.fabric.component_parts;


import by.bsuir.ief.system.fabric.command.GenericCreateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;

public class ComponentPartCreateCommand extends GenericCreateEntityCommand<ComponentPartEntity> {
    @Override
    public Class<ComponentPartEntity> getType() {
        return ComponentPartEntity.class;
    }
}
