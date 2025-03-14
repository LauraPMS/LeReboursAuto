package com.example.lereboursauto.models;

import java.sql.Date;

public class Lecon {
    private int id;
    private Date date;
    private Vehicule immatriculation;
    private int reglee;
    private Utilisateur eleve;
    private Utilisateur moniteur;
    private Permis permis;
    private String heure;

    public Lecon(int id, Date date, Vehicule immatriculation, int reglee, Utilisateur eleve, Utilisateur moniteur, Permis permis, String heure) {
        this.id = id;
        this.date = date;
        this.immatriculation = immatriculation;
        this.reglee = reglee;
        this.eleve = eleve;
        this.moniteur = moniteur;
        this.permis = permis;
        this.heure = heure;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vehicule getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(Vehicule immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getReglee() {
        return reglee;
    }

    public void setReglee(int reglee) {
        this.reglee = reglee;
    }

    public Utilisateur getEleve() {
        return eleve;
    }

    public void setEleve(Utilisateur eleve) {
        this.eleve = eleve;
    }

    public Utilisateur getMoniteur() {
        return moniteur;
    }

    public void setMoniteur(Utilisateur moniteur) {
        this.moniteur = moniteur;
    }

    public Permis getPermis() {
        return permis;
    }

    public void setPermis(Permis permis) {
        this.permis = permis;
    }
}