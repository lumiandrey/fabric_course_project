package by.bsuir.ief.system.fabric.model.repository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IProduсtionRepository extends BaseRepository<ProductionEntity> {

    List<ProductionEntity> productionListByBooking(int idBooking) throws Exception;
}
