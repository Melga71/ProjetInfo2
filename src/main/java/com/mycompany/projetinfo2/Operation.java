package com.mycompany.projetinfo2;

public class Operation {
    //Attributs
    String refOperation;
    String dOperation; 
    Equipement refEquipement;
    float dureeOperation;
    
    //Constructeur
    public Operation(String refOperation, String dOperation, Equipement refEquipement, float dureeOperation) {
        this.refOperation = refOperation;
        this.dOperation = dOperation;
        this.refEquipement = refEquipement;
        this.dureeOperation = dureeOperation;
    }
    
    //Méthodes
    
    
    
    
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
        this.refEquipement = refEquipement;
    }

    public float getDureeOperation() {
        return dureeOperation;
    }

    public void setDureeOperation(float dureeOperation) {
        this.dureeOperation = dureeOperation;
    }
    
    
    
}
