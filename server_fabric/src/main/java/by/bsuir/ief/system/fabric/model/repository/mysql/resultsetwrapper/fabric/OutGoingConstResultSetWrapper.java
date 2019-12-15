package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.OutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OutGoingConstResultSetWrapper extends BaseResultSetWrapper<OutGoingConstEntity> {

    private static final String NAME_TABLE = SchemaDataBase.OutGoingConst.TABLE + '.';

    public OutGoingConstResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public OutGoingConstEntity get() throws SQLException {

        return new OutGoingConstEntity(
                rs.getInt(NAME_TABLE + SchemaDataBase.OutGoingConst.ID),
                rs.getString(NAME_TABLE + SchemaDataBase.OutGoingConst.NAME),
                rs.getDouble(NAME_TABLE + SchemaDataBase.OutGoingConst.COST)
        );
    }
}
