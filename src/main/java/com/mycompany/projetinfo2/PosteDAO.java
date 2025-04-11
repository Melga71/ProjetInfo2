package com.mycompany.projetinfo2;

import java.sql.*;

public class PosteDAO {

    private Connection conn;

    public PosteDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:atelier.db");
        } catch (SQLException e) {
            System.out.println("Erreur connexion DAO Poste : " + e.getMessage());
        }
    }

    // Ajouter un poste
    public void ajouterPoste(Poste p) {
        String sql = "INSERT INTO Poste(refPoste, dPoste) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getRefPoste());
            pstmt.setString(2, p.getdPoste());
            pstmt.executeUpdate();
            System.out.println("Poste ajouté !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout poste : " + e.getMessage());
        }
    }

    // Lire un poste
    public Poste getPoste(String ref) {
        String sql = "SELECT * FROM Poste WHERE refPoste = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ref);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Poste(
                    rs.getString("refPoste"),
                    rs.getString("dPoste")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lecture poste : " + e.getMessage());
        }
        return null;
    }

    // Modifier un poste
    public void modifierPoste(Poste p) {
        String sql = "UPDATE Poste SET dPoste = ? WHERE refPoste = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getdPoste());
            pstmt.setString(2, p.getRefPoste());
            pstmt.executeUpdate();
            System.out.println("Poste modifié !");
        } catch (SQLException e) {
            System.out.println("Erreur modification poste : " + e.getMessage());
        }
    }

    // Supprimer un poste
    public void supprimerPoste(String ref) {
        String sql = "DELETE FROM Poste WHERE refPoste = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ref);
            pstmt.executeUpdate();
            System.out.println("Poste supprimé !");
        } catch (SQLException e) {
            System.out.println("Erreur suppression poste : " + e.getMessage());
        }
    }
}
