package by.bsuir.ief.system.fabric.command.fabric.producer;

import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;

public class ProducerReadListCommand extends GenericReadListEntityCommand<ProducerEntity> {
    @Override
    public Class<ProducerEntity> getType() {
        return ProducerEntity.class;
    }
}
