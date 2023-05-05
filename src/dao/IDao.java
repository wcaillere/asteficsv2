package dao;

import java.util.List;

public interface IDao<T> {
    List<T> getAll();

    T getOne(String id);

    void createOne(Object information);

    void modifyOne(String id, Object information);

    void suppressOne(String id);
}
