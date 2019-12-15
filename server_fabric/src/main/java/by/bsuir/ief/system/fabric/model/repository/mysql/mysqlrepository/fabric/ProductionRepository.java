package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.IProduсtionRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.ProductionResultSetWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionRepository extends AbstractRepositoryBase<ProductionEntity> implements IProduсtionRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemaDataBase.Production.TABLE,
                SchemaDataBase.Production.NAME,
                SchemaDataBase.Production.DESCRIBE
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s FROM %s",
                SchemaDataBase.Production.ID,
                SchemaDataBase.Production.NAME,
                SchemaDataBase.Production.DESCRIBE,
                SchemaDataBase.Production.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.Production.ID,
                SchemaDataBase.Production.NAME,
                SchemaDataBase.Production.DESCRIBE,
                SchemaDataBase.Production.TABLE,
                SchemaDataBase.Production.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.Production.TABLE,
                SchemaDataBase.Production.NAME,
                SchemaDataBase.Production.DESCRIBE,
                SchemaDataBase.Production.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.Production.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.Production.TABLE, SchemaDataBase.Production.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.Production.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<ProductionEntity> getResultSetWrapper(ResultSet resultSet) {
        return new ProductionResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, ProductionEntity insertEntity) throws SQLException {
        stm.setString(1, insertEntity.getName());
        stm.setString(2, insertEntity.getDescribe());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, ProductionEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getName());
        stm.setString(2, updateEntity.getDescribe());
        stm.setInt(3, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, ProductionEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

    @Override
    public List<ProductionEntity> productionListByBooking(int idBooking) throws Exception {

        String query = String.format(
                "SELECT * FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = ?",
               /* SchemaDataBase.Production.ID,
                SchemaDataBase.Production.NAME,
                SchemaDataBase.Production.DESCRIBE,*/
                //FROM
                SchemaDataBase.ProductionHasBooking.TABLE,
                //JOIN
                SchemaDataBase.Production.TABLE,
                // JOIN expresion
                SchemaDataBase.ProductionHasBooking.TABLE,
                SchemaDataBase.ProductionHasBooking.ID_PRODUCTION,
                SchemaDataBase.Production.TABLE,
                SchemaDataBase.Production.ID,
                SchemaDataBase.ProductionHasBooking.TABLE,
                SchemaDataBase.ProductionHasBooking.ID_BOOKING
        );

        List<ProductionEntity> entities = new ArrayList<>();

        Connection connection = openConnection();

        try {

            PreparedStatement stm = createPreparedStatement(connection, query);

            stm.setInt(1, idBooking);

            ResultSet rs = stm.executeQuery();
            rs.last();
            int rsSize = rs.getRow();
            rs.beforeFirst();

            if (rsSize > 0) {
                while (rs.next())
                    entities.add(getResultSetWrapper(rs).get());

                rs.next();
            }
        } finally {
            closeConnection(connection);
        }

        return entities;
    }
}