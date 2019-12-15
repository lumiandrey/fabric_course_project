package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.user;


import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.AbstractRepositoryBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.user.UserResultSetWrapper;
import by.bsuir.ief.system.fabric.model.repository.user.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl extends AbstractRepositoryBase<UserEntity> implements UserRepository {
    @Override
    protected String getSqlInsert() {
        return "INSERT INTO user" +
                "(login, password, status, id_role_application)" +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getSqlRead() {
        return "SELECT id_user, login, password, status, role_application.id_role_application, role_application.level, role_application.name " +
                "FROM user INNER JOIN role_application " +
                "ON user.id_role_application = role_application.id_role_application";
    }

    @Override
    protected String getSqlReadById() {
        return "SELECT id_user, login, password, status, id_role_application, level, name " +
                "FROM user INNER JOIN role_application " +
                "ON user.id_role_application = role_application.id_role_application WHERE id_user = ?";
    }

    @Override
    protected String getSqlUpdate() {
        return "UPDATE user SET " +
                "login =  ?," +
                "password = ?, " +
                "status = ?, " +
                "id_role_application = ? " +
                "WHERE id_user = ?";
    }

    @Override
    protected String getIdNameColumn() {
        return "id_user";
    }

    @Override
    protected String getSqlDeleteById() {
        return "DELETE FROM user WHERE user_id = ?";
    }

    @Override
    protected String getSqlDelete() {
        return "DELETE FROM user";
    }

    @Override
    protected BaseResultSetWrapper<UserEntity> getResultSetWrapper(ResultSet resultSet) {
        return new UserResultSetWrapper(resultSet);
    }

    @Override
    protected void installInsertStatement(PreparedStatement stm, UserEntity insertEntity) throws SQLException {

        stm.setString(1, insertEntity.getLogin());
        stm.setString(2, insertEntity.getPassword());
        stm.setInt(3, insertEntity.getStatus());
        stm.setInt(4, insertEntity.getRoleApplication().getIdRoleApplication());
    }

    @Override
    protected void installByIdStatement(PreparedStatement stm, int id) throws SQLException {
        stm.setInt(1, id);
    }

    @Override
    protected void installUpdateStatement(PreparedStatement stm, UserEntity updateEntity) throws SQLException {

        stm.setString(1, updateEntity.getLogin());
        stm.setString(2, updateEntity.getPassword());
        stm.setInt(3, updateEntity.getStatus());
        stm.setInt(4, updateEntity.getRoleApplication().getIdRoleApplication());

        stm.setInt(5, updateEntity.getIdUser());
    }

    @Override
    protected void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException {
        stm.setInt(1, id);
    }

    @Override
    protected void installDeleteStatement(PreparedStatement stm, UserEntity deleteEntity) throws SQLException {

        stm.setInt(1, deleteEntity.getIdUser());
    }

    @Override
    public boolean checkLogin(String login) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        Connection connection = openConnection();

        boolean result = false;
        try {

            String sql = "SELECT * FROM user WHERE login = ?;";
            PreparedStatement stm = createPreparedStatement(connection, sql);

            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();
            rs.last();
            int rsSize = rs.getRow();
            rs.beforeFirst();

            if (rsSize > 0) {
                result = true;
            }

        } finally {
            closeConnection(connection);
        }

        return result;
    }

    @Override
    public UserEntity getPairLoginPassword(String login, String password) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Connection connection = openConnection();

        UserEntity result = null;
        try {

            String sql = "SELECT * FROM user INNER JOIN role_application ON  user.id_role_application = role_application.id_role_application WHERE login = ? AND password = ?;";
            PreparedStatement stm = createPreparedStatement(connection, sql);

            stm.setString(1, login);
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();
            rs.last();
            int rsSize = rs.getRow();
            rs.beforeFirst();

            if (rsSize > 0) {
                rs.next();
                result = getResultSetWrapper(rs).get();
            }

        } finally {
            closeConnection(connection);
        }

        return result;
    }
}
