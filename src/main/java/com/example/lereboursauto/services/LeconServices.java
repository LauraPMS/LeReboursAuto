package com.example.lereboursauto.services;

import com.example.lereboursauto.models.Lecon;
import com.example.lereboursauto.repository.LeconRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LeconServices {
    LeconRepository leconRepository;

    public LeconServices() {
        leconRepository = new LeconRepository();
    }

    // get des leçons de l'élève

    public ArrayList<Lecon> getAllLeconForEleve(int codeEleveActif) throws SQLException {
        return leconRepository.getAllLeconForEleve(codeEleveActif);
    }

    public ArrayList<Lecon> getAllFuturLeconForEleve(int codeEleveActif) throws SQLException {
        return leconRepository.getAllFuturLeconForEleve(codeEleveActif);
    }

    // get des leçons du moniteur
    public ArrayList<Lecon> getAllLeconForMoniteur(int codeEleveActif) throws SQLException {
        return leconRepository.getAllLeconForMoniteur(codeEleveActif);
    }

    public ArrayList<Lecon> getAllFuturLeconForMoniteur(int codeEleveActif) throws SQLException {
        return leconRepository.getAllFuturLeconForMoniteur(codeEleveActif);
    }


    // utilisé pour les stats

    public int getTotalHeuresByLicence(int idUser, int licenceUser) throws SQLException {
        /**
         * Fonction qui va rechercher la liste des permis de l'élève
         **/

        return leconRepository.getTotalHeuresByLicence(idUser, licenceUser);
    }
    public HashMap<String,Integer> getDataGraphiquePermisVehicules(int idUser, int idPermis) throws SQLException {
        return leconRepository.getDataGraphiquePermisVehicules(idUser, idPermis);
    }

    public HashMap<String, Integer> getHeuresByMoniteurs(int idUser, int idPermis) throws SQLException {
        /**
         * Fonction qui va rechercher la liste des permis de l'utilisateur
         **/

        return leconRepository.getHeuresByMoniteurs(idUser, idPermis);
    }

    public ArrayList<String> getHorrairesNonDispo(int idPermis, String date) throws SQLException {
        return leconRepository.getHorrairesNonDispo(idPermis, date);
    }

    public ArrayList<Integer> getLeconNR(int idEleve) throws SQLException {
        return leconRepository.getLeconNR(idEleve);
    }

    public String getDateLecon(String idLecon) throws SQLException {
        return leconRepository.getDateLecon(idLecon);
    }

    public String getHeureLecon(String idLecon) throws SQLException {
        return leconRepository.getHeureLecon(idLecon);
    }

    public void update(String idLecon) throws SQLException {
        leconRepository.update(idLecon);
    }

    public ArrayList<Integer> getLeconR(int idMoniteur) throws SQLException {
        return leconRepository.getLeconR(idMoniteur);
    }

    public HashMap<String, Integer> getDataGraphiqueLeconRNR(int idmoniteur) throws SQLException {
        return leconRepository.getDataGraphiqueLeconRNR(idmoniteur);
    }
}
