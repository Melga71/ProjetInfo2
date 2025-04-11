package com.mycompany.projetinfo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseManager {

    public static void creerTables() {
        String url = "jdbc:sqlite:atelier.db"; // Le fichier sera créé ici

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            System.out.println("Connexion à la base réussie.");

            // Table Produit
            String sqlProduit = """
                CREATE TABLE IF NOT EXISTS Produit (
                    codeProduit INTEGER PRIMARY KEY,
                    dProduit TEXT
                );
                """;

            // Table Machine
            String sqlMachine = """
                CREATE TABLE IF NOT EXISTS Machine (
                    refMachine TEXT PRIMARY KEY,
                    dMachine TEXT,
                    type TEXT,
                    x REAL,
                    y REAL,
                    coutHoraire REAL,
                    duree REAL,
                    dispo BOOLEAN,
                    etat TEXT
                );
                """;

            // Table Poste
            String sqlPoste = """
                CREATE TABLE IF NOT EXISTS Poste (
                    refPoste TEXT PRIMARY KEY,
                    dPoste TEXT
                );
                """;

            // Table Operation
            String sqlOperation = """
                CREATE TABLE IF NOT EXISTS Operation (
                    refOperation TEXT PRIMARY KEY,
                    dOperation TEXT,
                    refEquipement TEXT,
                    dureeOperation REAL
                );
                """;

            // Table Gamme
            String sqlGamme = """
                CREATE TABLE IF NOT EXISTS Gamme (
                    refGamme TEXT PRIMARY KEY
                );
                """;
            // Table Operateur
            String sqlOperateur ="""
                 CREATE TABLE IF NOT EXISTS Operateur (
                    code INTEGER PRIMARY KEY,
                    nom TEXT,
                    prenom TEXT,
                    competences TEXT
                    );
                    """;
                    

            // Table de liaison Gamme - Operation
            String sqlGammeOperation = """
                CREATE TABLE IF NOT EXISTS Gamme_Operation (
                    refGamme TEXT,
                    refOperation TEXT,
                    PRIMARY KEY (refGamme, refOperation)
                );
                """;

            // Exécution
            stmt.execute(sqlProduit);
            stmt.execute(sqlMachine);
            stmt.execute(sqlPoste);
            stmt.execute(sqlOperation);
            stmt.execute(sqlGamme);
            stmt.execute(sqlGammeOperation);

            System.out.println("Toutes les tables ont été créées avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la création des tables : " + e.getMessage());
        }
    }
}
