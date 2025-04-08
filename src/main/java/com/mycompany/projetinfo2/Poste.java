package com.mycompany.projetinfo2;
import java.util.ArrayList;

public class Poste {
    //Attributs
    int refPoste;
    String dPoste;
    ArrayList<Machine> listMachines = new ArrayList<Machine>();
    
    //Constructeur
    public Poste(int refPoste, String dPoste) {
        this.refPoste = refPoste;
        this.dPoste = dPoste;
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

    public ArrayList<Machine> getListMachines() {
        return listMachines;
    }

    public void setListMachines(ArrayList<Machine> listMachines) {
        this.listMachines = listMachines;
    }
    
    
    
}
