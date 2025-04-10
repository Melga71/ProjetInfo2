package com.mycompany.projetinfo2;
import java.util.ArrayList;

public class Poste extends Equipement{
    //Attributs
    int refPoste;
    String dPoste;
    ArrayList<Machine> listeMachines = new ArrayList<>();
    
    //Constructeur
     public Poste(String refPoste, String dPoste) {
        this.refEquipement = refPoste;
        this.dEquipement = dPoste;
    }
     //Méthodes 
     public void ajouterMachine(Machine m) {
        listeMachines.add(m);
    }
     
     @Override
     public void afficher() {
        System.out.println("Poste: " + refEquipement + ", " + dEquipement);
        for (Machine m : listeMachines) m.afficher();
    }

    
    //Getters & Setters
    public int getRefPoste() {
        return refPoste;
    }

    public void setRefPoste(int refPoste) {
        this.refPoste = refPoste;
    }

    public String getdPoste() {
        return dPoste;
    }

    public void setdPoste(String dPoste) {
        this.dPoste = dPoste;
    }
    
    
}
