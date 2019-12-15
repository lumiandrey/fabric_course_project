package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.IComponentPartRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.ComponentPartResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.ComponentPartWithProducerResultSetWrapper;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ComponentPartRepository extends AbstractRepositoryBase<ComponentPartEntity> implements IComponentPartRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.NAME,
                SchemaDataBase.ComponentPart.COST,
                SchemaDataBase.ComponentPart.ID_PRODUCER
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s, %s, %s FROM %s",
                SchemaDataBase.ComponentPart.ID,
                SchemaDataBase.ComponentPart.NAME,
                SchemaDataBase.ComponentPart.COST,
                SchemaDataBase.ComponentPart.ID_PRODUCER,
                SchemaDataBase.ComponentPart.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s, %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.ComponentPart.ID,
                SchemaDataBase.ComponentPart.NAME,
                SchemaDataBase.ComponentPart.COST,
                SchemaDataBase.ComponentPart.ID_PRODUCER,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.NAME,
                SchemaDataBase.ComponentPart.COST,
                SchemaDataBase.ComponentPart.ID_PRODUCER,
                SchemaDataBase.ComponentPart.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.ComponentPart.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.ComponentPart.TABLE, SchemaDataBase.ComponentPart.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.ComponentPart.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<ComponentPartEntity> getResultSetWrapper(ResultSet resultSet) {
        return new ComponentPartResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, ComponentPartEntity insertEntity) throws SQLException {
        stm.setString(1, insertEntity.getName());
        stm.setDouble(2, insertEntity.getCost());
        stm.setInt(3, insertEntity.getIdProducer());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, ComponentPartEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getName());
        stm.setDouble(2, updateEntity.getCost());
        stm.setInt(3, updateEntity.getIdProducer());
        stm.setInt(4, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, ComponentPartEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

    @Override
    public List<ComponentPartWithProducerEntity> readComponentPartWithProducerList() throws Exception {

        String query = String.format(
                "SELECT %s.%s, %s.%s, %s.%s, %s.%s, %s.%s FROM %s INNER JOIN %s ON %s.%s = %s.%s",
                //arg select
                SchemaDataBase.Producer.TABLE,
                SchemaDataBase.Producer.ID,
                SchemaDataBase.Producer.TABLE,
                SchemaDataBase.Producer.NAME,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.ID,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.NAME,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.COST,
                //from
                SchemaDataBase.ComponentPart.TABLE,
                //join table
                SchemaDataBase.Producer.TABLE,
                //join column
                SchemaDataBase.Producer.TABLE,
                SchemaDataBase.Producer.ID,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.ID_PRODUCER
        );

        return readCustomQuery(query, new InitializeStatement<>() {
            @Override
            public void onSetValue(@NotNull PreparedStatement stm) throws SQLException {
            }

            @Override
            public BaseResultSetWrapper<ComponentPartWithProducerEntity> createWrapper(ResultSet rs) {
                return new ComponentPartWithProducerResultSetWrapper(rs);
            }
        });
    }

    @Override
    public List<ComponentPartWithProducerEntity> readComponentPartWithProducerListByProduction(int idProduction) throws Exception {
        String query = String.format(
                "SELECT %s.%s, %s.%s, %s.%s, %s.%s, %s.%s " +
                        "FROM %s INNER JOIN %s ON %s.%s = %s.%s INNER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = ?",
                //arg select
                SchemaDataBase.Producer.TABLE,
                SchemaDataBase.Producer.ID,
                SchemaDataBase.Producer.TABLE,
                SchemaDataBase.Producer.NAME,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.ID,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.NAME,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.COST,
                //from
                SchemaDataBase.ComponentPartHasProduction.TABLE,
                //join table1
                SchemaDataBase.ComponentPart.TABLE,
                //on JOIN table 1
                SchemaDataBase.ComponentPartHasProduction.TABLE,
                SchemaDataBase.ComponentPartHasProduction.ID_COMPONENT,
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.ID,
                //join table1
                SchemaDataBase.Producer.TABLE,
                //on JOIN table 2
                //join column
                SchemaDataBase.ComponentPart.TABLE,
                SchemaDataBase.ComponentPart.ID_PRODUCER,
                SchemaDataBase.Producer.TABLE,
                SchemaDataBase.Producer.ID,
                //WHERE
                SchemaDataBase.ComponentPartHasProduction.TABLE,
                SchemaDataBase.ComponentPartHasProduction.ID_PRODUCTION
        );

        return readCustomQuery(query, new InitializeStatement<>() {
            @Override
            public void onSetValue(@NotNull PreparedStatement stm) throws SQLException {
                stm.setInt(1, idProduction);
            }

            @Override
            public BaseResultSetWrapper<ComponentPartWithProducerEntity> createWrapper(ResultSet rs) {
                return new ComponentPartWithProducerResultSetWrapper(rs);
            }
        });
    }
}