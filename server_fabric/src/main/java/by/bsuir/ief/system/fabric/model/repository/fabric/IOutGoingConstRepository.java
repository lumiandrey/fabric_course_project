package by.bsuir.ief.system.fabric.model.repository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IOutGoingConstRepository extends BaseRepository<OutGoingConstEntity> {

    List<OutGoingConstEntity> readOutGoingByProduction(int idProduction) throws Exception;
}
