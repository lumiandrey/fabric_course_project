package by.bsuir.ief.system.fabric.command.fabric.outgoing_const;

import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;

public class OutGoingConstReadListCommand extends GenericReadListEntityCommand<OutGoingConstEntity> {
    @Override
    public Class<OutGoingConstEntity> getType() {
        return OutGoingConstEntity.class;
    }
}
