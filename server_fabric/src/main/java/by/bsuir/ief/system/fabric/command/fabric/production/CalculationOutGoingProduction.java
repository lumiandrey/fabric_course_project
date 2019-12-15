package by.bsuir.ief.system.fabric.command.fabric.production;

import by.bsuir.ief.system.fabric.command.BaseIdArgCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.*;
import by.bsuir.ief.system.fabric.model.repository.fabric.IComponentPartRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.IOutGoingConstRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.IOutGoingDynamicRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.IProduсtionRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

import java.util.List;

public class CalculationOutGoingProduction extends BaseIdArgCommand<CalculationOutGoingProductionEntity> {

    @Override
    protected CalculationOutGoingProductionEntity get(int id) throws Exception {

        IProduсtionRepository produсtionRepository = RepositoryManager.getInstance().getProduсtionRepository();

        ProductionEntity productionEntity = produсtionRepository.read(id);

        IOutGoingConstRepository outGoingConstRepository = RepositoryManager.getInstance().getOutGoingConstRepository();

        List<OutGoingConstEntity> outGoingConstEntities = outGoingConstRepository.readOutGoingByProduction(productionEntity.getId());

        IOutGoingDynamicRepository outGoingDynamicRepository = RepositoryManager.getInstance().getOutGoingDynamicRepository();

        List<OutGoingDynamicEntity> outGoingDynamicEntities = outGoingDynamicRepository.readOutGoingByProduction(productionEntity.getId());

        IComponentPartRepository componentPartRepository = RepositoryManager.getInstance().getComponentPartRepository();

        List<ComponentPartWithProducerEntity> componentPartWithProducerEntityList = componentPartRepository.readComponentPartWithProducerListByProduction(productionEntity.getId());

        productionEntity.setOutGoingDynamicEntities(outGoingDynamicEntities);
        productionEntity.setOutGoingConstEntities(outGoingConstEntities);
        productionEntity.setComponentPartEntities(componentPartWithProducerEntityList);

        double totalOutGoing = 0.0;

        for (OutGoingConstEntity entity: outGoingConstEntities) {
            totalOutGoing += entity.getCost();
        }

        for (OutGoingDynamicEntity entity: outGoingDynamicEntities) {
            totalOutGoing += entity.getCost();
        }

        for (ComponentPartWithProducerEntity entity: componentPartWithProducerEntityList) {
            totalOutGoing += entity.getCost();
        }

        return new CalculationOutGoingProductionEntity(productionEntity, totalOutGoing);
    }
}
