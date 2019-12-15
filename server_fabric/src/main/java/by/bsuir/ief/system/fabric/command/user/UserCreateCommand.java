package by.bsuir.ief.system.fabric.command.user;


import by.bsuir.ief.system.fabric.command.GenericCreateEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;

public class UserCreateCommand extends GenericCreateEntityCommand<UserEntity> {
    @Override
    public Class<UserEntity> getType() {
        return UserEntity.class;
    }
}
