package by.bsuir.ief.system.fabric.command.fabric.booking;


import by.bsuir.ief.system.fabric.command.GenericCreateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;

public class BookingCreateCommand extends GenericCreateEntityCommand<BookingEntity> {
    @Override
    public Class<BookingEntity> getType() {
        return BookingEntity.class;
    }
}
