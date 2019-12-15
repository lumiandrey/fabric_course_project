package by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.user;


import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.repository.mysql.resultsetwrapper.BaseResultSetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetWrapper extends BaseResultSetWrapper<UserEntity> {
    public UserResultSetWrapper(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public UserEntity get() throws SQLException {

        UserEntity userEntity = new UserEntity();
        userEntity.setIdUser(rs.getInt("user.id_user"));
        userEntity.setLogin(rs.getString("user.login"));
        userEntity.setPassword(rs.getString("user.password"));
        userEntity.setStatus(rs.getInt("user.status"));

        userEntity.setRoleApplication(new RoleApplicationResultSetWrapper(rs).get());

        return userEntity;
    }
}
