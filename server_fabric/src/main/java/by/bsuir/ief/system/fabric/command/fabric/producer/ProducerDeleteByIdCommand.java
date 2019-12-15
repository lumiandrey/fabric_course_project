package by.bsuir.ief.system.fabric.command.fabric.producer;


import by.bsuir.ief.system.fabric.command.GenericDeleteEntityByIdCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;

public class ProducerDeleteByIdCommand extends GenericDeleteEntityByIdCommand<ProducerEntity> {
    @Override
    protected Class<ProducerEntity> getType() {
        return ProducerEntity.class;
    }
}
