package com.example.lereboursauto.controllers;

import com.example.lereboursauto.services.LeconServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LeconController {

    private LeconServices leconServices;

    public LeconController() {
        leconServices = new LeconServices();
    }

    public int getTotalHeuresByLicence(int statutCompte, int licenceUser) throws SQLException {
        /**
         * Fonction qui va rechercher la liste des permis de l'élève
         **/

        return leconServices.getTotalHeuresByLicence(statutCompte, licenceUser);
    }

    public HashMap<String,Integer> getDataGraphiquePermisVehicules(int idUser, int idPermis) throws SQLException {
        return leconServices.getDataGraphiquePermisVehicules(idUser, idPermis);
    }

    public HashMap<String, Integer> getHeuresByMoniteurs(int statutCompte, int idPermis) throws SQLException {
        /**
         * Fonction qui va rechercher la liste des permis de l'utilisateur
         **/

        return leconServices.getHeuresByMoniteurs(statutCompte, idPermis);
    }
}
