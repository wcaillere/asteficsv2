package dal;

import dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DalSQL implements IDal {

    private final Connection cnx;
    private final Map<Class<?>, IDao<?>> dao;

    public DalSQL(Map<Class<?>, IDao<?>> dao, Connection cnx) {
        this.dao = dao;
        this.cnx = cnx;
    }

    @Override
    public Map<Class<?>, IDao<?>> getDao() {
        return dao;
    }

    public void initializeDB() {

        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();
        } catch (SQLException e) {
            System.out.println("Échec création statement");
            System.out.println(e.getMessage());
            System.exit(15);
        }

        String rqt = "SET FOREIGN_KEY_CHECKS = 0;" +
                "TRUNCATE TABLE categories;" +
                "TRUNCATE TABLE entries;" +
                "TRUNCATE TABLE formations;" +
                "TRUNCATE TABLE levels;" +
                "TRUNCATE TABLE students;" +
                "TRUNCATE TABLE teachers;" +
                "SET FOREIGN_KEY_CHECKS = 1";

        try {
            stmt.executeUpdate(rqt);
            System.out.println("\nRéinitialisation de la base réussie !");
        } catch (SQLException e) {
            System.out.println("Échec création statement");
            System.out.println(e.getMessage());
            System.exit(15);
        }
    }

    @Override
    public List<?> getAll(Class<?> entity) {
        return getDao().get(entity).getAll();
    }

    @Override
    public Object getOne(Class<?> entity, String id) {
        return getDao().get(entity).getOne(id);
    }

    @Override
    public void createOne(Class<?> entity, Object information) {
        getDao().get(entity).createOne(information);
    }

    @Override
    public void modifyOne(Class<?> entity, String id, Object information) {
        getDao().get(entity).modifyOne(id, information);
    }

    @Override
    public void suppressOne(Class<?> entity, String id) {
        getDao().get(entity).suppressOne(id);
    }
}
