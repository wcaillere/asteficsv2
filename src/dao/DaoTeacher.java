package dao;

import connection.SQLConnection;
import models.Teacher;

import java.sql.*;
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
    public void createOne(Object information) {
        String rqt = "insert into teachers (firstName, lastName) VALUES (? , ?)";

        PreparedStatement prstmt = null;

        try {
            prstmt = this.cnx.prepareStatement(rqt);

            Teacher teacher = (Teacher) information;

            prstmt.setString(1, teacher.getFirstName());
            prstmt.setString(2, teacher.getLastName());

            prstmt.executeUpdate();

            System.out.println("Création du formateur réussie !");
        } catch (SQLException e) {
            System.out.println("erreur création formateur : " + e.getMessage());
            System.exit(40);
        }
    }

    @Override
    public void modifyOne(String id) {

    }

    @Override
    public void suppressOne(String id) {

        String rqt = "DELETE FROM teachers WHERE id=" + id;
        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id FROM teachers WHERE id=" + id);

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
