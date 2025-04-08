package com.mycompany.projetinfo2;
import java.util.ArrayList;

public class Gamme {
    //Attributs
    String refGamme;
    ArrayList<Operation> listeOperations = new ArrayList<Operation>();
    ArrayList<Equipement> listeEquipement = new ArrayList<Equipement>();
    
    //Constructeur
    public Gamme(String refGamme) {
        this.refGamme = refGamme;
    }
    
    //Méthodes
    
    
    
    
    
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
