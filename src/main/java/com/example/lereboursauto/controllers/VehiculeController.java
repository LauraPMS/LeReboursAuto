package com.example.lereboursauto.controllers;


import com.example.lereboursauto.models.Vehicule;
import com.example.lereboursauto.services.VehiculeServices;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class VehiculeController {
    private VehiculeServices vehiculeServices;

    public VehiculeController() {
        vehiculeServices = new VehiculeServices();
    }

    public HashMap<String, String> getVehiculesByPermis(int statutCompte, int licenceUser) throws SQLException {
        /**
         * Fonction qui va rechercher la hashmap des vehicules par marque
         * selon le permis de l'utilisateur
         **/

        return vehiculeServices.getVehiculesByPermis(statutCompte, licenceUser);
    }

    public String getMarqueVehiculeLecon(String idLecon) throws SQLException {
        return vehiculeServices.getMarqueVehiculeLecon(idLecon);
    }

    public String getModeleVehiculeLecon(String idLecon) throws SQLException {
        return vehiculeServices.getModeleVehiculeLecon(idLecon);
    }

    public ArrayList<Vehicule> getAllVehiculeByIdPermis(int idPermis) throws SQLException{
        return vehiculeServices.getAllVehiculeByIdPermis(idPermis);
    }

    public ArrayList<String> getVehiculeNonDispo(Date date, String string) throws SQLException {
        return vehiculeServices.getVehiculeNonDispo(date, string);
    }
    public String getIdByModele (String modele) throws SQLException {
        return vehiculeServices.getIdByModele(modele);
    }

    }
