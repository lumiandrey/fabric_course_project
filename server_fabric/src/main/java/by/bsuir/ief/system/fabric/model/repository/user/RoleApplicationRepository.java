package by.bsuir.ief.system.fabric.model.repository.user;


import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;
import org.jetbrains.annotations.NotNull;

public interface RoleApplicationRepository extends BaseRepository<RoleApplicationEntity> {

    RoleApplicationEntity getRegistrationRole(@NotNull final String name) throws Exception;
}
