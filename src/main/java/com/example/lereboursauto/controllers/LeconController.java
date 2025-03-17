package com.example.lereboursauto.controllers;

import com.example.lereboursauto.services.LeconServices;

import java.sql.SQLException;

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
}
