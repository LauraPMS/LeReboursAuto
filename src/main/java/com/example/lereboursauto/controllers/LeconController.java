package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Lecon;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.services.LeconServices;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

    public int getTotalHeuresEleveByPermis(int statutCompte, int licenceUser) throws SQLException {
        return leconServices.getTotalHeuresEleveByPermis(statutCompte, licenceUser);
    }

    public int getTotalHeuresMoniteurByPermis(int statutCompte, int licenceUser) throws SQLException {
        return leconServices.getTotalHeuresMoniteurByPermis(statutCompte, licenceUser);
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

    public ArrayList<Integer> getLeconNR(int idEleve) throws SQLException {
        return leconServices.getLeconNR(idEleve);
    }

    public String getDateLecon(String idLecon) throws SQLException {
        return leconServices.getDateLecon(idLecon);
    }

    public String getHeureLecon(String idLecon) throws SQLException {
        return leconServices.getHeureLecon(idLecon);
    }

    public void update(String idLecon) throws SQLException {
        leconServices.update(idLecon);
    }

    public ArrayList<Integer> getLeconR(int idMoniteur) throws SQLException {
        return leconServices.getLeconR(idMoniteur);
    }

    public HashMap<String, Integer> getDataGraphiqueLeconRNR(int idmoniteur) throws SQLException {
        return leconServices.getDataGraphiqueLeconRNR(idmoniteur);
    }

    public ArrayList<Utilisateur> getMoniteurNonDispo(Date date, String heure) throws SQLException {
        return leconServices.getMoniteurNonDispo(date, heure);
    }

    public HashMap<String, ArrayList<String>> graphLeconElevesByPermis(int idMoniteur) throws SQLException {
        return leconServices.graphLeconElevesByPermis(idMoniteur);
    }

    public void create(Date date, String heure, String immatriculation, int reglee, int idEleve, int idMoniteur, int idPermis)throws SQLException{
        leconServices.create(date, heure, immatriculation, reglee, idEleve, idMoniteur, idPermis);
    }
}
