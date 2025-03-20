package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.services.UtilisateurServices;

import java.sql.SQLException;
import java.util.ArrayList;

public class UtilisateurController {
    UtilisateurServices service = new UtilisateurServices();

    public UtilisateurController() {
        service = new UtilisateurServices();
    }

    public Utilisateur findByCode(int id) throws SQLException {
        return service.findByCode(id);
    }
    public void create(Utilisateur utilisateur) throws SQLException {
        service.create(utilisateur);
    }
    public void update(Utilisateur utilisateur) throws SQLException {
        service.update(utilisateur);
    }
    public void delete(Utilisateur utilisateur) throws SQLException {
        service.delete(utilisateur);
    }

    public ArrayList<Utilisateur> getALlMoniteurAvecLicence(int idPremis) throws SQLException {
        return service.getALlMoniteurAvecLicence(idPremis);
    }
}
