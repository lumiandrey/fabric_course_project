package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.IOutGoingDynamicRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.OutGoingDynamicResultSetWrapper;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OutGoingDynamicRepository extends AbstractRepositoryBase<OutGoingDynamicEntity> implements IOutGoingDynamicRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemaDataBase.OutGoingDynamic.TABLE,
                SchemaDataBase.OutGoingDynamic.NAME,
                SchemaDataBase.OutGoingDynamic.COST
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s FROM %s",
                SchemaDataBase.OutGoingDynamic.ID,
                SchemaDataBase.OutGoingDynamic.NAME,
                SchemaDataBase.OutGoingDynamic.COST,
                SchemaDataBase.OutGoingDynamic.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.OutGoingDynamic.ID,
                SchemaDataBase.OutGoingDynamic.NAME,
                SchemaDataBase.OutGoingDynamic.COST,
                SchemaDataBase.OutGoingDynamic.TABLE,
                SchemaDataBase.OutGoingDynamic.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.OutGoingDynamic.TABLE,
                SchemaDataBase.OutGoingDynamic.NAME,
                SchemaDataBase.OutGoingDynamic.COST,
                SchemaDataBase.OutGoingDynamic.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.OutGoingDynamic.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.OutGoingDynamic.TABLE, SchemaDataBase.OutGoingDynamic.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.OutGoingDynamic.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<OutGoingDynamicEntity> getResultSetWrapper(ResultSet resultSet) {
        return new OutGoingDynamicResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, OutGoingDynamicEntity insertEntity) throws SQLException {
        stm.setString(1, insertEntity.getName());
        stm.setDouble(2, insertEntity.getCost());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, OutGoingDynamicEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getName());
        stm.setDouble(2, updateEntity.getCost());
        stm.setInt(3, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, OutGoingDynamicEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

    @Override
    public List<OutGoingDynamicEntity> readOutGoingByProduction(int idProduction) throws Exception {

        String query = String.format(
                "SELECT %s.%s, %s.%s, %s.%s FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = ?",
                //arg select
                SchemaDataBase.OutGoingDynamic.TABLE,
                SchemaDataBase.OutGoingDynamic.ID,
                SchemaDataBase.OutGoingDynamic.TABLE,
                SchemaDataBase.OutGoingDynamic.NAME,
                SchemaDataBase.OutGoingDynamic.TABLE,
                SchemaDataBase.OutGoingDynamic.COST,
                //from
                SchemaDataBase.ProductionHasOutGoingDynamic.TABLE,
                //join table
                SchemaDataBase.OutGoingDynamic.TABLE,
                //join column
                SchemaDataBase.ProductionHasOutGoingDynamic.TABLE,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_OUTGOING_DYNAMIC,
                SchemaDataBase.OutGoingDynamic.TABLE,
                SchemaDataBase.OutGoingDynamic.ID,
                //WHERE
                SchemaDataBase.ProductionHasOutGoingDynamic.TABLE,
                SchemaDataBase.ProductionHasOutGoingDynamic.ID_PRODUCTION
        );


        return readCustomQuery(query, new InitializeStatement<>() {
            @Override
            public void onSetValue(@NotNull PreparedStatement stm) throws SQLException {
                stm.setInt(1, idProduction);
            }

            @Override
            public BaseResultSetWrapper<OutGoingDynamicEntity> createWrapper(ResultSet rs) {
                return new OutGoingDynamicResultSetWrapper(rs);
            }
        });
    }
}