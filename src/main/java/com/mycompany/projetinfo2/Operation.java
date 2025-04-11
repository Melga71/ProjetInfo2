package com.mycompany.projetinfo2;

public class Operation {
    //Attributs
    String refOperation;
    String dOperation; 
    Equipement equipement;
    float dureeOperation;
    
    //Constructeur
    public Operation(String refOperation, String dOperation, Equipement equipement, float dureeOperation) {
        this.refOperation = refOperation;
        this.dOperation = dOperation;
        this.equipement = equipement;
        this.dureeOperation = dureeOperation;
    }
    
    //Méthodes
    public float getCout() {
        if (equipement instanceof Machine) {
            Machine m = (Machine) equipement;
            return dureeOperation * m.getC();
        }
        return 0;
    }
    
    
    
    //Setters & Getters
    public String getRefOperation() {
        return refOperation;
    }

    public void setRefOperation(String refOperation) {
        this.refOperation = refOperation;
    }

    public String getdOperation() {
        return dOperation;
    }

    public void setdOperation(String dOperation) {
        this.dOperation = dOperation;
    }


    public void setRefEquipement(Equipement refEquipement) {
        this.equipement = equipement;
    }

    public float getDureeOperation() {
        return dureeOperation;
    }

    public void setDureeOperation(float dureeOperation) {
        this.dureeOperation = dureeOperation;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }
    
    
    
}
