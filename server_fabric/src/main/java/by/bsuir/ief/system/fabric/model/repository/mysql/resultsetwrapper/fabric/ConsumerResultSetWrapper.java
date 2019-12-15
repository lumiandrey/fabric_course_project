package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ConsumerEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerResultSetWrapper extends BaseResultSetWrapper<ConsumerEntity> {

    private static final String NAME_TABLE = SchemaDataBase.Consumer.TABLE + '.';

    public ConsumerResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ConsumerEntity get() throws SQLException {
        return new ConsumerEntity(
                rs.getInt(NAME_TABLE + SchemaDataBase.Consumer.ID),
                rs.getString(NAME_TABLE + SchemaDataBase.Consumer.NAME)
        );
    }
}
