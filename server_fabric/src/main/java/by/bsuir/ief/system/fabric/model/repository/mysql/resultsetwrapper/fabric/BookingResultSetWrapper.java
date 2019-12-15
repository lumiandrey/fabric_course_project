package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingResultSetWrapper extends BaseResultSetWrapper<BookingEntity> {

    private static final String NAME_TABLE = SchemaDataBase.Booking.TABLE + '.';

    public BookingResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public BookingEntity get() throws SQLException {

        return new BookingEntity(
                rs.getInt(NAME_TABLE + SchemaDataBase.Booking.ID),
                rs.getString(NAME_TABLE + '.' + SchemaDataBase.Booking.NAME),
                rs.getDouble(NAME_TABLE + '.' + SchemaDataBase.Booking.COEF_MULTIPLY),
                rs.getInt(NAME_TABLE + '.' + SchemaDataBase.Booking.ID_CONSUMER)
        );
    }
}
