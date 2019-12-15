package by.bsuir.ief.system.fabric.command.fabric.booking;

import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;

public class BookingReadListCommand extends GenericReadListEntityCommand<BookingEntity> {
    @Override
    public Class<BookingEntity> getType() {
        return BookingEntity.class;
    }
}
