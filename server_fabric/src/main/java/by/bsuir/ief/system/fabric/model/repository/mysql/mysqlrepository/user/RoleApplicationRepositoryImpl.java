package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.user;


import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.user.RoleApplicationResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.user.RoleApplicationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleApplicationRepositoryImpl extends AbstractRepositoryBase<RoleApplicationEntity> implements RoleApplicationRepository {

    @Override
    protected String getSqlInsert() {
        return "INSERT INTO " + SchemaDataBase.RoleApplication.TABLE +
                '(' +
                SchemaDataBase.RoleApplication.NAME + ", " +
                SchemaDataBase.RoleApplication.LEVEL + ") " +
                "VALUES (?, ?)";
    }


    @Override
    protected String getSqlRead() {
        return "SELECT " + SchemaDataBase.RoleApplication.ID + ", " +
                SchemaDataBase.RoleApplication.NAME + ", " +
                SchemaDataBase.RoleApplication.LEVEL +
                " FROM " + SchemaDataBase.RoleApplication.TABLE;
    }

    @Override
    protected String getSqlReadById() {
        return "SELECT " + SchemaDataBase.RoleApplication.ID + ", " +
                SchemaDataBase.RoleApplication.NAME + ", " +
                SchemaDataBase.RoleApplication.LEVEL +
                " FROM " + SchemaDataBase.RoleApplication.TABLE + " WHERE " + SchemaDataBase.RoleApplication.ID + " = ?";
    }

    @Override
    protected String getSqlUpdate() {
        return "UPDATE " + SchemaDataBase.RoleApplication.TABLE + " SET " +
                SchemaDataBase.RoleApplication.NAME + " = ?, " +
                SchemaDataBase.RoleApplication.LEVEL + " = ?, " +
                "WHERE " + SchemaDataBase.RoleApplication.ID + " = ?";
    }

    @Override
    protected String getIdNameColumn() {
        return SchemaDataBase.RoleApplication.ID;
    }

    @Override
    protected String getSqlDeleteById() {
        return String.format("DELETE FROM %s WHERE %s = ?", SchemaDataBase.RoleApplication.TABLE, SchemaDataBase.RoleApplication.ID);
    }

    @Override
    protected String getSqlDelete() {
        return String.format("DELETE FROM %s", SchemaDataBase.RoleApplication.TABLE);
    }

    @Override
    protected BaseResultSetWrapper<RoleApplicationEntity> getResultSetWrapper(ResultSet resultSet) {
        return new RoleApplicationResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, RoleApplicationEntity insertEntity) throws SQLException {

        stm.setString(1, insertEntity.getName());
        stm.setInt(2, insertEntity.getLevel());

    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, RoleApplicationEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getName());
        stm.setInt(2, updateEntity.getLevel());
        stm.setInt(3, updateEntity.getIdRoleApplication());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {

        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, RoleApplicationEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getIdRoleApplication());
    }


    @Override
    public RoleApplicationEntity getRegistrationRole(String name) throws Exception {

        final String sql = String.format("SELECT %s, " +
                        "%s, " +
                        "%s " +
                        "FROM %s " +
                        "WHERE %s = ?",
                SchemaDataBase.RoleApplication.ID,
                SchemaDataBase.RoleApplication.NAME,
                SchemaDataBase.RoleApplication.LEVEL,
                SchemaDataBase.RoleApplication.TABLE,
                SchemaDataBase.RoleApplication.ID);
        Connection connection = openConnection();

        try {

            PreparedStatement stm = createPreparedStatement(connection, sql);

            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            rs.last();
            int rsSize = rs.getRow();
            rs.beforeFirst();

            if (rsSize > 0) {
                rs.next();
                return getResultSetWrapper(rs).get();
            }
        } finally {
            closeConnection(connection);
        }

        return null;
    }
}
