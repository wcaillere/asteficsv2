package dao;

import connection.SQLConnection;
import models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoStudent implements IDao<Student> {

    private final Connection cnx = new SQLConnection().getConnection();

    @Override
    public List<Student> getAll() {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.cnx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM students");

        } catch (SQLException e) {
            System.out.println("erreur Récupération des étudiants : " + e.getMessage());
            System.exit(15);
        }

        List<Student> results = new ArrayList<>();

        try {
            while (rs.next()) {
                Student result = new Student(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("erreur création liste étudiants : " + e.getMessage());
            System.exit(15);
        }

        return results;
    }

    @Override
    public Student getOne(String id) {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.cnx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM students WHERE id=" + id);

        } catch (SQLException e) {
            System.out.println("erreur Récupération d'un étudiant : " + e.getMessage());
            System.exit(25);
        }

        Student result = null;

        try {
            if (rs.next()) {
                result = new Student(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
            }
        } catch (SQLException e) {
            System.out.println("erreur création liste étudiants : " + e.getMessage());
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
