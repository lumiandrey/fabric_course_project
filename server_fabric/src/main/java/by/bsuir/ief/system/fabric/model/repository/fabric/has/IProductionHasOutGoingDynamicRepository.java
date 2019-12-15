package by.bsuir.ief.system.fabric.model.repository.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IProductionHasOutGoingDynamicRepository extends BaseRepository<ProductionHasOutGoingDynamicEntity> {

    List<ProductionHasOutGoingDynamicEntity> getListByProduction(int idProduction) throws Exception;
}
