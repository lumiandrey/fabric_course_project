package by.bsuir.ief.system.fabric.command.fabric.consumer;


import by.bsuir.ief.system.fabric.command.GenericDeleteEntityByIdCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;

public class ConsumerDeleteByIdCommand extends GenericDeleteEntityByIdCommand<ConsumerEntity> {
    @Override
    protected Class<ConsumerEntity> getType() {
        return ConsumerEntity.class;
    }
}
