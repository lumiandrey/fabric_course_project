package by.bsuir.ief.system.fabric.command.fabric.outgoing_dynamic;


import by.bsuir.ief.system.fabric.command.GenericCreateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;

public class OutGoingDynamicCreateCommand extends GenericCreateEntityCommand<OutGoingDynamicEntity> {
    @Override
    public Class<OutGoingDynamicEntity> getType() {
        return OutGoingDynamicEntity.class;
    }
}
