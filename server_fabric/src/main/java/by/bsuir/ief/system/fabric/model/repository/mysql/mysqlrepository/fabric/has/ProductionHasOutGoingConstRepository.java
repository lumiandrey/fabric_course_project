package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasOutGoingConstRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.has.ProductionHasOutGoingConstResultSetWrapper;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductionHasOutGoingConstRepository extends AbstractRepositoryBase<ProductionHasOutGoingConstEntity> implements IProductionHasOutGoingConstRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemaDataBase.ProductionHasOutGoingConst.TABLE,
                SchemaDataBase.ProductionHasOutGoingConst.ID_OUTGOING_CONST,
                SchemaDataBase.ProductionHasOutGoingConst.ID_PRODUCTION
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s FROM %s",
                SchemaDataBase.ProductionHasOutGoingConst.ID,
                SchemaDataBase.ProductionHasOutGoingConst.ID_OUTGOING_CONST,
                SchemaDataBase.ProductionHasOutGoingConst.ID_PRODUCTION,
                SchemaDataBase.ProductionHasOutGoingConst.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.ProductionHasOutGoingConst.ID,
                SchemaDataBase.ProductionHasOutGoingConst.ID_OUTGOING_CONST,
                SchemaDataBase.ProductionHasOutGoingConst.ID_PRODUCTION,
                SchemaDataBase.ProductionHasOutGoingConst.TABLE,
                SchemaDataBase.ProductionHasOutGoingConst.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.ProductionHasOutGoingConst.TABLE,
                SchemaDataBase.ProductionHasOutGoingConst.ID_OUTGOING_CONST,
                SchemaDataBase.ProductionHasOutGoingConst.ID_PRODUCTION,
                SchemaDataBase.ProductionHasOutGoingConst.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.ProductionHasOutGoingConst.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.ProductionHasOutGoingConst.TABLE, SchemaDataBase.ProductionHasOutGoingConst.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.ProductionHasOutGoingConst.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<ProductionHasOutGoingConstEntity> getResultSetWrapper(ResultSet resultSet) {
        return new ProductionHasOutGoingConstResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, ProductionHasOutGoingConstEntity insertEntity) throws SQLException {
        stm.setInt(1, insertEntity.getIdOutGoingConst());
        stm.setInt(2, insertEntity.getIdProduction());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, ProductionHasOutGoingConstEntity updateEntity) throws SQLException {

        stm.setInt(1, updateEntity.getIdOutGoingConst());
        stm.setInt(2, updateEntity.getIdProduction());
        stm.setInt(3, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, ProductionHasOutGoingConstEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

    @Override
    public List<ProductionHasOutGoingConstEntity> getListByProduction(int idProduction) throws Exception {
        String sql = String.format(String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.ProductionHasOutGoingConst.ID,
                SchemaDataBase.ProductionHasOutGoingConst.ID_OUTGOING_CONST,
                SchemaDataBase.ProductionHasOutGoingConst.ID_PRODUCTION,
                SchemaDataBase.ProductionHasOutGoingConst.TABLE,
                SchemaDataBase.ProductionHasOutGoingConst.ID_PRODUCTION
        ));

        return readCustomQuery(sql, new InitializeStatement<>() {
            @Override
            public void onSetValue(@NotNull PreparedStatement stm) throws SQLException {
                stm.setInt(1, idProduction);
            }

            @Override
            public BaseResultSetWrapper<ProductionHasOutGoingConstEntity> createWrapper(ResultSet rs) {
                return new ProductionHasOutGoingConstResultSetWrapper(rs);
            }
        });
    }
}