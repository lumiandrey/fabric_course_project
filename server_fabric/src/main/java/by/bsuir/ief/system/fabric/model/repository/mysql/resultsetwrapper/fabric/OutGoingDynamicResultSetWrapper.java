package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OutGoingDynamicResultSetWrapper extends BaseResultSetWrapper<OutGoingDynamicEntity> {

    private static final String NAME_TABLE = SchemaDataBase.OutGoingDynamic.TABLE + '.';

    public OutGoingDynamicResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public OutGoingDynamicEntity get() throws SQLException {

        return new OutGoingDynamicEntity(
                rs.getInt(NAME_TABLE + SchemaDataBase.OutGoingDynamic.ID),
                rs.getString(NAME_TABLE + SchemaDataBase.OutGoingDynamic.NAME),
                rs.getDouble(NAME_TABLE + SchemaDataBase.OutGoingDynamic.COST)
        );
    }
}
