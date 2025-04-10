package com.mycompany.projetinfo2;

public class Produit {
    //Attributs
    int codeProduit;
    String dProduit;
    
    //Constructeur
    public Produit(int codeProduit, String dProduit) {
        this.codeProduit = codeProduit;
        this.dProduit = dProduit;
    }
    //Méthodes 
    public void afficherProduit() {
        System.out.println("Produit: " + codeProduit + ", " + dProduit);
    }
    //Getters & Setters

    public int getCodeProduit() {
        return codeProduit;
    }

    public String getdProduit() {
        return dProduit;
    }

    public void setCodeProduit(int codeProduit) {
        this.codeProduit = codeProduit;
    }

    public void setdProduit(String dProduit) {
        this.dProduit = dProduit;
    }
    
   
    
}
