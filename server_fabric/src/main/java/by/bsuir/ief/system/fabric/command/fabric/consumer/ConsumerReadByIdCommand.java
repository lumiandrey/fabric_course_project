package by.bsuir.ief.system.fabric.command.fabric.consumer;

import by.bsuir.ief.system.fabric.command.GenericReadByIdEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;

public class ConsumerReadByIdCommand extends GenericReadByIdEntityCommand<ConsumerEntity> {
    @Override
    public Class<ConsumerEntity> getType() {
        return ConsumerEntity.class;
    }
}
