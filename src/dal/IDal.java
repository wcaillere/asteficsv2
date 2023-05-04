package dal;

import dao.IDao;

import java.util.List;
import java.util.Map;

public interface IDal {

    Map<Class<?>, IDao<?>> getDao();

    public void InitializeDB();

    List<?> getAll(Class<?> entity);

    public Object getOne(Class<?> Entity, String id);

}
