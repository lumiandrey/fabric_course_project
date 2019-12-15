package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionHasOutGoingDynamicResultSetWrapper extends BaseResultSetWrapper<ProductionHasOutGoingDynamicEntity> {

    private static final String NAME_TABLE = SchemaDataBase.ProductionHasOutGoingDynamic.TABLE + '.';

    public ProductionHasOutGoingDynamicResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ProductionHasOutGoingDynamicEntity get() throws SQLException {
        return new ProductionHasOutGoingDynamicEntity(
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasOutGoingDynamic.ID),
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasOutGoingDynamic.ID_PRODUCTION),
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasOutGoingDynamic.ID_OUTGOING_DYNAMIC)
        );
    }
}
