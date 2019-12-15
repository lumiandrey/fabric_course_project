package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.IConsumerRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.ConsumerResultSetWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerRepository extends AbstractRepositoryBase<ConsumerEntity> implements IConsumerRepository {

    @Override
    protected String getSqlInsert() {
        return String.format("INSERT INTO %s ( %s ) VALUES (?)",
                SchemaDataBase.Consumer.TABLE,
                SchemaDataBase.Consumer.NAME
        );
    }

    @Override
    protected String getSqlRead() {
        return String.format("SELECT %s, %s FROM %s",
                SchemaDataBase.Consumer.ID,
                SchemaDataBase.Consumer.NAME,
                SchemaDataBase.Consumer.TABLE
        );
    }

    @Override
    protected String getSqlReadById() {
        return String.format("SELECT %s, %s FROM %s WHERE %s = ?",
                SchemaDataBase.Consumer.ID,
                SchemaDataBase.Consumer.NAME,
                SchemaDataBase.Consumer.TABLE,
                SchemaDataBase.Consumer.ID
        );
    }

    @Override
    protected String getSqlUpdate() {
        return String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                SchemaDataBase.Consumer.TABLE,
                SchemaDataBase.Consumer.NAME,
                SchemaDataBase.Consumer.ID
        );
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.Consumer.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.Consumer.TABLE, SchemaDataBase.Consumer.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.Consumer.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<ConsumerEntity> getResultSetWrapper(ResultSet resultSet) {
        return new ConsumerResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, ConsumerEntity insertEntity) throws SQLException {
        stm.setString(1, insertEntity.getName());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, ConsumerEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getName());
        stm.setInt(2, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, ConsumerEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }
}