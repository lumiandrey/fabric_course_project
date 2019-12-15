package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ComponentPartHasProductionEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentPartHasProductionEntityResultSetWrapper extends BaseResultSetWrapper<ComponentPartHasProductionEntity> {

    private static final String NAME_TABLE = SchemaDataBase.ComponentPartHasProduction.TABLE;

    public ComponentPartHasProductionEntityResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ComponentPartHasProductionEntity get() throws SQLException {
        return new ComponentPartHasProductionEntity(
                rs.getInt(NAME_TABLE + '.' + SchemaDataBase.ComponentPartHasProduction.ID),
                rs.getInt(NAME_TABLE + '.' + SchemaDataBase.ComponentPartHasProduction.ID_PRODUCTION),
                rs.getInt(NAME_TABLE + '.' + SchemaDataBase.ComponentPartHasProduction.ID_COMPONENT)
        );
    }
}
