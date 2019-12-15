package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.fabric.IProducerRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.ProducerResultSetWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProducerRepository extends AbstractRepositoryBase<ProducerEntity> implements IProducerRepository {

    @Override
    protected String getSqlInsert() {
        return "INSERT INTO " + SchemaDataBase.Producer.TABLE +
                '(' +
                SchemaDataBase.Producer.NAME + ") " +
                "VALUES (?)";
    }

    @Override
    protected String getSqlRead() {
        return "SELECT " + SchemaDataBase.Producer.ID + ", " +
                SchemaDataBase.Producer.NAME +
                " FROM " + SchemaDataBase.Producer.TABLE;
    }

    @Override
    protected String getSqlReadById() {
        return "SELECT " + SchemaDataBase.Producer.ID + ", " +
                SchemaDataBase.Producer.NAME +
                " FROM " + SchemaDataBase.Producer.TABLE + " WHERE " + SchemaDataBase.Producer.ID + " = ?";
    }

    @Override
    protected String getSqlUpdate() {
        return "UPDATE " + SchemaDataBase.Producer.TABLE + " SET " +
                SchemaDataBase.Producer.NAME + " = ? " +
                "WHERE " + SchemaDataBase.Producer.ID + " = ?";
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.Producer.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.Producer.TABLE, SchemaDataBase.Producer.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.Producer.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<ProducerEntity> getResultSetWrapper(ResultSet resultSet) {
        return new ProducerResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, ProducerEntity insertEntity) throws SQLException {
        stm.setString(1, insertEntity.getName());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, ProducerEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getName());
        stm.setInt(2, updateEntity.getId());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, ProducerEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getId());
    }

}
