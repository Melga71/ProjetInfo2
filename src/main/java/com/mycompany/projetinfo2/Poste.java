package com.mycompany.projetinfo2;
import java.util.ArrayList;

public class Poste extends Equipement{
    //Attributs
    int refPoste;
    String dPoste;
    ArrayList<Machine> listeMachines = new ArrayList<Machine>();
    
    //Constructeur
    public Poste(int refPoste, String dPoste) {
        this.refPoste = refPoste;
        this.dPoste = dPoste;
    }
     //Méthodes 

    
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
