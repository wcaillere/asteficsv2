package dao;

import connection.SQLConnection;
import models.*;

import java.sql.*;
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
    public void createOne(Object information) {
        String rqt = "insert into students (firstName, lastName) VALUES (? , ?)";

        PreparedStatement prstmt = null;

        try {
            prstmt = this.cnx.prepareStatement(rqt);

            Student student = (Student) information;

            prstmt.setString(1, student.getFirstName());
            prstmt.setString(2, student.getLastName());

            prstmt.executeUpdate();

            System.out.println("Création de l'étudiant réussie !");
        } catch (SQLException e) {
            System.out.println("erreur création de l'étudiant : " + e.getMessage());
            System.exit(40);
        }
    }

    @Override
    public void modifyOne(String id, Object information) {

        Student foundedObject = getOne(id);
        Student modifications = (Student) information;

        String rqt = "UPDATE students SET firstName=?, lastName=? WHERE id=?";
        PreparedStatement prstmt = null;

        try {
            prstmt = this.cnx.prepareStatement(rqt);

            prstmt.setString(1, modifications.getFirstName() != null ? modifications.getFirstName() : foundedObject.getFirstName());
            prstmt.setString(2, modifications.getLastName() != null ? modifications.getLastName() : foundedObject.getLastName());
            prstmt.setString(3, id);

            prstmt.executeUpdate();

            System.out.println("Modification réussie !");
        } catch (SQLException e) {
            System.out.println("Échec de la modification : " + e.getMessage());
            System.exit(45);
        }
    }

    @Override
    public void suppressOne(String id) {

        String rqt = "DELETE FROM students WHERE id=" + id;
        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id FROM students WHERE id=" + id);

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
