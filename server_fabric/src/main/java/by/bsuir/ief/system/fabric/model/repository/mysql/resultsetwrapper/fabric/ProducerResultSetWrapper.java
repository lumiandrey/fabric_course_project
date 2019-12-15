package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProducerResultSetWrapper extends BaseResultSetWrapper<ProducerEntity> {

    private static final String NAME_TABLE = SchemaDataBase.Producer.TABLE;

    public ProducerResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ProducerEntity get() throws SQLException {
        return new ProducerEntity(
                rs.getInt(NAME_TABLE + '.' + SchemaDataBase.Producer.ID),
                rs.getString(NAME_TABLE + '.' + SchemaDataBase.Producer.NAME)
        );
    }
}
