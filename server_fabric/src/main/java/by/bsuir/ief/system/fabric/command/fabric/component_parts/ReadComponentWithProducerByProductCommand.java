package by.bsuir.ief.system.fabric.command.fabric.component_parts;

import by.bsuir.ief.system.fabric.command.BaseIdArgCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.repository.fabric.IComponentPartRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

import java.util.List;

public class ReadComponentWithProducerByProductCommand extends BaseIdArgCommand<List<ComponentPartWithProducerEntity>> {

    @Override
    protected List<ComponentPartWithProducerEntity> get(int id) throws Exception {
        IComponentPartRepository repository = RepositoryManager.getInstance().getComponentPartRepository();

        return repository.readComponentPartWithProducerListByProduction(id);
    }
}
