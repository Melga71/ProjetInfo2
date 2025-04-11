package com.mycompany.projetinfo2;

import java.sql.*;

public class OperateurDAO {

    private Connection conn;

    public OperateurDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:atelier.db");
        } catch (SQLException e) {
            System.out.println("Erreur connexion DAO Operateur : " + e.getMessage());
        }
    }

    // Ajouter un opérateur
    public void ajouterOperateur(Operateur o) {
        String sql = "INSERT INTO Operateur(code, nom, prenom, competences) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, o.getCode());
            pstmt.setString(2, o.getNom());
            pstmt.setString(3, o.getPrenom());
            pstmt.setString(4, o.getCompetences());

            pstmt.executeUpdate();
            System.out.println("Opérateur ajouté !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout opérateur : " + e.getMessage());
        }
    }

    // Lire un opérateur
    public Operateur getOperateur(int code) {
        String sql = "SELECT * FROM Operateur WHERE code = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, code);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Operateur(
                    rs.getInt("code"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("competences")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lecture opérateur : " + e.getMessage());
        }
        return null;
    }

    // Modifier un opérateur
    public void modifierOperateur(Operateur o) {
        String sql = "UPDATE Operateur SET nom = ?, prenom = ?, competences = ? WHERE code = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, o.getNom());
            pstmt.setString(2, o.getPrenom());
            pstmt.setString(3, o.getCompetences());
            pstmt.setInt(4, o.getCode());

            pstmt.executeUpdate();
            System.out.println("Opérateur modifié !");
        } catch (SQLException e) {
            System.out.println("Erreur modification : " + e.getMessage());
        }
    }

    // Supprimer un opérateur
    public void supprimerOperateur(int code) {
        String sql = "DELETE FROM Operateur WHERE code = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, code);
            pstmt.executeUpdate();
            System.out.println("Opérateur supprimé !");
        } catch (SQLException e) {
            System.out.println("Erreur suppression : " + e.getMessage());
        }
    }
}
