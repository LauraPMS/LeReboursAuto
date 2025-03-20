package com.example.lereboursauto.controllers;


import com.example.lereboursauto.services.VehiculeServices;

import java.sql.SQLException;
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
}
