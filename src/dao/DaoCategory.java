package dao;

import connection.SQLConnection;
import models.Category;
import models.Student;

import java.sql.*;
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
    public void createOne(Object information) {
        String rqt = "insert into categories (name_category) VALUES (?)";

        PreparedStatement prstmt = null;

        try {
            prstmt = this.cnx.prepareStatement(rqt);

            Category category = (Category) information;

            prstmt.setString(1, category.getName());
            prstmt.executeUpdate();

            System.out.println("Création de la catégorie réussie !");
        } catch (SQLException e) {
            System.out.println("erreur création de la catégorie : " + e.getMessage());
            System.exit(40);
        }
    }

    @Override
    public void modifyOne(String id, Object information) {

    }

    @Override
    public void suppressOne(String id) {

        String rqt = "DELETE FROM categories WHERE id=" + id;
        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id FROM categories WHERE id=" + id);

            if (rs.next()) {
                stmt = this.cnx.createStatement();
                stmt.executeUpdate(rqt);

                System.out.println("Suppression réussie !");
            } else {
                System.out.println("Suppression annulée : aucun élément ne possède l'id donné");
            }

        } catch (SQLException e) {
            System.out.println("Échec de la suppression");
            System.out.println(e.getMessage());
            System.exit(35);
        }
    }
}
