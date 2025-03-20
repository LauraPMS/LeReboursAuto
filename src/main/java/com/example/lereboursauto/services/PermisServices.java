package com.example.lereboursauto.services;

import com.example.lereboursauto.repository.PermisRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class PermisServices {

    private PermisRepository permisRepository;

    public PermisServices() {
        permisRepository = new PermisRepository();
    }

    public ArrayList<String> getLicence(int statutCompte) throws SQLException {
        /**
         * Fonction qui va rechercher la liste des permis de l'utilisateur
         **/

        return permisRepository.getLicence(statutCompte);
    }

    public int getIdPermisByLibelle(String licenceUser) throws SQLException {
        /**
         * Fonction qui va renvoyer l'id du permis selon son libelle
         **/

        return permisRepository.getIdPermisByLibelle(licenceUser);
    }

    public int getIdByName(String nom) throws SQLException {
        return permisRepository.getIdByNom(nom);
    }
}
