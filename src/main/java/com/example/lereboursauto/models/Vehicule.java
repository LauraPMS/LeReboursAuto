package com.example.lereboursauto.models;

public class Vehicule {
    private String immatriculation;
    private String annee;
    private Permis permis;
    private String marque;
    private String modele;

    public Vehicule(String immatriculation, String annee, Permis permis, String marque, String modele) {
        this.immatriculation = immatriculation;
        this.annee = annee;
        this.permis = permis;
        this.marque = marque;
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Permis getPermis() {
        return permis;
    }

    public void setPermis(Permis permis) {
        this.permis = permis;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
}