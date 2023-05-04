package dao;

import java.util.List;

public interface IDao<T> {
    List<T> getAll();

    T getOne(String id);

    void createOne();

    void modifyOne(String id);

    void suppressOne(String id);
}
