package by.bsuir.ief.system.fabric.command.fabric.booking;


import by.bsuir.ief.system.fabric.command.GenericDeleteEntityByIdCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;

public class BookingDeleteByIdCommand extends GenericDeleteEntityByIdCommand<BookingEntity> {
    @Override
    protected Class<BookingEntity> getType() {
        return BookingEntity.class;
    }
}
