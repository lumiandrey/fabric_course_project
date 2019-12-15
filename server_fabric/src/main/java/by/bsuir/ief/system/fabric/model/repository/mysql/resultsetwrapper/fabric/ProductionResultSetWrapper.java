package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric;

import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionResultSetWrapper extends BaseResultSetWrapper<ProductionEntity> {

    private static final String NAME_TABLE = SchemaDataBase.Production.TABLE + '.';

    public ProductionResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ProductionEntity get() throws SQLException {
        return new ProductionEntity(
                rs.getInt(NAME_TABLE + SchemaDataBase.Production.ID),
                rs.getString(NAME_TABLE + SchemaDataBase.Production.NAME),
                rs.getString(NAME_TABLE + SchemaDataBase.Production.DESCRIBE)
        );
    }
}
