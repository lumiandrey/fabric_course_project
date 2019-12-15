package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.IOutGoingConstRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.OutGoingConstResultSetWrapper;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OutGoingConstRepository extends AbstractRepositoryBase<OutGoingConstEntity> implements IOutGoingConstRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemaDataBase.OutGoingConst.TABLE,
                SchemaDataBase.OutGoingConst.NAME,
                SchemaDataBase.OutGoingConst.COST
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s FROM %s",
                SchemaDataBase.OutGoingConst.ID,
                SchemaDataBase.OutGoingConst.NAME,
                SchemaDataBase.OutGoingConst.COST,
                SchemaDataBase.OutGoingConst.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.OutGoingConst.ID,
                SchemaDataBase.OutGoingConst.NAME,
                SchemaDataBase.OutGoingConst.COST,
                SchemaDataBase.OutGoingConst.TABLE,
                SchemaDataBase.OutGoingConst.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.OutGoingConst.TABLE,
                SchemaDataBase.OutGoingConst.NAME,
                SchemaDataBase.OutGoingConst.COST,
                SchemaDataBase.OutGoingConst.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.OutGoingConst.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.OutGoingConst.TABLE, SchemaDataBase.OutGoingConst.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.OutGoingConst.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<OutGoingConstEntity> getResultSetWrapper(ResultSet resultSet) {
        return new OutGoingConstResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, OutGoingConstEntity insertEntity) throws SQLException {
        stm.setString(1, insertEntity.getName());
        stm.setDouble(2, insertEntity.getCost());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, OutGoingConstEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getName());
        stm.setDouble(2, updateEntity.getCost());
        stm.setInt(3, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, OutGoingConstEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

    @Override
    public List<OutGoingConstEntity> readOutGoingByProduction(int idProduction) throws Exception {

        String query = String.format(
                "SELECT %s.%s, %s.%s, %s.%s FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = ?",
                //arg select
                SchemaDataBase.OutGoingConst.TABLE,
                SchemaDataBase.OutGoingConst.ID,
                SchemaDataBase.OutGoingConst.TABLE,
                SchemaDataBase.OutGoingConst.NAME,
                SchemaDataBase.OutGoingConst.TABLE,
                SchemaDataBase.OutGoingConst.COST,
                //from
                SchemaDataBase.ProductionHasOutGoingConst.TABLE,
                //join table
                SchemaDataBase.OutGoingConst.TABLE,
                //join column
                SchemaDataBase.ProductionHasOutGoingConst.TABLE,
                SchemaDataBase.ProductionHasOutGoingConst.ID_OUTGOING_CONST,
                SchemaDataBase.OutGoingConst.TABLE,
                SchemaDataBase.OutGoingConst.ID,
                //WHERE
                SchemaDataBase.ProductionHasOutGoingConst.TABLE,
                SchemaDataBase.ProductionHasOutGoingConst.ID_PRODUCTION
        );


        return readCustomQuery(query, new InitializeStatement<>() {
            @Override
            public void onSetValue(@NotNull PreparedStatement stm) throws SQLException {
                stm.setInt(1, idProduction);
            }

            @Override
            public BaseResultSetWrapper<OutGoingConstEntity> createWrapper(ResultSet rs) {
                return new OutGoingConstResultSetWrapper(rs);
            }
        });
    }
}