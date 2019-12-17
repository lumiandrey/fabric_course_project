package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ComponentPartWithProducerEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProducerEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentPartWithProducerResultSetWrapper extends BaseResultSetWrapper<ComponentPartWithProducerEntity> {

    public ComponentPartWithProducerResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ComponentPartWithProducerEntity get() throws SQLException {

        return new ComponentPartWithProducerEntity(
                rs.getInt(SchemaDataBase.ComponentPart.TABLE + '.' + SchemaDataBase.ComponentPart.ID),
                rs.getString(SchemaDataBase.ComponentPart.TABLE + '.' + SchemaDataBase.ComponentPart.NAME),
                rs.getDouble(SchemaDataBase.ComponentPart.TABLE + '.' + SchemaDataBase.ComponentPart.COST),
                rs.getInt(SchemaDataBase.Producer.TABLE + '.' + SchemaDataBase.Producer.ID),
                new ProducerEntity(
                        rs.getInt(SchemaDataBase.Producer.TABLE + '.' + SchemaDataBase.Producer.ID),
                        rs.getString(SchemaDataBase.Producer.TABLE + '.' + SchemaDataBase.Producer.NAME)
                )
        );
    }
}
