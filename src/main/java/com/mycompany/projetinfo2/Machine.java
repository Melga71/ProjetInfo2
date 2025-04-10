package com.mycompany.projetinfo2;

public class Machine extends Equipement {
    //Attributs
    String refMachine;
    String dMachine;
    String type;
    String etat;
    float x;
    float y;
    float c;
    float t;
    boolean dispo;
    
    
    //Constructeur
    public Machine(String refMachine, String dMachine, String type, float x, float y, float c, float t, boolean dispo, String etat) {
        this.refMachine = refMachine;
        this.dMachine = dMachine;
        this.type = type;
        this.x = x;
        this.y = y;
        this.c = c;
        this.t = t;
        this.dispo = dispo;
        this.etat = etat;
    }
    //Méthodes 
     @Override
    public void afficher() {
        System.out.println("Machine: " + refEquipement + ", " + dEquipement);
    }

    
    //Getters & Setters

    public String getRefMachine() {
        return refMachine;
    }

    public void setRefMachine(String refMachine) {
        this.refMachine = refMachine;
    }

    public String getdMachine() {
        return dMachine;
    }

    public void setdMachine(String dMachine) {
        this.dMachine = dMachine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    public String getRefEquipement() {
        return refEquipement;
    }

    public void setRefEquipement(String refEquipement) {
        this.refEquipement = refEquipement;
    }

    public String getdEquipement() {
        return dEquipement;
    }

    public void setdEquipement(String dEquipement) {
        this.dEquipement = dEquipement;
    }
    
    
}

