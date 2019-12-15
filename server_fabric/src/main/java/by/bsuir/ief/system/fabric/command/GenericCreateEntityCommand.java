package by.bsuir.ief.system.fabric.command;

import by.bsuir.ief.system.fabric.model.repository.BaseRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

public abstract class GenericCreateEntityCommand<T> extends BaseCreateCommand<T> {

    public abstract Class<T> getType();

    @Override
    protected void onCreate(T entity) throws Exception {
        BaseRepository<T> userRepository = RepositoryManager.getInstance().getRepository(getType());
        userRepository.create(entity);
    }

}
