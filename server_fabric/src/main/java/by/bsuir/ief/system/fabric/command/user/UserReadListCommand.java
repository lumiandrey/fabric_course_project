package by.bsuir.ief.system.fabric.command.user;

import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;

public class UserReadListCommand extends GenericReadListEntityCommand<UserEntity> {
    @Override
    public Class<UserEntity> getType() {
        return UserEntity.class;
    }
}
