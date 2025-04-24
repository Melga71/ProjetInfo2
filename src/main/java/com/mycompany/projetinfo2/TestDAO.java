package com.mycompany.projetinfo2;

public class TestDAO {

    // Active ou désactive les suppressions à la fin des tests
    private static final boolean cleanUp = false; // passe à true pour nettoyer

    public static void main(String[] args) {
        System.out.println("=== TEST PRODUIT ===");
        testProduit();

        System.out.println("\n=== TEST MACHINE ===");
        testMachine();

        System.out.println("\n=== TEST POSTE ===");
        testPoste();

        System.out.println("\n=== TEST OPERATEUR ===");
        testOperateur();

        System.out.println("\n=== TEST OPERATION ===");
        testOperation();

        System.out.println("\n=== TEST GAMME ===");
        testGamme();
    }

    public static void testProduit() {
        ProduitDAO dao = new ProduitDAO();
        Produit p = new Produit(1, "Produit Test");
        dao.ajouterProduit(p);
        Produit lu = dao.getProduit(1);
        System.out.println("Lu : " + lu.getdProduit());
        lu.setdProduit("Produit Modifié");
        dao.modifierProduit(lu);
        if (cleanUp) dao.supprimerProduit(1);
    }

    public static void testMachine() {
        MachineDAO dao = new MachineDAO();
        dao.supprimerMachine("M001"); // évite les doublons
        Machine m = new Machine("M001", "Machine Test", "type", 0, 0, 10, 1, true, "ok");
        dao.ajouterMachine(m);
        Machine lu = dao.getMachine("M001");
        System.out.println("Lu : " + lu.getdMachine());
        lu.setdMachine("Machine Modifiée");
        dao.modifierMachine(lu);
        if (cleanUp) dao.supprimerMachine("M001");
    }

    public static void testPoste() {
        PosteDAO dao = new PosteDAO();
        dao.supprimerPoste("P001");
        Poste p = new Poste("P001", "Poste Test");
        dao.ajouterPoste(p);
        Poste lu = dao.getPoste("P001");
        System.out.println("Lu : " + lu.getdPoste());
        lu.setdPoste("Poste Modifié");
        dao.modifierPoste(lu);
        if (cleanUp) dao.supprimerPoste("P001");
    }

    public static void testOperateur() {
        OperateurDAO dao = new OperateurDAO();
        dao.supprimerOperateur(101);
        Operateur o = new Operateur(101, "Jean", "Dupont", "tout faire");
        dao.ajouterOperateur(o);
        Operateur lu = dao.getOperateur(101);
        System.out.println("Lu : " + lu.getPrenom() + " " + lu.getNom());
        lu.setCompetences("moins");
        dao.modifierOperateur(lu);
        if (cleanUp) dao.supprimerOperateur(101);
    }

    public static void testOperation() {
        OperationDAO dao = new OperationDAO();
        MachineDAO machineDAO = new MachineDAO();

        Machine m = new Machine("M002", "Machine Op", "perçage", 1, 1, 12, 2, true, "ok");
        machineDAO.supprimerMachine("M002");
        machineDAO.ajouterMachine(m);

        dao.supprimerOperation("OP1");
        Operation o = new Operation("OP1", "Op test", m, 2.5f);
        dao.ajouterOperation(o);

        Operation lu = dao.getOperation("OP1");
        if (lu != null && lu.getEquipement() != null) {
            System.out.println("Lu : " + lu.getdOperation());
        } else {
            System.out.println("⚠ Opération ou équipement introuvable !");
        }

        if (cleanUp) {
            dao.supprimerOperation("OP1");
            machineDAO.supprimerMachine("M002");
        }
    }

    public static void testGamme() {
        GammeDAO dao = new GammeDAO();
        MachineDAO machineDAO = new MachineDAO();
        OperationDAO operationDAO = new OperationDAO();

        machineDAO.supprimerMachine("M100");
        machineDAO.supprimerMachine("M101");

        Machine m1 = new Machine("M100", "M pour G", "type", 1, 1, 10, 1, true, "ok");
        Machine m2 = new Machine("M101", "M pour G 2", "type", 2, 2, 10, 1, true, "ok");

        machineDAO.ajouterMachine(m1);
        machineDAO.ajouterMachine(m2);

        Operation o1 = new Operation("OG1", "op 1", m1, 1.0f);
        Operation o2 = new Operation("OG2", "op 2", m2, 2.0f);

        operationDAO.supprimerOperation("OG1");
        operationDAO.supprimerOperation("OG2");

        Gamme g = new Gamme("G001");
        g.getListeOperations().add(o1);
        g.getListeOperations().add(o2);

        dao.ajouterGamme(g);

        Gamme gLue = dao.getGamme("G001");
        System.out.println("Opérations dans la gamme lue :");
        for (Operation o : gLue.getListeOperations()) {
            System.out.println("- " + o.getdOperation());
        }

        if (cleanUp) {
            dao.supprimerGamme("G001");
            operationDAO.supprimerOperation("OG1");
            operationDAO.supprimerOperation("OG2");
            machineDAO.supprimerMachine("M100");
            machineDAO.supprimerMachine("M101");
        }
    }
}
