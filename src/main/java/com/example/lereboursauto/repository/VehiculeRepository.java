package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Permis;
import com.example.lereboursauto.models.Vehicule;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculeRepository {
    Connection connection;
    PermisRepository permisRepository;
    public VehiculeRepository() {
        connection = ConnexionBDD.getCnx();
        permisRepository = new PermisRepository();
    }

    public Vehicule findByCode(String immatriculation) throws SQLException {
        Vehicule v = null;
        PreparedStatement ps = connection.prepareStatement("Select immatriculation, annee, codePermis, marque, modele FROM vehicule where immatriculation = ? ");
        ps.setString(1, immatriculation);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Permis p = permisRepository.findByCode(rs.getInt("codePermis"));
            v = new Vehicule(rs.getString("immatriculation"), rs.getString("annee"), p, rs.getString("marque"), rs.getString("modele"));
        }
        return v;
    }
}
