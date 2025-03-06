package com.example.lereboursauto.models;

public class Permis {
    int id;
    String libelle;
    Float prix;

    public Permis(int id, String libelle, Float prix) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
}
