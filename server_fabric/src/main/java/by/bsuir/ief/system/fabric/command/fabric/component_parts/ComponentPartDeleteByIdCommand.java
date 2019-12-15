package by.bsuir.ief.system.fabric.command.fabric.component_parts;


import by.bsuir.ief.system.fabric.command.GenericDeleteEntityByIdCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;

public class ComponentPartDeleteByIdCommand extends GenericDeleteEntityByIdCommand<ComponentPartEntity> {
    @Override
    protected Class<ComponentPartEntity> getType() {
        return ComponentPartEntity.class;
    }
}
