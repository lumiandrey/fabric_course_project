package by.bsuir.ief.system.fabric.command.fabric.production;


import by.bsuir.ief.system.fabric.command.BaseCreateCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ComponentPartHasProductionEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IComponentPartHasProductionRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasOutGoingConstRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasOutGoingDynamicRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

public class ProductionCreateCommand extends BaseCreateCommand<ProductionEntity> {
    @Override
    public Class<ProductionEntity> getType() {
        return ProductionEntity.class;
    }

    @Override
    protected void onCreate(ProductionEntity entity) throws Exception {

        long idProduction = RepositoryManager.getInstance().getProduсtionRepository().create(entity);

        if (idProduction > 0) {

            if (entity.getComponentPartEntities() != null) {
                IComponentPartHasProductionRepository componentPartHasProductionRepository = RepositoryManager.getInstance().getComponentPartHasProductionRepository();

                for (ComponentPartEntity componentPartEntity : entity.getComponentPartEntities()) {
                    componentPartHasProductionRepository.create(new ComponentPartHasProductionEntity(-1, (int) idProduction, componentPartEntity.getId()));
                }
            }

            if (entity.getOutGoingConstEntities() != null) {
                IProductionHasOutGoingConstRepository productionHasOutGoingConstRepository = RepositoryManager.getInstance().getProductionHasOutGoingConstRepository();

                for (OutGoingConstEntity goingConstEntity : entity.getOutGoingConstEntities()) {
                    productionHasOutGoingConstRepository.create(new ProductionHasOutGoingConstEntity(-1, (int) idProduction, goingConstEntity.getId()));
                }
            }

            if (entity.getOutGoingDynamicEntities() != null) {
                IProductionHasOutGoingDynamicRepository productionHasOutGoingConstRepository = RepositoryManager.getInstance().getProductionHasOutGoingDynamicRepository();

                for (OutGoingDynamicEntity goingConstEntity : entity.getOutGoingDynamicEntities()) {
                    productionHasOutGoingConstRepository.create(new ProductionHasOutGoingDynamicEntity(-1, (int) idProduction, goingConstEntity.getId()));
                }
            }
        } else {
            throw new Exception("Не удалось создать!");
        }
    }
}
