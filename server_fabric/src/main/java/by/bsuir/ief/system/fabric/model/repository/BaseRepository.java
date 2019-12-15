package by.bsuir.ief.system.fabric.model.repository;

import java.util.List;

public interface BaseRepository<T> {

    long create(T entity) throws Exception;

    T read(int id) throws Exception;

    List<T> read() throws Exception;

    T update(T updateEntity) throws Exception;

    boolean delete(T deleteEntity) throws Exception;

    boolean delete(int idEntity) throws Exception;
}
