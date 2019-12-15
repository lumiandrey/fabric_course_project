package by.bsuir.ief.system.fabric.command.fabric.consumer;

import by.bsuir.ief.system.fabric.command.GenericUpdateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;

public class ConsumerUpdateCommand extends GenericUpdateEntityCommand<ConsumerEntity> {

    @Override
    public Class<ConsumerEntity> getType() {
        return ConsumerEntity.class;
    }
}
