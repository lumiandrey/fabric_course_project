package by.bsuir.ief.system.fabric.command.fabric.consumer;

import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;

public class ConsumerReadListCommand extends GenericReadListEntityCommand<ConsumerEntity> {
    @Override
    public Class<ConsumerEntity> getType() {
        return ConsumerEntity.class;
    }
}
