package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionHasOutGoingConstResultSetWrapper extends BaseResultSetWrapper<ProductionHasOutGoingConstEntity> {

    private static final String NAME_TABLE = SchemaDataBase.ProductionHasOutGoingConst.TABLE + '.';

    public ProductionHasOutGoingConstResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ProductionHasOutGoingConstEntity get() throws SQLException {
        return new ProductionHasOutGoingConstEntity(
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasOutGoingConst.ID),
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasOutGoingConst.ID_PRODUCTION),
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasOutGoingConst.ID_OUTGOING_CONST)
        );
    }
}
