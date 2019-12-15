package by.bsuir.ief.system.fabric.command.fabric.outgoing_const;

import by.bsuir.ief.system.fabric.command.BaseIdArgCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.repository.fabric.IOutGoingConstRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

import java.util.List;

public class ReadOutGoingConstByProductCommand extends BaseIdArgCommand<List<OutGoingConstEntity>> {


    @Override
    protected List<OutGoingConstEntity> get(int id) throws Exception {
        IOutGoingConstRepository repository = RepositoryManager.getInstance().getOutGoingConstRepository();

        return repository.readOutGoingByProduction(1);
    }
}
