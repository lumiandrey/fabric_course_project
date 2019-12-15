package by.bsuir.ief.system.fabric.command.user;


import by.bsuir.ief.system.fabric.command.GenericReadListEntityCommand;
import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;

public class RoleApplicationListReadCommand extends GenericReadListEntityCommand<RoleApplicationEntity> {
    @Override
    public Class<RoleApplicationEntity> getType() {
        return RoleApplicationEntity.class;
    }
}
