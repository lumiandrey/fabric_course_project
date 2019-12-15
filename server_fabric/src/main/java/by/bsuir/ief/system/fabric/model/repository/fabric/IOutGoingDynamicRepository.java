package by.bsuir.ief.system.fabric.model.repository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IOutGoingDynamicRepository extends BaseRepository<OutGoingDynamicEntity> {

    List<OutGoingDynamicEntity> readOutGoingByProduction(int idProduction) throws Exception;
}
