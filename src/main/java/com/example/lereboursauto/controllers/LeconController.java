package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Lecon;
import com.example.lereboursauto.services.LeconServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LeconController {

    private LeconServices leconServices;

    public LeconController() {
        leconServices = new LeconServices();
    }


    /*

        obtenir toutes les leçons d'un eleve

     */

    public ArrayList<Lecon> getAllLeconForEleve(int codeEleveActif) throws SQLException {
        return leconServices.getAllLeconForEleve(codeEleveActif);
    }

    public ArrayList<Lecon> getAllFuturLeconForEleve(int codeEleveActif) throws SQLException {
        return leconServices.getAllFuturLeconForEleve(codeEleveActif);
    }

    /*

        Obtenir toute les lecon d'un moniteur

     */

    public ArrayList<Lecon> getAllLeconForMoniteur(int codeEleveActif) throws SQLException {
        return leconServices.getAllLeconForMoniteur(codeEleveActif);
    }

    public ArrayList<Lecon> getAllFuturLeconForMoniteur(int codeEleveActif) throws SQLException {
        return leconServices.getAllFuturLeconForMoniteur(codeEleveActif);
    }



    // utilisé pour les stats

    public int getTotalHeuresByLicence(int statutCompte, int licenceUser) throws SQLException {
        return leconServices.getTotalHeuresByLicence(statutCompte, licenceUser);
    }

    public HashMap<String,Integer> getDataGraphiquePermisVehicules(int idUser, int idPermis) throws SQLException {
        return leconServices.getDataGraphiquePermisVehicules(idUser, idPermis);
    }

    public HashMap<String, Integer> getHeuresByMoniteurs(int statutCompte, int idPermis) throws SQLException {
        /**
         * Retourne la liste des permis de l'utilisateur
         **/

        return leconServices.getHeuresByMoniteurs(statutCompte, idPermis);
    }

    public ArrayList<String> getHorrairesNonDispo(int idPermis, String date) throws SQLException {
        return leconServices.getHorrairesNonDispo(idPermis, date);
    }

}
