package com.mycompany.projetinfo2;

import java.sql.*;
import java.util.ArrayList;

public class GammeDAO {

    private Connection conn;
    private OperationDAO operationDAO = new OperationDAO();

    public GammeDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:atelier.db");
        } catch (SQLException e) {
            System.out.println("Erreur connexion DAO Gamme : " + e.getMessage());
        }
    }

    // Ajouter une gamme
    public void ajouterGamme(Gamme g) {
        String sqlGamme = "INSERT INTO Gamme(refGamme) VALUES (?)";
        String sqlLiaison = "INSERT INTO Gamme_Operation(refGamme, refOperation) VALUES (?, ?)";

        try (PreparedStatement pstmt1 = conn.prepareStatement(sqlGamme);
             PreparedStatement pstmt2 = conn.prepareStatement(sqlLiaison)) {

            // 1. Ajouter la gamme elle-même
            pstmt1.setString(1, g.getRefGamme());
            pstmt1.executeUpdate();

            // 2. Ajouter les opérations liées
            for (Operation op : g.getListeOperations()) {
                // D'abord enregistrer l'opération si elle n'existe pas
                operationDAO.ajouterOperation(op);

                // Puis créer la liaison
                pstmt2.setString(1, g.getRefGamme());
                pstmt2.setString(2, op.getRefOperation());
                pstmt2.executeUpdate();
            }

            System.out.println("Gamme ajoutée avec ses opérations !");

        } catch (SQLException e) {
            System.out.println("Erreur ajout gamme : " + e.getMessage());
        }
    }

    // Lire une gamme
    public Gamme getGamme(String ref) {
        Gamme gamme = new Gamme(ref);
        String sql = "SELECT refOperation FROM Gamme_Operation WHERE refGamme = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ref);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String refOp = rs.getString("refOperation");
                Operation op = operationDAO.getOperation(refOp);
                if (op != null) {
                    gamme.getListeOperations().add(op);
                }
            }

            System.out.println("Gamme chargée avec opérations.");

        } catch (SQLException e) {
            System.out.println("Erreur lecture gamme : " + e.getMessage());
        }

        return gamme;
    }

    // Supprimer une gamme
    public void supprimerGamme(String ref) {
        String sqlLiaison = "DELETE FROM Gamme_Operation WHERE refGamme = ?";
        String sqlGamme = "DELETE FROM Gamme WHERE refGamme = ?";

        try (PreparedStatement pstmt1 = conn.prepareStatement(sqlLiaison);
             PreparedStatement pstmt2 = conn.prepareStatement(sqlGamme)) {

            pstmt1.setString(1, ref);
            pstmt1.executeUpdate();

            pstmt2.setString(1, ref);
            pstmt2.executeUpdate();

            System.out.println("Gamme supprimée.");

        } catch (SQLException e) {
            System.out.println("Erreur suppression gamme : " + e.getMessage());
        }
    }
}
