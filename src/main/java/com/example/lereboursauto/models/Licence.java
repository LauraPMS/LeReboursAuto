package com.example.lereboursauto.models;

import java.sql.Date;

public class Licence {
    private int id;
    private Permis idCategorie;
    private Utilisateur idUtilisateur;
    private Date dateObtention;

    public Licence(int id, Permis idCategorie, Utilisateur idUtilisateur, Date dateObtention) {
        this.id = id;
        this.idCategorie = idCategorie;
        this.idUtilisateur = idUtilisateur;
        this.dateObtention = dateObtention;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Permis getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Permis idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
