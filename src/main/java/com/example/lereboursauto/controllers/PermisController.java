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
        return permisServices.getLicence(statutCompte);
    }

    public int getIdPermisByLibelle(String licenceUser) throws SQLException {
        return permisServices.getIdPermisByLibelle(licenceUser);
    }

    public int getIdByName(String name) throws SQLException {
        return permisServices.getIdByName(name);
    }

    public String getPermisByIdLecon(String idLecon) throws SQLException {
        return permisServices.getPermisByIdLecon(idLecon);
    }
}
