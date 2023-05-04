package dal;

import dao.*;
import models.Category;
import models.Formation;
import models.Student;
import models.Teacher;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DalSQL implements IDal {

    private Connection cnx;
    private Map<Class<?>, IDao<?>> dao;

    public DalSQL(Map<Class<?>, IDao<?>> dao, Connection cnx) {
        this.dao = dao;
        this.cnx = cnx;
    }

    @Override
    public Map<Class<?>, IDao<?>> getDao() {
        return dao;
    }

    public void InitializeDB() {

        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();
        } catch (SQLException e) {
            System.out.println("Echec création statement");
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
            System.out.println("\nInitialisation de la base réussie !");
        } catch (SQLException e) {
            System.out.println("Echec création statement");
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
    public void suppressOne(Class<?> entity, String id) {
        getDao().get(entity).suppressOne(id);
    }
}
