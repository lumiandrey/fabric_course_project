package by.bsuir.ief.system.fabric.command.fabric.consumer;


import by.bsuir.ief.system.fabric.command.GenericCreateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;

public class ConsumerCreateCommand extends GenericCreateEntityCommand<ConsumerEntity> {
    @Override
    public Class<ConsumerEntity> getType() {
        return ConsumerEntity.class;
    }
}
