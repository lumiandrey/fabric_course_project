package by.bsuir.ief.system.fabric.command.user;


import by.bsuir.ief.system.fabric.command.GenericDeleteEntityByIdCommand;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;

public class UserDeleteByIdCommand extends GenericDeleteEntityByIdCommand<UserEntity> {
    @Override
    protected Class<UserEntity> getType() {
        return UserEntity.class;
    }
}
