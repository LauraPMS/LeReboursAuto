package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.services.UtilisateurServices;

import java.sql.SQLException;
import java.util.ArrayList;

public class UtilisateurController {
    UtilisateurServices utilisateurService = new UtilisateurServices();

    public UtilisateurController() {
        utilisateurService = new UtilisateurServices();
    }

    public Utilisateur findByCode(int id) throws SQLException {
        return utilisateurService.findByCode(id);
    }
    public void create(Utilisateur utilisateur) throws SQLException {
        utilisateurService.create(utilisateur);
    }
    public void update(Utilisateur utilisateur) throws SQLException {
        utilisateurService.update(utilisateur);
    }
    public void delete(Utilisateur utilisateur) throws SQLException {
        utilisateurService.delete(utilisateur);
    }

    public ArrayList<Utilisateur> getALlMoniteurAvecLicence(int idPremis) throws SQLException {
        return utilisateurService.getALlMoniteurAvecLicence(idPremis);
    }

    public String getMoniteurLecon(String idLecon) throws SQLException {
        return utilisateurService.getMoniteurLecon(idLecon);
    }

    public String getEleveLecon(String idLecon) throws SQLException {
        return utilisateurService.getEleveLecon(idLecon);
    }
}
