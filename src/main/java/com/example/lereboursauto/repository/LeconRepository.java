package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Lecon;
import com.example.lereboursauto.models.Permis;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.models.Vehicule;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    public ArrayList<Lecon> getAllFuturLeconForEleve(int codeEleveActif) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement ps = connexion.prepareStatement("SELECT id, date, heure, immatriculation, reglee, idEleve, idMoniteur, idPermis FROM lecon where idEleve = ? and date >= NOW() ORDER BY(date) ASC");
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

    public ArrayList<Lecon> getAllFuturLeconForMoniteur(int codeEleveActif) throws SQLException {
        ArrayList<Lecon> lecons = new ArrayList<>();
        PreparedStatement ps = connexion.prepareStatement("SELECT id, date, heure, immatriculation, reglee, idEleve, idMoniteur, idPermis FROM lecon where idMoniteur = ? AND date BETWEEN NOW() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY) ORDER BY(date) ASC");
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

    public int getTotalHeuresByLicence(int idUser, int licenceUser) throws SQLException {
        /** Fonction qui va rechercher la liste des permis de l'utilisateur **/
        int totalHeures = 0;
        PreparedStatement ps;
        ps = connexion.prepareStatement("Select COUNT(lecon.heure) AS nbheures\n" +
                "FROM lecon\n" +
                "JOIN utilisateur ON lecon.idEleve = utilisateur.code\n" +
                "WHERE utilisateur.idStatut = ?\n" +
                "AND lecon.idPermis = ?");
        ps.setInt(1, idUser);
        ps.setInt(2, licenceUser);
        ResultSet rs = ps.executeQuery();
        rs.next();
        totalHeures = rs.getInt("nbheures");
        return totalHeures;
    }

    public HashMap<String,Integer> getDataGraphiquePermisVehicules(int idUser, int licenceUser) throws SQLException {
        HashMap<String, Integer> data = new HashMap();
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT vehicule.modele ,COUNT(lecon.heure) AS \"heures\"\n" +
                "FROM lecon\n" +
                "INNER JOIN vehicule on lecon.immatriculation = vehicule.immatriculation \n" +
                "WHERE lecon.idEleve = ? \n" +
                "AND lecon.idPermis = ? \n" +
                "GROUP BY vehicule.modele;");
        preparedStatement.setInt(1, idUser);
        preparedStatement.setInt(2, licenceUser);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            data.put(resultSet.getString("modele"), resultSet.getInt("heures"));
        }
        preparedStatement.close();
        resultSet.close();
        return data;
    }

    public HashMap<String, Integer> getHeuresByMoniteurs(int idUser, int idPermis) throws SQLException {

        /** Fonction qui va rechercher le nombres d'heures par moniteurs selon l'utilisateur **/

        HashMap<String, Integer> heuresByMoniteur = new HashMap<>();
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT distinct utilisateur.nom, utilisateur.prenom, COUNT(lecon.heure) AS heures\n" +
                "FROM lecon\n" +
                "JOIN utilisateur on lecon.idMoniteur = utilisateur.code\n" +
                "WHERE lecon.idEleve = ?\n" +
                "AND lecon.idPermis = ?\n" +
                "GROUP BY  utilisateur.nom;");
        ps.setInt(1, idUser);
        ps.setInt(2, idPermis);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String nomPrenomMoniteur = rs.getString("nom") + " " + rs.getString("prenom");
            heuresByMoniteur.put(nomPrenomMoniteur, rs.getInt("heures"));

        }
        return heuresByMoniteur;
    }

    public void create (Lecon l) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("INSERT INTO (date, heure, immatriculation, reglee, idEleve, idMoniteur, idPermis) VALUES (?,?,?,?,?,?,?)");
        ps.setDate(1, l.getDate());
        ps.setString(2, l.getHeure());
        ps.setString(3, l.getImmatriculation());
        ps.setInt(4, l.getReglee());
        ps.setInt(5, l.getEleve().getCode());
        ps.setInt(6, l.getMoniteur().getCode());
        ps.setInt(7, l.getPermis().getId());
        ps.executeUpdate();
    }


}
