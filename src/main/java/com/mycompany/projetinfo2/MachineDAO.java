package com.mycompany.projetinfo2;

import java.sql.*;

public class MachineDAO {

    private Connection conn;

    public MachineDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:atelier.db");
        } catch (SQLException e) {
            System.out.println("Erreur connexion DAO Machine : " + e.getMessage());
        }
    }

    // Ajouter une machine
    public void ajouterMachine(Machine m) {
        String sql = """
            INSERT INTO Machine(refMachine, dMachine, type, x, y, coutHoraire, duree, dispo, etat)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, m.getRefMachine());
            pstmt.setString(2, m.getdMachine());
            pstmt.setString(3, m.getType());
            pstmt.setFloat(4, m.getX());
            pstmt.setFloat(5, m.getY());
            pstmt.setFloat(6, m.getC());
            pstmt.setFloat(7, m.getT());
            pstmt.setBoolean(8, m.dispo);
            pstmt.setString(9, m.getEtat());

            pstmt.executeUpdate();
            System.out.println("Machine ajoutée !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout machine : " + e.getMessage());
        }
    }

    // Lire une machine
    public Machine getMachine(String ref) {
        String sql = "SELECT * FROM Machine WHERE refMachine = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ref);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Machine(
                    rs.getString("refMachine"),
                    rs.getString("dMachine"),
                    rs.getString("type"),
                    rs.getFloat("x"),
                    rs.getFloat("y"),
                    rs.getFloat("coutHoraire"),
                    rs.getFloat("duree"),
                    rs.getBoolean("dispo"),
                    rs.getString("etat")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lecture machine : " + e.getMessage());
        }
        return null;
    }

    // Modifier une machine
    public void modifierMachine(Machine m) {
        String sql = """
            UPDATE Machine SET dMachine = ?, type = ?, x = ?, y = ?, coutHoraire = ?, duree = ?, dispo = ?, etat = ?
            WHERE refMachine = ?
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, m.getdMachine());
            pstmt.setString(2, m.getType());
            pstmt.setFloat(3, m.getX());
            pstmt.setFloat(4, m.getY());
            pstmt.setFloat(5, m.getC());
            pstmt.setFloat(6, m.getT());
            pstmt.setBoolean(7, m.dispo);
            pstmt.setString(8, m.getEtat());
            pstmt.setString(9, m.getRefMachine());

            pstmt.executeUpdate();
            System.out.println("Machine modifiée !");
        } catch (SQLException e) {
            System.out.println("Erreur modification : " + e.getMessage());
        }
    }

    // Supprimer une machine
    public void supprimerMachine(String ref) {
        String sql = "DELETE FROM Machine WHERE refMachine = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ref);
            pstmt.executeUpdate();
            System.out.println("Machine supprimée !");
        } catch (SQLException e) {
            System.out.println("Erreur suppression : " + e.getMessage());
        }
    }
}
