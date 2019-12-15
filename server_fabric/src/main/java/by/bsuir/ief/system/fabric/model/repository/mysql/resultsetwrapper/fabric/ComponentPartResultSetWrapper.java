package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentPartResultSetWrapper extends BaseResultSetWrapper<ComponentPartEntity> {

    private static final String NAME_TABLE = SchemaDataBase.ComponentPart.TABLE;

    public ComponentPartResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ComponentPartEntity get() throws SQLException {

        return new ComponentPartEntity(
                rs.getInt(NAME_TABLE + '.' + SchemaDataBase.ComponentPart.ID),
                rs.getString(NAME_TABLE + '.' + SchemaDataBase.ComponentPart.NAME),
                rs.getDouble(NAME_TABLE + '.' + SchemaDataBase.ComponentPart.COST),
                rs.getInt(NAME_TABLE + '.' + SchemaDataBase.ComponentPart.ID_PRODUCER)
        );
    }
}
