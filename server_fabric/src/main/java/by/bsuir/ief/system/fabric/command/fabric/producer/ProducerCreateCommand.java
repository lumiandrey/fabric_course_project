package by.bsuir.ief.system.fabric.command.fabric.producer;


import by.bsuir.ief.system.fabric.command.GenericCreateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;

public class ProducerCreateCommand extends GenericCreateEntityCommand<ProducerEntity> {
    @Override
    public Class<ProducerEntity> getType() {
        return ProducerEntity.class;
    }
}
