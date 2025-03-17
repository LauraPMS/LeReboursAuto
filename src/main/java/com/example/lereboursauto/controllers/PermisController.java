package com.example.lereboursauto.controllers;
import com.example.lereboursauto.services.PermisServices;

import java.sql.SQLException;
import java.util.ArrayList;

public class PermisController {

    private PermisServices permisServices;

    public PermisController() {
        permisServices = new PermisServices();
    }

    public ArrayList<String> getLicence(int statutCompte) throws SQLException {
        /**
         * Fonction qui va rechercher la liste des permis de l'élève
         **/
        return permisServices.getLicence(statutCompte);
    }

    public int getIdPermisByLibelle(String licenceUser) throws SQLException {
        /**
         * Fonction qui va renvoyer l'id du permis selon son libelle
         **/

        return permisServices.getIdPermisByLibelle(licenceUser);
    }
}
