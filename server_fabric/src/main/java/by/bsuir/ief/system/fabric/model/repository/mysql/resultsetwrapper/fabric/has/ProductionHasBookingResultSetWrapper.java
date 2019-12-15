package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.fabric.has;

import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasBookingEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionHasBookingResultSetWrapper extends BaseResultSetWrapper<ProductionHasBookingEntity> {

    private static final String NAME_TABLE = SchemaDataBase.ProductionHasBooking.TABLE + '.';

    public ProductionHasBookingResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public ProductionHasBookingEntity get() throws SQLException {
        return new ProductionHasBookingEntity(
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasBooking.ID),
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasBooking.ID_PRODUCTION),
                rs.getInt(NAME_TABLE + SchemaDataBase.ProductionHasBooking.ID_BOOKING)
        );
    }
}
