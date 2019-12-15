package by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository;


import by.bsuir.ief.system.fabric.model.repository.BaseRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.ConnectionDataBaseCloseException;
import by.bsuir.ief.system.fabric.model.repository.mysql.ConnectionPool;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractRepositoryBase<T> implements BaseRepository<T> {

    protected abstract String getSqlInsert();

    protected abstract String getSqlRead();

    protected abstract String getSqlReadById();

    protected abstract String getSqlUpdate();

    protected abstract String getSqlDeleteById();

    protected abstract String getSqlDelete();

    protected abstract String getIdNameColumn();

    protected abstract BaseResultSetWrapper<T> getResultSetWrapper(final ResultSet resultSet);

    protected abstract void installInsertStatement(PreparedStatement stm, T insertEntity) throws SQLException;

    protected abstract void installByIdStatement(PreparedStatement stm, int id) throws SQLException;

    protected abstract void installUpdateStatement(PreparedStatement stm, T updateEntity) throws SQLException;

    protected abstract void installDeleteByIdStatement(PreparedStatement stm, int id) throws SQLException;

    protected abstract void installDeleteStatement(PreparedStatement stm, T deleteEntity) throws SQLException;

    @Override
    public synchronized long create(T entity) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        Connection connection = ConnectionPool.getInstance().retrieve();

        try {
            int resultInsert = 0;

            PreparedStatement stm = createPreparedStatementId(connection, getSqlInsert());

            if (stm != null) {
                installInsertStatement(stm, entity);

                resultInsert = stm.executeUpdate();

                ResultSet resultSet = stm.getGeneratedKeys();
                if (resultSet.next()) {
                    System.out.println("added entity " + entity.getClass().getName() + " generate value " + resultSet.getLong(1));
                    return resultSet.getLong(1);
                }
            }


            return resultInsert;
        } finally {
            ConnectionPool.getInstance().putback(connection);
        }
    }

    @Override
    public synchronized T read(int id) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        Connection connection = ConnectionPool.getInstance().retrieve();

        try {
            T readEntity = null;
            System.out.println("read " + getClass().getName() + " by id " + id);
            PreparedStatement stm = createPreparedStatement(connection, getSqlReadById());

            if (stm != null) {
                installByIdStatement(stm, id);

                ResultSet rs = stm.executeQuery();
                rs.last();
                int rsSize = rs.getRow();
                rs.beforeFirst();
                if (rsSize > 0) {
                    rs.next();
                    readEntity = getResultSetWrapper(rs).get();
                }
            }

            return readEntity;
        } finally {

            ConnectionPool.getInstance().putback(connection);
        }
    }

    @Override
    public synchronized List<T> read() throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        Connection connection = ConnectionPool.getInstance().retrieve();
        try {

            List<T> entityList = new ArrayList<>();

            PreparedStatement stm = createPreparedStatement(connection, getSqlRead());

            if (stm != null) {

                ResultSet rs = stm.executeQuery();

                rs.last();
                int rsSize = rs.getRow();
                rs.beforeFirst();

                if (rsSize > 0) {
                    while (rs.next())
                        entityList.add(getResultSetWrapper(rs).get());

                    rs.next();
                }
            }

            return entityList;
        } finally {
            ConnectionPool.getInstance().putback(connection);
        }
    }

    @Override
    public synchronized T update(T updateEntity) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        Connection connection = ConnectionPool.getInstance().retrieve();

        try {
            PreparedStatement stm = createPreparedStatement(connection, getSqlUpdate());

            if (stm != null) {
                int result = 0;
                installUpdateStatement(stm, updateEntity);

                result = stm.executeUpdate();
                if (result > 0)
                    return updateEntity;
            }

            return null;
        } finally {
            ConnectionPool.getInstance().putback(connection);
        }
    }

    @Override
    public synchronized boolean delete(T deleteEntity) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Connection connection = ConnectionPool.getInstance().retrieve();

        try {
            int resultDeleteRow = 0;

            PreparedStatement stm = createPreparedStatement(connection, getSqlDelete());

            if (stm != null) {
                installDeleteStatement(stm, deleteEntity);

                resultDeleteRow = stm.executeUpdate();
            }
            return resultDeleteRow != 0;
        } finally {
            ConnectionPool.getInstance().putback(connection);
        }
    }

    @Override
    public synchronized boolean delete(int idEntity) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Connection connection = ConnectionPool.getInstance().retrieve();

        try {
            int resultDeleteRow = 0;

            PreparedStatement stm = createPreparedStatement(connection, getSqlDeleteById());

            if (stm != null) {
                installDeleteByIdStatement(stm, idEntity);

                resultDeleteRow = stm.executeUpdate();
            }
            return resultDeleteRow != 0;
        } finally {
            ConnectionPool.getInstance().putback(connection);
        }
    }

    protected synchronized PreparedStatement createPreparedStatement(final Connection connection, final String sqlQuery) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        if (!connection.isClosed())
            return connection.prepareStatement(sqlQuery);
        throw new ConnectionDataBaseCloseException("Connection is closed");
    }

    protected synchronized PreparedStatement createPreparedStatementId(final Connection connection, final String sqlQuery) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        if (!connection.isClosed())
            return connection.prepareStatement(sqlQuery, new String[]{getIdNameColumn()});
        throw new ConnectionDataBaseCloseException("Connection is closed");
    }

    protected synchronized Connection openConnection() throws SQLException {

        Connection connection = ConnectionPool.getInstance().retrieve();

        return connection;
    }

    protected synchronized void closeConnection(final Connection connection) {
        ConnectionPool.getInstance().putback(connection);
    }

    protected <TYPE> List<TYPE> readCustomQuery(@NotNull String sqlQuery, @NotNull InitializeStatement<TYPE> initializeStatement) throws Exception {

        List<TYPE> typeList = new LinkedList<>();

        Connection connection = openConnection();

        try {

            PreparedStatement stm = createPreparedStatement(connection, sqlQuery);

            if (stm != null) {
                initializeStatement.onSetValue(stm);

                ResultSet rs = stm.executeQuery();
                rs.last();
                int rsSize = rs.getRow();
                rs.beforeFirst();

                if (rsSize > 0) {
                    while (rs.next()) {

                        typeList.add(initializeStatement.createWrapper(rs).get());
                    }

                    rs.next();
                }
            } else {
                throw new NullPointerException("PreparedStatement == null");
            }
        } finally {
            closeConnection(connection);
        }

        return typeList;
    }

    public interface InitializeStatement<TYPE> {
        void onSetValue(@NotNull PreparedStatement stm) throws SQLException;

        BaseResultSetWrapper<TYPE> createWrapper(ResultSet rs);
    }
}
