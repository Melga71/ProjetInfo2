package com.mycompany.projetinfo2;

import java.sql.*;

public class ProduitDAO {

    private Connection conn;

    public ProduitDAO() {
        try {
            // Connexion à la base
            conn = DriverManager.getConnection("jdbc:sqlite:atelier.db");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion DAO : " + e.getMessage());
        }
    }

    // Ajouter un produit
    public void ajouterProduit(Produit p) {
        String sql = "INSERT INTO Produit(codeProduit, dProduit) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, p.getCodeProduit());
            pstmt.setString(2, p.getdProduit());
            pstmt.executeUpdate();
            System.out.println("Produit ajouté à la base !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout produit : " + e.getMessage());
        }
    }

    // Lire un produit à partir de son code
    public Produit getProduit(int code) {
        String sql = "SELECT * FROM Produit WHERE codeProduit = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, code);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("dProduit");
                return new Produit(code, nom);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lecture produit : " + e.getMessage());
        }
        return null;
    }

    // Supprimer un produit
    public void supprimerProduit(int code) {
        String sql = "DELETE FROM Produit WHERE codeProduit = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, code);
            pstmt.executeUpdate();
            System.out.println("Produit supprimé.");
        } catch (SQLException e) {
            System.out.println("Erreur suppression : " + e.getMessage());
        }
    }

    // Mettre à jour un produit
    public void modifierProduit(Produit p) {
        String sql = "UPDATE Produit SET dProduit = ? WHERE codeProduit = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getdProduit());
            pstmt.setInt(2, p.getCodeProduit());
            pstmt.executeUpdate();
            System.out.println("Produit modifié.");
        } catch (SQLException e) {
            System.out.println("Erreur modification : " + e.getMessage());
        }
    }
}
