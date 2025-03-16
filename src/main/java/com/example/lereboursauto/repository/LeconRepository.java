package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Lecon;
import com.example.lereboursauto.models.Permis;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.models.Vehicule;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeconRepository {

    private Connection connexion;
    private UtilisateurRepository utilisateurRepository;
    private PermisRepository permisRepository;
    private VehiculeRepository vehiculeRepository;

    public LeconRepository() {
        connexion = ConnexionBDD.getCnx();
        utilisateurRepository = new UtilisateurRepository();
        permisRepository = new PermisRepository();
        vehiculeRepository = new VehiculeRepository();
    }

    public ArrayList<Lecon> getAllLeconForEleve(int codeEleveActif) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement ps = connexion.prepareStatement("SELECT id, date, heure, immatriculation, reglee, idEleve, idMoniteur, idPermis FROM lecon where idEleve = ? ORDER BY(date) ASC");
        ps.setInt(1, codeEleveActif);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            Date date = Date.valueOf(rs.getString("date"));
            String heure = rs.getString("heure");
            int reglee = rs.getInt("reglee");

            String immatriculation = rs.getString("immatriculation");
            int idEleve = rs.getInt("idEleve");
            Utilisateur eleve = utilisateurRepository.findByCode(idEleve);
            int idMoniteur = rs.getInt("idMoniteur");
            Utilisateur moniteur = utilisateurRepository.findByCode(idMoniteur);
            int idPermis = rs.getInt("idPermis");
            Permis permis = permisRepository.findByCode(idPermis);
            Vehicule vehicule = vehiculeRepository.findByCode(rs.getString("immatriculation"));

            String nomPrenomEleve = eleve.getNom() + eleve.getPrenom();
            String nomPrenomMoniteur = moniteur.getNom() + moniteur.getPrenom();
            String nomPermis = permis.getLibelle();
            String modeleVehicule = vehicule.getModele();

            Lecon l = new Lecon(id, date, immatriculation, reglee, eleve, moniteur, permis, heure, nomPermis, modeleVehicule, nomPrenomMoniteur, nomPrenomEleve);
            lecons.add(l);
        }
        return lecons;
    }

    public ArrayList<Lecon> getAllLeconForMoniteur(int codeEleveActif) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement ps = connexion.prepareStatement("SELECT id, date, heure, immatriculation, reglee, idEleve, idMoniteur, idPermis FROM lecon where idMoniteur = ? AND date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY) ORDER BY(date) ASC");
        ps.setInt(1, codeEleveActif);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            Date date = Date.valueOf(rs.getString("date"));
            String heure = rs.getString("heure");
            int reglee = rs.getInt("reglee");

            String immatriculation = rs.getString("immatriculation");
            int idEleve = rs.getInt("idEleve");
            Utilisateur eleve = utilisateurRepository.findByCode(idEleve);
            int idMoniteur = rs.getInt("idMoniteur");
            Utilisateur moniteur = utilisateurRepository.findByCode(idMoniteur);
            int idPermis = rs.getInt("idPermis");
            Permis permis = permisRepository.findByCode(idPermis);
            Vehicule vehicule = vehiculeRepository.findByCode(rs.getString("immatriculation"));

            String nomPrenomEleve = eleve.getNom() + eleve.getPrenom();
            String nomPrenomMoniteur = moniteur.getNom() + moniteur.getPrenom();
            String nomPermis = permis.getLibelle();
            String modeleVehicule = vehicule.getModele();

            Lecon l = new Lecon(id, date, immatriculation, reglee, eleve, moniteur, permis, heure, nomPermis, modeleVehicule, nomPrenomMoniteur, nomPrenomEleve);
            lecons.add(l);
        }
        return lecons;
    }
}
