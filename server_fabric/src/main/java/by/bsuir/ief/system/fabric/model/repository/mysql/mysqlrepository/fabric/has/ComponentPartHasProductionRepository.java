package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ComponentPartHasProductionEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IComponentPartHasProductionRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.has.ComponentPartHasProductionEntityResultSetWrapper;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ComponentPartHasProductionRepository extends AbstractRepositoryBase<ComponentPartHasProductionEntity> implements IComponentPartHasProductionRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                SchemaDataBase.ComponentPartHasProduction.TABLE,
                SchemaDataBase.ComponentPartHasProduction.ID_COMPONENT,
                SchemaDataBase.ComponentPartHasProduction.ID_PRODUCTION
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s FROM %s",
                SchemaDataBase.ComponentPartHasProduction.ID,
                SchemaDataBase.ComponentPartHasProduction.ID_COMPONENT,
                SchemaDataBase.ComponentPartHasProduction.ID_PRODUCTION,
                SchemaDataBase.ComponentPartHasProduction.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.ComponentPartHasProduction.ID,
                SchemaDataBase.ComponentPartHasProduction.ID_COMPONENT,
                SchemaDataBase.ComponentPartHasProduction.ID_PRODUCTION,
                SchemaDataBase.ComponentPartHasProduction.TABLE,
                SchemaDataBase.ComponentPartHasProduction.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.ComponentPartHasProduction.TABLE,
                SchemaDataBase.ComponentPartHasProduction.ID_COMPONENT,
                SchemaDataBase.ComponentPartHasProduction.ID_PRODUCTION,
                SchemaDataBase.ComponentPartHasProduction.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.ComponentPartHasProduction.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.ComponentPartHasProduction.TABLE, SchemaDataBase.ComponentPartHasProduction.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.ComponentPartHasProduction.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<ComponentPartHasProductionEntity> getResultSetWrapper(ResultSet resultSet) {
        return new ComponentPartHasProductionEntityResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, ComponentPartHasProductionEntity insertEntity) throws SQLException {
        stm.setInt(1, insertEntity.getIdComponentParts());
        stm.setInt(2, insertEntity.getIdProduction());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, ComponentPartHasProductionEntity updateEntity) throws SQLException {

        stm.setInt(1, updateEntity.getIdComponentParts());
        stm.setInt(2, updateEntity.getIdProduction());
        stm.setInt(3, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, ComponentPartHasProductionEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

    @Override
    public List<ComponentPartHasProductionEntity> getListByProduction(int idProduction) throws Exception {

        String sql = String.format(String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.ComponentPartHasProduction.ID,
                SchemaDataBase.ComponentPartHasProduction.ID_COMPONENT,
                SchemaDataBase.ComponentPartHasProduction.ID_PRODUCTION,
                SchemaDataBase.ComponentPartHasProduction.TABLE,
                SchemaDataBase.ComponentPartHasProduction.ID_PRODUCTION
        ));

        return readCustomQuery(sql, new InitializeStatement<>() {
            @Override
            public void onSetValue(@NotNull PreparedStatement stm) throws SQLException {
                stm.setInt(1, idProduction);
            }

            @Override
            public BaseResultSetWrapper<ComponentPartHasProductionEntity> createWrapper(ResultSet rs) {
                return new ComponentPartHasProductionEntityResultSetWrapper(rs);
            }
        });
    }
}