package by.bsuir.ief.system.fabric.command.fabric.outgoing_dynamic;

import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;

public class OutGoingDynamicReadListCommand extends GenericReadListEntityCommand<OutGoingDynamicEntity> {
    @Override
    public Class<OutGoingDynamicEntity> getType() {
        return OutGoingDynamicEntity.class;
    }
}
