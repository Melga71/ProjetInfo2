package com.mycompany.projetinfo2;

public class Operateur {
    //Attributs
    int code; 
    String nom;
    String prenom;
    String competences;
    //Constructeur

    public Operateur(int code, String nom, String prenom, String competences) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.competences = competences;
    }
    
    //Getters & Setters

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }
    
}
