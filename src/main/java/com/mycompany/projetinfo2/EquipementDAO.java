package com.mycompany.projetinfo2;

import java.sql.*;

public class EquipementDAO {

    private Connection conn;

    public EquipementDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:atelier.db");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion EquipementDAO : " + e.getMessage());
        }
    }

    public Equipement getEquipementParReference(String ref) {
        // 1. Cherche dans Machine
        System.out.println("Recherche équipement : " + ref);

        String sqlMachine = "SELECT * FROM Machine WHERE refMachine = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sqlMachine)) {
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
            System.out.println("Erreur recherche machine : " + e.getMessage());
        }

        // 2. Si pas trouvé, cherche dans Poste
        String sqlPoste = "SELECT * FROM Poste WHERE refPoste = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlPoste)) {
            pstmt.setString(1, ref);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Poste poste = new Poste(
                    rs.getString("refPoste"),
                    rs.getString("dPoste")
                );
                return poste;
            }
        } catch (SQLException e) {
            System.out.println("Erreur recherche poste : " + e.getMessage());
        }
        System.out.println("Aucun équipement trouvé avec la référence : " + ref);


        return null; // Aucun équipement trouvé
    }
}
