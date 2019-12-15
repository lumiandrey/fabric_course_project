package by.bsuir.ief.system.fabric.command.fabric.outgoing_dynamic;

import by.bsuir.ief.system.fabric.command.GenericReadByIdEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;

public class OutGoingDynamicReadByIdCommand extends GenericReadByIdEntityCommand<OutGoingDynamicEntity> {
    @Override
    public Class<OutGoingDynamicEntity> getType() {
        return OutGoingDynamicEntity.class;
    }
}
