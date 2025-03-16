package com.example.lereboursauto.models;

import java.sql.Date;

public class Lecon {
    private int id;
    private Date date;
    private String immatriculation;
    private int reglee;
    private Utilisateur eleve;
    private Utilisateur moniteur;
    private Permis permis;
    private String heure;
    private String nomPermis;
    private String modeleVehicule;
    private String nomPrenomMoniteur;
    private String nomPrenomEleve;

    public Lecon(int id, Date date, String immatriculation, int reglee, Utilisateur eleve, Utilisateur moniteur, Permis permis, String heure, String nomPermis, String modeleVehicule, String nomPrenomMoniteur, String nomPrenomEleve) {
        this.id = id;
        this.date = date;
        this.immatriculation = immatriculation;
        this.reglee = reglee;
        this.eleve = eleve;
        this.moniteur = moniteur;
        this.permis = permis;
        this.heure = heure;
        this.nomPermis = nomPermis;
        this.modeleVehicule = modeleVehicule;
        this.nomPrenomMoniteur = nomPrenomMoniteur;
        this.nomPrenomEleve = nomPrenomEleve;
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

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
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

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getNomPermis() {
        return nomPermis;
    }

    public void setNomPermis(String nomPermis) {
        this.nomPermis = nomPermis;
    }

    public String getModeleVehicule() {
        return modeleVehicule;
    }

    public void setModeleVehicule(String modeleVehicule) {
        this.modeleVehicule = modeleVehicule;
    }

    public String getNomPrenomMoniteur() {
        return nomPrenomMoniteur;
    }

    public void setNomPrenomMoniteur(String nomPrenomMoniteur) {
        this.nomPrenomMoniteur = nomPrenomMoniteur;
    }

    public String getNomPrenomEleve() {
        return nomPrenomEleve;
    }

    public void setNomPrenomEleve(String nomPrenomEleve) {
        this.nomPrenomEleve = nomPrenomEleve;
    }
}