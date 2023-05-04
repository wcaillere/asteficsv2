package dao;

import connection.SQLConnection;
import models.Category;
import models.Student;
import models.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoTeacher implements IDao<Teacher> {

    private final Connection cnx = new SQLConnection().getConnection();

    @Override
    public List<Teacher> getAll() {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.cnx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM teachers");

        } catch (SQLException e) {
            System.out.println("erreur Récupération des formateurs : " + e.getMessage());
            System.exit(15);
        }

        List<Teacher> results = new ArrayList<>();

        try {
            while (rs.next()) {
                Teacher result = new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("erreur création liste formateurs : " + e.getMessage());
            System.exit(15);
        }

        return results;
    }

    @Override
    public Teacher getOne(String id) {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.cnx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM teachers WHERE id=" + id);

        } catch (SQLException e) {
            System.out.println("erreur Récupération d'un formateur : " + e.getMessage());
            System.exit(25);
        }

        Teacher result = null;

        try {
            if (rs.next()) {
                result = new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
            }
        } catch (SQLException e) {
            System.out.println("erreur création liste formateurs : " + e.getMessage());
            System.exit(30);
        }

        return result;
    }

    @Override
    public void createOne() {

    }

    @Override
    public void modifyOne(String id) {

    }

    @Override
    public void suppressOne(String id) {

    }
}
