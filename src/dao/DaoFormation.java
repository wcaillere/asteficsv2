package dao;

import connection.SQLConnection;
import models.Category;
import models.Formation;
import models.Level;
import models.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoFormation implements IDao<Formation> {

    private Connection cnx = new SQLConnection().getConnection();

    @Override
    public List<Formation> getAll() {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.cnx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM formations_details");

        } catch (SQLException e) {
            System.out.println("erreur Récupération des catégories : " + e.getMessage());
            System.exit(15);
        }

        List<Formation> results = new ArrayList<>();

        try {
            while (rs.next()) {
                Formation result = new Formation(
                        rs.getInt("id"),
                        rs.getString("name_category"),
                        rs.getDate("begin_at"),
                        rs.getInt("nbDays"),
                        rs.getFloat("price"),
                        new Level(rs.getInt("id_level"), rs.getString("name_level")),
                        rs.getBoolean("isOnline"),
                        rs.getString("program"),
                        new Category(rs.getInt("id_category"), rs.getString("name_category")),
                        new Teacher(rs.getInt("id_teacher"), rs.getString("firstName"), rs.getString("lastName"))
                );
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("erreur création liste catégories : " + e.getMessage());
            System.exit(15);
        }

        return results;
    }

    @Override
    public Formation getOne(String id) {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.cnx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM formations_details WHERE id=" + id);

        } catch (SQLException e) {
            System.out.println("erreur Récupération d'une formation : " + e.getMessage());
            System.exit(25);
        }

        Formation result = null;

        try {
            if (rs.next()) {
                result = new Formation(
                        rs.getInt("id"),
                        rs.getString("name_category"),
                        rs.getDate("begin_at"),
                        rs.getInt("nbDays"),
                        rs.getFloat("price"),
                        new Level(rs.getInt("id_level"), rs.getString("name_level")),
                        rs.getBoolean("isOnline"),
                        rs.getString("program"),
                        new Category(rs.getInt("id_category"), rs.getString("name_category")),
                        new Teacher(rs.getInt("id_teacher"), rs.getString("firstName"), rs.getString("lastName"))
                );
            }
        } catch (SQLException e) {
            System.out.println("erreur création liste formations : " + e.getMessage());
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
