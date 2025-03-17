package com.example.lereboursauto.services;
import com.example.lereboursauto.repository.VehiculeRepository;

import java.sql.SQLException;
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
}
