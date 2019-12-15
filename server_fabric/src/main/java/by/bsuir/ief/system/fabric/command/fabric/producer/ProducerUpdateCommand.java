package by.bsuir.ief.system.fabric.command.fabric.producer;

import by.bsuir.ief.system.fabric.command.GenericUpdateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;

public class ProducerUpdateCommand extends GenericUpdateEntityCommand<ProducerEntity> {

    @Override
    public Class<ProducerEntity> getType() {
        return ProducerEntity.class;
    }
}
