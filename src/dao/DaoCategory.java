package dao;

import connection.SQLConnection;
import models.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoCategory implements IDao<Category> {

    private final Connection cnx = new SQLConnection().getConnection();

    @Override
    public List<Category> getAll() {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.cnx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM categories");

        } catch (SQLException e) {
            System.out.println("erreur Récupération des catégories : " + e.getMessage());
            System.exit(15);
        }

        List<Category> results = new ArrayList<>();

        try {
            while (rs.next()) {
                Category result = new Category(
                        rs.getInt("id"),
                        rs.getString("name_category")
                );
                results.add(result);
            }
        } catch (SQLException e) {
            System.out.println("erreur création liste catégories : " + e.getMessage());
            System.exit(20);
        }

        return results;
    }

    @Override
    public Category getOne(String id) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.cnx.createStatement();
            rs = stmt.executeQuery("SELECT * FROM categories WHERE id=" + id);

        } catch (SQLException e) {
            System.out.println("erreur Récupération d'une catégorie : " + e.getMessage());
            System.exit(25);
        }

        Category result = null;

        try {
            if (rs.next()) {
                result = new Category(
                        rs.getInt("id"),
                        rs.getString("name_category")
                );
            }
        } catch (SQLException e) {
            System.out.println("erreur création liste catégories : " + e.getMessage());
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
