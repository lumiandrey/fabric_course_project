package by.bsuir.ief.system.fabric.model.repository.user;


import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;

public interface UserRepository extends BaseRepository<UserEntity> {

    boolean checkLogin(final String login) throws Exception;

    UserEntity getPairLoginPassword(final String login, final String password) throws Exception;
}
