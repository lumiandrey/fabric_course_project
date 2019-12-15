package by.bsuir.ief.system.fabric.command.fabric.outgoing_const;


import by.bsuir.ief.system.fabric.command.GenericDeleteEntityByIdCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;

public class OutGoingConstDeleteByIdCommand extends GenericDeleteEntityByIdCommand<OutGoingConstEntity> {
    @Override
    protected Class<OutGoingConstEntity> getType() {
        return OutGoingConstEntity.class;
    }
}
