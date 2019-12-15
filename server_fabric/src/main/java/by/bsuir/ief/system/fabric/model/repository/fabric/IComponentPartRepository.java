package by.bsuir.ief.system.fabric.model.repository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IComponentPartRepository extends BaseRepository<ComponentPartEntity> {

    List<ComponentPartWithProducerEntity> readComponentPartWithProducerList() throws Exception;

    List<ComponentPartWithProducerEntity> readComponentPartWithProducerListByProduction(int idProduction) throws Exception;
}
