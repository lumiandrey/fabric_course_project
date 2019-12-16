package by.bsuir.ief.system.fabric.model.repository.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasBookingEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IProductionHasBookingRepository extends BaseRepository<ProductionHasBookingEntity> {
    List<ProductionHasBookingEntity> getListByBooking(int idBooking) throws Exception;
}
