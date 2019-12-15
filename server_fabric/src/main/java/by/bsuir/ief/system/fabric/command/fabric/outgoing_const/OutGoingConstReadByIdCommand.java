package by.bsuir.ief.system.fabric.command.fabric.outgoing_const;

import by.bsuir.ief.system.fabric.command.GenericReadByIdEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;

public class OutGoingConstReadByIdCommand extends GenericReadByIdEntityCommand<OutGoingConstEntity> {
    @Override
    public Class<OutGoingConstEntity> getType() {
        return OutGoingConstEntity.class;
    }
}
