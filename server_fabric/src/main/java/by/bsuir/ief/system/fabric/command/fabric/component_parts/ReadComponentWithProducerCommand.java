package by.bsuir.ief.system.fabric.command.fabric.component_parts;

import by.bsuir.ief.system.fabric.command.BaseCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.repository.fabric.IComponentPartRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

import java.util.List;

public class ReadComponentWithProducerCommand extends BaseCommand<List<ComponentPartWithProducerEntity>> {

    @Override
    protected List<ComponentPartWithProducerEntity> getResponse() throws Exception {
        IComponentPartRepository repository = RepositoryManager.getInstance().getComponentPartRepository();

        return repository.readComponentPartWithProducerList();
    }
}
