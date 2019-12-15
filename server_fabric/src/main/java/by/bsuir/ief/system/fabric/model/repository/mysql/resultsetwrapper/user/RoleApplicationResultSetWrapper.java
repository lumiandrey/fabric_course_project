package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.user;


import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.repository.SchemaDataBase;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleApplicationResultSetWrapper extends BaseResultSetWrapper<RoleApplicationEntity> {
    public RoleApplicationResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public RoleApplicationEntity get() throws SQLException {
        RoleApplicationEntity accessLevel = new RoleApplicationEntity();

        accessLevel.setIdRoleApplication(rs.getInt(SchemaDataBase.RoleApplication.TABLE + '.' + SchemaDataBase.RoleApplication.ID));
        accessLevel.setLevel(rs.getInt(SchemaDataBase.RoleApplication.TABLE + '.' + SchemaDataBase.RoleApplication.LEVEL));
        accessLevel.setName(rs.getString(SchemaDataBase.RoleApplication.TABLE + '.' + SchemaDataBase.RoleApplication.NAME));

        return accessLevel;
    }
}
