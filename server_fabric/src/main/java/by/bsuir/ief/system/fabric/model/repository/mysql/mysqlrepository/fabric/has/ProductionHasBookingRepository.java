package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasBookingEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasBookingRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.has.ProductionHasBookingResultSetWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductionHasBookingRepository extends AbstractRepositoryBase<ProductionHasBookingEntity> implements IProductionHasBookingRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemaDataBase.ProductionHasBooking.TABLE,
                SchemaDataBase.ProductionHasBooking.ID_BOOKING,
                SchemaDataBase.ProductionHasBooking.ID_PRODUCTION
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s FROM %s",
                SchemaDataBase.ProductionHasBooking.ID,
                SchemaDataBase.ProductionHasBooking.ID_BOOKING,
                SchemaDataBase.ProductionHasBooking.ID_PRODUCTION,
                SchemaDataBase.ProductionHasBooking.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.ProductionHasBooking.ID,
                SchemaDataBase.ProductionHasBooking.ID_BOOKING,
                SchemaDataBase.ProductionHasBooking.ID_PRODUCTION,
                SchemaDataBase.ProductionHasBooking.TABLE,
                SchemaDataBase.ProductionHasBooking.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.ProductionHasBooking.TABLE,
                SchemaDataBase.ProductionHasBooking.ID_BOOKING,
                SchemaDataBase.ProductionHasBooking.ID_PRODUCTION,
                SchemaDataBase.ProductionHasBooking.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.ProductionHasBooking.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.ProductionHasBooking.TABLE, SchemaDataBase.ProductionHasBooking.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.ProductionHasBooking.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<ProductionHasBookingEntity> getResultSetWrapper(ResultSet resultSet) {
        return new ProductionHasBookingResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, ProductionHasBookingEntity insertEntity) throws SQLException {
        stm.setInt(1, insertEntity.getIdBooking());
        stm.setInt(2, insertEntity.getIdProduction());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, ProductionHasBookingEntity updateEntity) throws SQLException {

        stm.setInt(1, updateEntity.getIdBooking());
        stm.setInt(2, updateEntity.getIdProduction());
        stm.setInt(3, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, ProductionHasBookingEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

    @Override
    public List<ProductionHasBookingEntity> getListByBooking(int idBooking) throws Exception {
        return null;
    }
}