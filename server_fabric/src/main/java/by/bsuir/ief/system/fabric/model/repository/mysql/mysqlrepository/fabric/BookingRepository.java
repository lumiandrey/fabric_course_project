package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.IBookingRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.BookingResultSetWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRepository extends AbstractRepositoryBase<BookingEntity> implements IBookingRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
                SchemaDataBase.Booking.TABLE,
                SchemaDataBase.Booking.NAME,
                SchemaDataBase.Booking.COEF_MULTIPLY,
                SchemaDataBase.Booking.ID_CONSUMER
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s, %s FROM %s",
                SchemaDataBase.Booking.ID,
                SchemaDataBase.Booking.NAME,
                SchemaDataBase.Booking.COEF_MULTIPLY,
                SchemaDataBase.Booking.ID_CONSUMER,
                SchemaDataBase.Booking.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.Booking.ID,
                SchemaDataBase.Booking.NAME,
                SchemaDataBase.Booking.COEF_MULTIPLY,
                SchemaDataBase.Booking.ID_CONSUMER,
                SchemaDataBase.Booking.TABLE,
                SchemaDataBase.Booking.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.Booking.TABLE,
                SchemaDataBase.Booking.NAME,
                SchemaDataBase.Booking.COEF_MULTIPLY,
                SchemaDataBase.Booking.ID_CONSUMER,
                SchemaDataBase.Booking.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.Booking.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.Booking.TABLE, SchemaDataBase.Booking.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.Booking.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<BookingEntity> getResultSetWrapper(ResultSet resultSet) {
        return new BookingResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, BookingEntity insertEntity) throws SQLException {
        stm.setString(1, insertEntity.getName());
        stm.setDouble(2, insertEntity.getCoefMultiply());
        stm.setInt(3, insertEntity.getIdConsumer());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, BookingEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getName());
        stm.setDouble(2, updateEntity.getCoefMultiply());
        stm.setInt(3, updateEntity.getIdConsumer());
        stm.setInt(4, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, BookingEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }
}