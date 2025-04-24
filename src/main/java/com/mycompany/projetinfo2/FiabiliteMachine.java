package com.mycompany.projetinfo2;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.*;


public class FiabiliteMachine {

    static class Evenement {
        LocalTime heure;
        String machine;
        String typeEvenement; // A ou D

        public Evenement(String heureStr, String machine, String typeEvenement) {
            this.heure = LocalTime.parse(heureStr);
            this.machine = machine;
            this.typeEvenement = typeEvenement;
        }
    }

    static class StatMachine {
        String machine;
        Duration tempsPanne = Duration.ZERO;
        Duration observation = Duration.between(LocalTime.of(6, 0), LocalTime.of(20, 0));

        public double getFiabilite() {
            long fonctionnement = observation.minus(tempsPanne).toMinutes();
            return (double) fonctionnement / observation.toMinutes();
        }
    }

    public static void main(String[] args) {
        String fichier = "suiviMaintenance.txt";
        Map<String, StatMachine> stats = new HashMap<>();
        Map<String, LocalTime> enPanneDepuis = new HashMap<>();
          List<StatMachine> machinesTriees = new ArrayList<>(stats.values());

        try {
            List<String> lignes = Files.readAllLines(Paths.get(fichier));
            for (String ligne : lignes) {
                if (ligne.trim().isEmpty()) continue;
                String[] parts = ligne.split(" ");
                if (parts.length < 5) continue;

                String heure = parts[1];
                String machine = parts[2];
                String evenement = parts[3];

                Evenement ev = new Evenement(heure, machine, evenement);

                stats.putIfAbsent(ev.machine, new StatMachine());
                stats.get(ev.machine).machine = ev.machine;

                if (ev.typeEvenement.equals("A")) {
                    enPanneDepuis.put(ev.machine, ev.heure);
                } else if (ev.typeEvenement.equals("D")) {
                    LocalTime debutPanne = enPanneDepuis.get(ev.machine);
                    if (debutPanne != null) {
                        Duration duree = Duration.between(debutPanne, ev.heure);
                        stats.get(ev.machine).tempsPanne = stats.get(ev.machine).tempsPanne.plus(duree);
                        enPanneDepuis.remove(ev.machine);
                    }
                }
            }

            // Affichage trié par fiabilité décroissante
          
            machinesTriees.sort(Comparator.comparingDouble(StatMachine::getFiabilite).reversed());

            System.out.println("=== Fiabilité des machines ===");
            for (StatMachine stat : machinesTriees) {
                System.out.printf("%s : %.2f %%\n", stat.machine, stat.getFiabilite() * 100);
            }

        } catch (IOException e) {
            System.out.println("Erreur lecture fichier : " + e.getMessage());
        }
        // Connexion pour insertion des résultats
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:atelier.db")) {
        String insert = "INSERT INTO Fiabilite(refMachine, fiabilite, dateCalc) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insert);
        String dateCalc = LocalDate.now().toString();

        for (StatMachine stat : machinesTriees) {
            pstmt.setString(1, stat.machine);
            pstmt.setDouble(2, stat.getFiabilite() * 100);
            pstmt.setString(3, dateCalc);
            pstmt.executeUpdate();
    }

    System.out.println("Résultats de fiabilité enregistrés dans la base !");
} catch (SQLException e) {
    System.out.println("Erreur lors de l'enregistrement des fiabilités : " + e.getMessage());
}

    }
}
