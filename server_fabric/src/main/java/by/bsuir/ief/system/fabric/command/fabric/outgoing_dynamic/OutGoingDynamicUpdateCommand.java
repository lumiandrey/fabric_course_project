package by.bsuir.ief.system.fabric.command.fabric.outgoing_dynamic;

import by.bsuir.ief.system.fabric.command.GenericUpdateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;

public class OutGoingDynamicUpdateCommand extends GenericUpdateEntityCommand<OutGoingDynamicEntity> {

    @Override
    public Class<OutGoingDynamicEntity> getType() {
        return OutGoingDynamicEntity.class;
    }
}
