package by.bsuir.ief.system.fabric.command.user;

import by.bsuir.ief.system.fabric.command.GenericReadByIdEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;

public class UserReadByIdCommand extends GenericReadByIdEntityCommand<UserEntity> {
    @Override
    public Class<UserEntity> getType() {
        return UserEntity.class;
    }
}
