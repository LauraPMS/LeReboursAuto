package com.example.lereboursauto.services;

import com.example.lereboursauto.models.Lecon;
import com.example.lereboursauto.repository.LeconRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class LeconServices {
    LeconRepository leconRepository;

    public LeconServices() {
        leconRepository = new LeconRepository();
    }

    public ArrayList<Lecon> getAllLeconForEleve(int codeEleveActif) throws SQLException {
        return leconRepository.getAllLeconForEleve(codeEleveActif);
    }

    public ArrayList<Lecon> getAllLeconForMoniteur(int codeEleveActif) throws SQLException {
        return leconRepository.getAllLeconForMoniteur(codeEleveActif);
    }

    public int getTotalHeuresByLicence(int statutCompte, int licenceUser) throws SQLException {
        /**
         * Fonction qui va rechercher la liste des permis de l'élève
         **/

        return leconRepository.getTotalHeuresByLicence(statutCompte, licenceUser);
    }

}
