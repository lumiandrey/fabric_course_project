package by.bsuir.ief.system.fabric.command.fabric.outgoing_dynamic;

import by.bsuir.ief.system.fabric.command.BaseIdArgCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.repository.fabric.IOutGoingDynamicRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

import java.util.List;

public class ReadOutGoingDynamictByProductCommand extends BaseIdArgCommand<List<OutGoingDynamicEntity>> {


    @Override
    protected List<OutGoingDynamicEntity> get(int id) throws Exception {
        IOutGoingDynamicRepository repository = RepositoryManager.getInstance().getOutGoingDynamicRepository();

        return repository.readOutGoingByProduction(1);
    }
}
