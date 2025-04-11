package com.mycompany.projetinfo2;

import java.sql.*;

public class OperationDAO {

    private Connection conn;

    public OperationDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:atelier.db");
        } catch (SQLException e) {
            System.out.println("Erreur connexion DAO Operation : " + e.getMessage());
        }
    }

    // Ajouter une opération
    public void ajouterOperation(Operation o) {
        String sql = "INSERT INTO Operation(refOperation, dOperation, refEquipement, dureeOperation) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, o.getRefOperation());
            pstmt.setString(2, o.getdOperation());
            pstmt.setString(3, o.getEquipement().getRefEquipement()); // liaison par référence
            pstmt.setFloat(4, o.getDureeOperation());

            pstmt.executeUpdate();
            System.out.println("Opération ajoutée !");
        } catch (SQLException e) {
            System.out.println("Erreur ajout opération : " + e.getMessage());
        }
    }

    // Lire une opération
    public Operation getOperation(String ref) {
        String sql = "SELECT * FROM Operation WHERE refOperation = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ref);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // ⚠ Ici on n'a que le refEquipement → on peut le recharger plus tard
                String refEquipement = rs.getString("refEquipement");
                EquipementDAO eqDao = new EquipementDAO();
                Equipement equip = eqDao.getEquipementParReference(refEquipement);

                return new Operation(
                    rs.getString("refOperation"),
                    rs.getString("dOperation"),
                    equip,
                    rs.getFloat("dureeOperation")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lecture opération : " + e.getMessage());
        }
        return null;
    }

    // Modifier une opération
    public void modifierOperation(Operation o) {
        String sql = "UPDATE Operation SET dOperation = ?, refEquipement = ?, dureeOperation = ? WHERE refOperation = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, o.getdOperation());
            pstmt.setString(2, o.getEquipement().getRefEquipement());
            pstmt.setFloat(3, o.getDureeOperation());
            pstmt.setString(4, o.getRefOperation());

            pstmt.executeUpdate();
            System.out.println("Opération modifiée !");
        } catch (SQLException e) {
            System.out.println("Erreur modification : " + e.getMessage());
        }
    }

    // Supprimer une opération
    public void supprimerOperation(String ref) {
        String sql = "DELETE FROM Operation WHERE refOperation = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ref);
            pstmt.executeUpdate();
            System.out.println("Opération supprimée !");
        } catch (SQLException e) {
            System.out.println("Erreur suppression : " + e.getMessage());
        }
    }   
}
