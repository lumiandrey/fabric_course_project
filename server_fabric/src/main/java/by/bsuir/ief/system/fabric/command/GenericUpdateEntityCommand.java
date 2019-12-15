package by.bsuir.ief.system.fabric.command;

import by.bsuir.ief.system.fabric.model.repository.BaseRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

public abstract class GenericUpdateEntityCommand<T> extends BaseUpdateCommand<T> {
    public abstract Class<T> getType();

    @Override
    protected T onUpdate(T entity) throws Exception {
        BaseRepository<T> repository = RepositoryManager.getInstance().getRepository(getType());
        return repository.update(entity);
    }

}
