package by.bsuir.ief.system.fabric.model.repository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntityWithConsumer;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

import java.util.List;

public interface IBookingRepository extends BaseRepository<BookingEntity> {
    List<BookingEntityWithConsumer> readBookingEntityWithConsumerList() throws Exception;
}
