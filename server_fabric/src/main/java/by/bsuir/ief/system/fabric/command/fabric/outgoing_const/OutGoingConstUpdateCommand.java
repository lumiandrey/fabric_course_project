package by.bsuir.ief.system.fabric.command.fabric.outgoing_const;

import by.bsuir.ief.system.fabric.command.GenericUpdateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;

public class OutGoingConstUpdateCommand extends GenericUpdateEntityCommand<OutGoingConstEntity> {

    @Override
    public Class<OutGoingConstEntity> getType() {
        return OutGoingConstEntity.class;
    }
}
