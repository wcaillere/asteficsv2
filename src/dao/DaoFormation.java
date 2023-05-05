package dao;

import connection.SQLConnection;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoFormation implements IDao<Formation> {

    private final Connection cnx = new SQLConnection().getConnection();

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
                        rs.getString("name_formation"),
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
    public void createOne(Object information) {
        String rqt = "insert into formations (name_formation, begin_at, nbDays, price, id_level, isOnline, program, id_category, id_teacher) " +
                "VALUES (? , ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prstmt = null;

        try {
            prstmt = this.cnx.prepareStatement(rqt);

            Formation formation = (Formation) information;

            prstmt.setString(1, formation.getName());
            prstmt.setDate(2, new Date(formation.getBegin_at().getTime()));
            prstmt.setInt(3, formation.getNbDays());
            prstmt.setFloat(4, formation.getPrice());
            prstmt.setInt(5, formation.getLevel().getId());
            prstmt.setBoolean(6, formation.isOnline());
            prstmt.setString(7, formation.getProgram());
            prstmt.setInt(8, formation.getCategory().getId());
            prstmt.setInt(9, formation.getTeacher().getId());

            prstmt.executeUpdate();

            System.out.println("Création de la formation réussie !");
        } catch (SQLException e) {
            System.out.println("erreur création de la formation : " + e.getMessage());
            System.exit(40);
        }
    }

    @Override
    public void modifyOne(String id, Object information) {

        Formation foundedObject = getOne(id);
        Formation modifications = (Formation) information;

        String rqt = "UPDATE formations SET name_formation=?, begin_at=?, nbDays=?, price=?, id_level=?, isOnline=?, program=?, id_category=?, id_teacher=? WHERE id=?";
        PreparedStatement prstmt = null;

        try {
            prstmt = this.cnx.prepareStatement(rqt);

            prstmt.setString(1, modifications.getName() != null ? modifications.getName() : foundedObject.getName());
            prstmt.setDate(2, modifications.getBegin_at() != null ?
                    new Date(modifications.getBegin_at().getTime()) :
                    new Date(foundedObject.getBegin_at().getTime()));
            prstmt.setInt(3, modifications.getNbDays() != 0 ? modifications.getNbDays() : foundedObject.getNbDays());
            prstmt.setFloat(4, modifications.getPrice() != 0.0 ? modifications.getPrice() : foundedObject.getPrice());
            prstmt.setInt(5, modifications.getLevel() != null ? modifications.getLevel().getId() : foundedObject.getLevel().getId());
            prstmt.setBoolean(6, modifications.isOnline() != null ? modifications.isOnline() : foundedObject.isOnline());
            prstmt.setString(7, modifications.getProgram() != null ? modifications.getProgram() : foundedObject.getProgram());
            prstmt.setInt(8, modifications.getCategory() != null ? modifications.getCategory().getId() : foundedObject.getCategory().getId());
            prstmt.setInt(9, modifications.getTeacher() != null ? modifications.getTeacher().getId() : foundedObject.getTeacher().getId());
            prstmt.setString(10, id);

            prstmt.executeUpdate();

            System.out.println("Modification réussie !");
        } catch (SQLException e) {
            System.out.println("Échec de la modification : " + e.getMessage());
            System.exit(45);
        }
    }

    @Override
    public void suppressOne(String id) {

        String rqt = "DELETE FROM formations WHERE id=" + id;
        Statement stmt = null;

        try {
            stmt = this.cnx.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id FROM formations WHERE id=" + id);

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
