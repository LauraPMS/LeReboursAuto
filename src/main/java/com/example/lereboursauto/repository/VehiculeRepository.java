package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Permis;
import com.example.lereboursauto.models.Vehicule;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class VehiculeRepository {
    Connection connexion;
    PermisRepository permisRepository;
    public VehiculeRepository() {
        connexion = ConnexionBDD.getCnx();
        permisRepository = new PermisRepository();
    }

    public Vehicule findByCode(String immatriculation) throws SQLException {
        Vehicule v = null;
        PreparedStatement ps = connexion.prepareStatement("Select immatriculation, annee, codePermis, marque, modele FROM vehicule where immatriculation = ? ");
        ps.setString(1, immatriculation);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Permis p = permisRepository.findByCode(rs.getInt("codePermis"));
            v = new Vehicule(rs.getString("immatriculation"), rs.getString("annee"), p, rs.getString("marque"), rs.getString("modele"));
        }
        return v;
    }

    public HashMap<String, String> getVehiculesByPermis(int statutUser, int licenceUser) throws SQLException {
        HashMap<String, String> vehiculesPermisEleve = new HashMap<>();
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT vehicule.marque, vehicule.modele\n" +
                "FROM vehicule\n" +
                "JOIN lecon on vehicule.immatriculation = lecon.immatriculation\n" +
                "WHERE lecon.idEleve = ?\n" +
                "AND lecon.idPermis = ?\n" +
                "GROUP BY vehicule.marque;");
        ps.setInt(1, statutUser);
        ps.setInt(2, licenceUser);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            vehiculesPermisEleve.put(rs.getString("marque"), rs.getString("modele"));
        }
        return vehiculesPermisEleve;
    }

    public String getMarqueVehiculeLecon(String idLecon) throws SQLException {
        /** Fonction qui va rechercher la marque d'un vehicule selon l'idLecon **/
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT marque FROM vehicule JOIN lecon on vehicule.immatriculation = lecon.immatriculation WHERE lecon.id = ?;");
        ps.setString(1, idLecon);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String marqueVehicule = rs.getString("marque");
        return marqueVehicule;
    }

    public String getModeleVehiculeLecon(String idLecon) throws SQLException {
        /** Fonction qui va rechercher le modele d'un vehicule selon l'idLecon **/
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT modele FROM vehicule JOIN lecon on vehicule.immatriculation = lecon.immatriculation WHERE lecon.id = ?;");
        ps.setString(1, idLecon);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String modeleVehicule = rs.getString("modele");
        return modeleVehicule;
    }
}
