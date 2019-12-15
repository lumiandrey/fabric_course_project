package by.bsuir.ief.system.fabric.command.fabric.outgoing_dynamic;


import by.bsuir.ief.system.fabric.command.GenericDeleteEntityByIdCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;

public class OutGoingDynamicDeleteByIdCommand extends GenericDeleteEntityByIdCommand<OutGoingDynamicEntity> {
    @Override
    protected Class<OutGoingDynamicEntity> getType() {
        return OutGoingDynamicEntity.class;
    }
}
