package by.bsuir.ief.system.fabric.command.fabric.production;

import by.bsuir.ief.system.fabric.command.BaseUpdateCommand;
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
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ProductionUpdateCommand extends BaseUpdateCommand<ProductionEntity> {

    @Override
    public Class<ProductionEntity> getType() {
        return ProductionEntity.class;
    }

    @Override
    protected ProductionEntity onUpdate(ProductionEntity entity) throws Exception {

        ProductionEntity updateEntity = RepositoryManager.getInstance().getProduсtionRepository().update(entity);

        if (updateEntity != null) {

            //------------- ComponentPartHasProductionEntity update logic---------------//
            List<ComponentPartEntity> componentPartEntityList = updateEntity.getComponentPartEntities();

            if (componentPartEntityList != null && !componentPartEntityList.isEmpty()) {

                IComponentPartHasProductionRepository componentPartHasProductionRepository = RepositoryManager.getInstance().getComponentPartHasProductionRepository();
                List<ComponentPartHasProductionEntity> currentDataBaseIdPartHasProduction
                        = componentPartHasProductionRepository.getListByProduction(updateEntity.getId());

                Set<Integer> createSetComponentPartHasProductionEntity = new LinkedHashSet<>();
                Set<ComponentPartHasProductionEntity> deleteSetComponentPartHasProductionEntity = new LinkedHashSet<>();

                //---------  Find removed component entity
                for (ComponentPartHasProductionEntity currentDbEntity : currentDataBaseIdPartHasProduction) {
                    int idCurrentDbEntity = currentDbEntity.getIdComponentParts();

                    if (!isContainsComponentPartHasProductEntity(componentPartEntityList, idCurrentDbEntity)) {
                        deleteSetComponentPartHasProductionEntity.add(currentDbEntity);
                    }
                }
                //------- find create entity
                for (ComponentPartEntity componentPartEntity : componentPartEntityList) {
                    int id = componentPartEntity.getId();
                    if (!isContainsComponentPartEntity(currentDataBaseIdPartHasProduction, id)) {
                        createSetComponentPartHasProductionEntity.add(id);
                    }
                }

                //--------- create process (not optimized) for optimization need adding logic for update or delete entity
                //--------- delete entity -----------
                for (ComponentPartHasProductionEntity deleteEntity : deleteSetComponentPartHasProductionEntity) {
                    componentPartHasProductionRepository.delete(deleteEntity.getId());
                }

                for (Integer createId : createSetComponentPartHasProductionEntity) {
                    componentPartHasProductionRepository.create(new ComponentPartHasProductionEntity(-1, updateEntity.getId(), createId));
                }

            }

            //-------------------- logic update out going features --------//
            List<OutGoingConstEntity> outGoingConstEntities = updateEntity.getOutGoingConstEntities();

            if (outGoingConstEntities != null && !outGoingConstEntities.isEmpty()) {
                IProductionHasOutGoingConstRepository componentPartHasProductionRepository = RepositoryManager.getInstance().getProductionHasOutGoingConstRepository();
                List<ProductionHasOutGoingConstEntity> currentOutGoingConstEntitieList
                        = componentPartHasProductionRepository.getListByProduction(updateEntity.getId());

                Set<Integer> createSetComponentPartHasProductionEntity = new LinkedHashSet<>();
                Set<ProductionHasOutGoingConstEntity> deleteSetComponentPartHasProductionEntity = new LinkedHashSet<>();

                //---------  Find removed component entity
                for (ProductionHasOutGoingConstEntity currentDbEntity : currentOutGoingConstEntitieList) {
                    int idCurrentDbEntity = currentDbEntity.getIdOutGoingConst();

                    if (!isContainsProductionHasOutGoingConstEntity(outGoingConstEntities, idCurrentDbEntity)) {
                        deleteSetComponentPartHasProductionEntity.add(currentDbEntity);
                    }
                }
                //------- find create entity
                for (OutGoingConstEntity componentPartEntity : outGoingConstEntities) {
                    int id = componentPartEntity.getId();
                    if (!isContainsOutGoingConstEntity(currentOutGoingConstEntitieList, id)) {
                        createSetComponentPartHasProductionEntity.add(id);
                    }
                }

                //--------- create process (not optimized) for optimization need adding logic for update or delete entity
                //--------- delete entity -----------
                for (ProductionHasOutGoingConstEntity deleteEntity : deleteSetComponentPartHasProductionEntity) {
                    componentPartHasProductionRepository.delete(deleteEntity.getId());
                }

                for (Integer createId : createSetComponentPartHasProductionEntity) {
                    componentPartHasProductionRepository.create(new ProductionHasOutGoingConstEntity(-1, updateEntity.getId(), createId));
                }
            }

            List<OutGoingDynamicEntity> outGoingDynamicEntities = updateEntity.getOutGoingDynamicEntities();

            if (outGoingDynamicEntities != null && !outGoingDynamicEntities.isEmpty()) {
                IProductionHasOutGoingDynamicRepository componentPartHasProductionRepository = RepositoryManager.getInstance().getProductionHasOutGoingDynamicRepository();
                List<ProductionHasOutGoingDynamicEntity> currentOutGoingConstEntitieList
                        = componentPartHasProductionRepository.getListByProduction(updateEntity.getId());

                Set<Integer> createSetComponentPartHasProductionEntity = new LinkedHashSet<>();
                Set<ProductionHasOutGoingDynamicEntity> deleteSetComponentPartHasProductionEntity = new LinkedHashSet<>();

                //---------  Find removed component entity
                for (ProductionHasOutGoingDynamicEntity currentDbEntity : currentOutGoingConstEntitieList) {
                    int idCurrentDbEntity = currentDbEntity.getIdOutGoingDynamic();

                    if (!isContainsProductionHasOutGoingDynamicEntity(outGoingDynamicEntities, idCurrentDbEntity)) {
                        deleteSetComponentPartHasProductionEntity.add(currentDbEntity);
                    }
                }
                //------- find create entity
                for (OutGoingDynamicEntity componentPartEntity : outGoingDynamicEntities) {
                    int id = componentPartEntity.getId();
                    if (!isContainsOutGoingDynamicEntity(currentOutGoingConstEntitieList, id)) {
                        createSetComponentPartHasProductionEntity.add(id);
                    }
                }

                //--------- create process (not optimized) for optimization need adding logic for update or delete entity
                //--------- delete entity -----------
                for (ProductionHasOutGoingDynamicEntity deleteEntity : deleteSetComponentPartHasProductionEntity) {
                    componentPartHasProductionRepository.delete(deleteEntity.getId());
                }

                for (Integer createId : createSetComponentPartHasProductionEntity) {
                    componentPartHasProductionRepository.create(new ProductionHasOutGoingDynamicEntity(-1, updateEntity.getId(), createId));
                }
            }

        } else {
            throw new Exception("Не удалось создать!");
        }

        return updateEntity;
    }

    //------------ component part logic --------------//
    private boolean isContainsComponentPartHasProductEntity(@NotNull List<ComponentPartEntity> componentPartEntityList, int idCurrentDbEntity) {

        for (ComponentPartEntity componentPartEntity : componentPartEntityList) {

            if (componentPartEntity.getId() == idCurrentDbEntity)
                return true;
        }

        return false;
    }

    private boolean isContainsComponentPartEntity(@NotNull List<ComponentPartHasProductionEntity> componentPartEntityList, int IdComponent) {

        for (ComponentPartHasProductionEntity componentPartEntity : componentPartEntityList) {

            if (componentPartEntity.getIdComponentParts() == IdComponent)
                return true;
        }

        return false;
    }

    //--------------- out going cost logic
    private boolean isContainsProductionHasOutGoingConstEntity(@NotNull List<OutGoingConstEntity> componentPartEntityList, int idCurrentDbEntity) {

        for (OutGoingConstEntity componentPartEntity : componentPartEntityList) {

            if (componentPartEntity.getId() == idCurrentDbEntity)
                return true;
        }

        return false;
    }

    private boolean isContainsOutGoingConstEntity(@NotNull List<ProductionHasOutGoingConstEntity> componentPartEntityList, int IdComponent) {

        for (ProductionHasOutGoingConstEntity componentPartEntity : componentPartEntityList) {

            if (componentPartEntity.getIdOutGoingConst() == IdComponent)
                return true;
        }

        return false;
    }

    //--------------- out going cost logic
    private boolean isContainsProductionHasOutGoingDynamicEntity(@NotNull List<OutGoingDynamicEntity> componentPartEntityList, int idCurrentDbEntity) {

        for (OutGoingDynamicEntity componentPartEntity : componentPartEntityList) {

            if (componentPartEntity.getId() == idCurrentDbEntity)
                return true;
        }

        return false;
    }

    private boolean isContainsOutGoingDynamicEntity(@NotNull List<ProductionHasOutGoingDynamicEntity> componentPartEntityList, int IdComponent) {

        for (ProductionHasOutGoingDynamicEntity componentPartEntity : componentPartEntityList) {

            if (componentPartEntity.getIdOutGoingDynamic() == IdComponent)
                return true;
        }

        return false;
    }
}
