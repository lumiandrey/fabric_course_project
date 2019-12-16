package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntityWithConsumer;
import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingWithCOnsumerResultSetWrapper extends BaseResultSetWrapper<BookingEntityWithConsumer> {

    public BookingWithCOnsumerResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public BookingEntityWithConsumer get() throws SQLException {

        return new BookingEntityWithConsumer(
                rs.getInt(SchemaDataBase.Booking.TABLE + '.' + SchemaDataBase.Booking.ID),
                rs.getString(SchemaDataBase.Booking.TABLE + '.' + SchemaDataBase.Booking.NAME),
                rs.getDouble(SchemaDataBase.Booking.TABLE + '.' + SchemaDataBase.Booking.COEF_MULTIPLY),
                rs.getInt(SchemaDataBase.Consumer.TABLE + '.' + SchemaDataBase.Consumer.ID),
                new ConsumerEntity(
                        rs.getInt(SchemaDataBase.Consumer.TABLE + '.' + SchemaDataBase.Consumer.ID),
                        rs.getString(SchemaDataBase.Consumer.TABLE + '.' + SchemaDataBase.Consumer.NAME)
                )
        );
    }
}
