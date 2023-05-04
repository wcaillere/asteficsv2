package dal;

import dao.IDao;

import java.util.List;
import java.util.Map;

public interface IDal {

    Map<Class<?>, IDao<?>> getDao();

    void initializeDB();

    List<?> getAll(Class<?> entity);

    Object getOne(Class<?> entity, String id);

    void createOne(Class<?> entity, Object information);

    void suppressOne(Class<?> entity, String id);

}
