package by.bsuir.ief.system.fabric.command.fabric.outgoing_const;


import by.bsuir.ief.system.fabric.command.GenericCreateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;

public class OutGoingConstCreateCommand extends GenericCreateEntityCommand<OutGoingConstEntity> {
    @Override
    public Class<OutGoingConstEntity> getType() {
        return OutGoingConstEntity.class;
    }
}
