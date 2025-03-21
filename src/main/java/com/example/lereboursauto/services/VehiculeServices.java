package com.example.lereboursauto.services;
import com.example.lereboursauto.models.Vehicule;
import com.example.lereboursauto.repository.VehiculeRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class VehiculeServices {
    private VehiculeRepository vehiculeRepository;

    public VehiculeServices() {
        vehiculeRepository = new VehiculeRepository();
    }

    public HashMap<String, String> getVehiculesByPermis(int statutCompte, int licenceUser) throws SQLException {
        /**
         * Fonction qui va rechercher la hashmap des vehicules par marque
         * selon le permis de l'utilisateur
         **/

        return vehiculeRepository.getVehiculesByPermis(statutCompte, licenceUser);
    }

    public String getMarqueVehiculeLecon(String idLecon) throws SQLException {
        return vehiculeRepository.getMarqueVehiculeLecon(idLecon);
    }

    public String getModeleVehiculeLecon(String idLecon) throws SQLException {
        return vehiculeRepository.getModeleVehiculeLecon(idLecon);
    }

    public ArrayList<Vehicule> getAllVehiculeByIdPermis(int idPermis) throws SQLException{
        return vehiculeRepository.getAllVehiculeByIdPermis(idPermis);
    }

    public ArrayList<String> getVehiculeNonDispo(Date date, String string) throws SQLException {
        return vehiculeRepository.getVehiculeNonDispo(date, string);
    }

    public String getIdByModele (String modele) throws SQLException {
        return vehiculeRepository.getIdByModele(modele);
    }
}
