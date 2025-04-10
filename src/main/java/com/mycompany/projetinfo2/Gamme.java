package com.mycompany.projetinfo2;
import java.util.ArrayList;

public class Gamme {
    //Attributs
    String refGamme;
    ArrayList<Operation> listeOperations = new ArrayList<>();
    ArrayList<Equipement> listeEquipement = new ArrayList<>();
    
    //Constructeur
    public Gamme(String refGamme) {
        this.refGamme = refGamme;
    }
    
    //Méthodes
     public void ajouterOperation(Operation op) {
        listeOperations.add(op);
    }

    public float dureeTotale() {
        float total = 0;
        for (Operation op : listeOperations) total += op.dureeOperation;
        return total;
    }

    public float coutTotal() {
        float total = 0;
        for (Operation op : listeOperations) total += op.getCout();
        return total;
    }

    public void afficherGamme() {
        System.out.println("Gamme: " + refGamme);
        for (Operation op : listeOperations) {
            System.out.println("Op: " + op.refOperation + ", Duree: " + op.dureeOperation + ", Cout: " + op.getCout());
        }
    }
    
    
    
    
    //Getters & Setters 
    public String getRefGamme() {
        return refGamme;
    }

    public void setRefGamme(String refGamme) {
        this.refGamme = refGamme;
    }

    public ArrayList<Operation> getListeOperations() {
        return listeOperations;
    }

    public void setListeOperations(ArrayList<Operation> listeOperations) {
        this.listeOperations = listeOperations;
    }

    public ArrayList<Equipement> getListeEquipement() {
        return listeEquipement;
    }

    public void setListeEquipement(ArrayList<Equipement> listeEquipement) {
        this.listeEquipement = listeEquipement;
    }
    
    
}
