package by.bsuir.ief.system.fabric.model.repository.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IProductionHasOutGoingConstRepository extends BaseRepository<ProductionHasOutGoingConstEntity> {

    List<ProductionHasOutGoingConstEntity> getListByProduction(int idProduction) throws Exception;
}
