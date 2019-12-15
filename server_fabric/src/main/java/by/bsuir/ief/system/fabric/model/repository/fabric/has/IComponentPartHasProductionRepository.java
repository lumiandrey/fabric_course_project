package by.bsuir.ief.system.fabric.model.repository.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ComponentPartHasProductionEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IComponentPartHasProductionRepository extends BaseRepository<ComponentPartHasProductionEntity> {

    List<ComponentPartHasProductionEntity> getListByProduction(int idProduction) throws Exception;
}
