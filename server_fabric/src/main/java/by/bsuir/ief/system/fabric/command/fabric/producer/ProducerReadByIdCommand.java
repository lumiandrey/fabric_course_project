package by.bsuir.ief.system.fabric.command.fabric.producer;

import by.bsuir.ief.system.fabric.command.GenericReadByIdEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;

public class ProducerReadByIdCommand extends GenericReadByIdEntityCommand<ProducerEntity> {
    @Override
    public Class<ProducerEntity> getType() {
        return ProducerEntity.class;
    }
}
