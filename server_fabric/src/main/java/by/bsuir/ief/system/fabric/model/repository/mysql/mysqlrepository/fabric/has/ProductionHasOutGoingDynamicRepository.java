package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasOutGoingDynamicRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.has.ProductionHasOutGoingDynamicResultSetWrapper;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductionHasOutGoingDynamicRepository extends AbstractRepositoryBase<ProductionHasOutGoingDynamicEntity> implements IProductionHasOutGoingDynamicRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemaDataBase.ProductionHasOutGoingDynamic.TABLE,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_OUTGOING_DYNAMIC,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_PRODUCTION
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s FROM %s",
                SchemaDataBase.ProductionHasOutGoingDynamic.ID,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_OUTGOING_DYNAMIC,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_PRODUCTION,
                SchemaDataBase.ProductionHasOutGoingDynamic.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.ProductionHasOutGoingDynamic.ID,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_OUTGOING_DYNAMIC,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_PRODUCTION,
                SchemaDataBase.ProductionHasOutGoingDynamic.TABLE,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.ProductionHasOutGoingDynamic.TABLE,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_OUTGOING_DYNAMIC,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_PRODUCTION,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.ProductionHasOutGoingDynamic.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.ProductionHasOutGoingDynamic.TABLE, SchemaDataBase.ProductionHasOutGoingDynamic.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.ProductionHasOutGoingDynamic.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<ProductionHasOutGoingDynamicEntity> getResultSetWrapper(ResultSet resultSet) {
        return new ProductionHasOutGoingDynamicResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, ProductionHasOutGoingDynamicEntity insertEntity) throws SQLException {
        stm.setInt(1, insertEntity.getIdOutGoingDynamic());
        stm.setInt(2, insertEntity.getIdProduction());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, ProductionHasOutGoingDynamicEntity updateEntity) throws SQLException {

        stm.setInt(1, updateEntity.getIdOutGoingDynamic());
        stm.setInt(2, updateEntity.getIdProduction());
        stm.setInt(3, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, ProductionHasOutGoingDynamicEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

    @Override
    public List<ProductionHasOutGoingDynamicEntity> getListByProduction(int idProduction) throws Exception {
        String sql = String.format(String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.ProductionHasOutGoingDynamic.ID,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_OUTGOING_DYNAMIC,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_PRODUCTION,
                SchemaDataBase.ProductionHasOutGoingDynamic.TABLE,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_PRODUCTION
        ));

        return readCustomQuery(sql, new InitializeStatement<>() {
            @Override
            public void onSetValue(@NotNull PreparedStatement stm) throws SQLException {
                stm.setInt(1, idProduction);
            }

            @Override
            public BaseResultSetWrapper<ProductionHasOutGoingDynamicEntity> createWrapper(ResultSet rs) {
                return new ProductionHasOutGoingDynamicResultSetWrapper(rs);
            }
        });
    }
}