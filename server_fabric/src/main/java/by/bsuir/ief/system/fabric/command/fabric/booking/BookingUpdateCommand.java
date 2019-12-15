package by.bsuir.ief.system.fabric.command.fabric.booking;

import by.bsuir.ief.system.fabric.command.GenericUpdateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;

public class BookingUpdateCommand extends GenericUpdateEntityCommand<BookingEntity> {

    @Override
    public Class<BookingEntity> getType() {
        return BookingEntity.class;
    }
}
