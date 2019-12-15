package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseResultSetWrapper<T> {

    protected final ResultSet rs;

    public BaseResultSetWrapper(ResultSet resultSet) {
        this.rs = resultSet;
    }

    public abstract T get() throws SQLException;
}
