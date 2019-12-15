package by.bsuir.ief.system.fabric.command.fabric.booking;

import by.bsuir.ief.system.fabric.command.GenericReadByIdEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;

public class BookingReadByIdCommand extends GenericReadByIdEntityCommand<BookingEntity> {
    @Override
    public Class<BookingEntity> getType() {
        return BookingEntity.class;
    }
}
