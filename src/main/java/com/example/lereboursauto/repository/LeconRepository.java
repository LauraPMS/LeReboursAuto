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

    public int getTotalHeuresEleveByPermis(int idUser, int licenceUser) throws SQLException {
        /** Fonction qui va rechercher la liste des permis de l'eleve **/
        int totalHeures = 0;
        PreparedStatement ps;
        ps = connexion.prepareStatement("Select COUNT(lecon.heure) AS nbheures\n" +
                "FROM lecon\n" +
                "WHERE lecon.idEleve = ?\n" +
                "AND lecon.idPermis = ?");
        ps.setInt(1, idUser);
        ps.setInt(2, licenceUser);
        ResultSet rs = ps.executeQuery();
        rs.next();
        totalHeures = rs.getInt("nbheures");
        return totalHeures;
    }

    public int getTotalHeuresMoniteurByPermis(int idUser, int licenceUser) throws SQLException {
        /** Fonction qui va rechercher la liste des permis de l'eleve **/
        int totalHeures = 0;
        PreparedStatement ps;
        ps = connexion.prepareStatement("Select COUNT(lecon.heure) AS nbheures\n" +
                "FROM lecon\n" +
                "WHERE lecon.idMoniteur = ?\n" +
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


    // récupérer les horaires pour une date donnée
    public ArrayList<String> getHorrairesNonDispo(int idPermis, String date) throws SQLException {
        ArrayList<String> horrairesNonDispo = new ArrayList<>();
        PreparedStatement ps = connexion.prepareStatement("SELECT heure \n" +
                "FROM lecon \n" +
                "WHERE idPermis = ? AND date = ?;");

        ps.setInt(1, idPermis);
        ps.setString(2, date);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            horrairesNonDispo.add(rs.getString("heure"));
        }
        return horrairesNonDispo;
    }

    public ArrayList<Utilisateur> getMoniteursNonDispo( String date, String horraire) throws SQLException {
        ArrayList<Utilisateur> moniteursNonDispo = new ArrayList<>();
        PreparedStatement ps = connexion.prepareStatement("SELECT idUser \n"+
                "FROM lecon \n" +
                "WHERE date = ? AND horraire = ?;");

        ps.setString(1, date);
        ps.setString(2, date);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            moniteursNonDispo.add(utilisateurRepository.findByCode(rs.getInt("idUser")));
        }
        return moniteursNonDispo;
    }

    public ArrayList<Integer> getLeconNR(int idEleve) throws SQLException {
        ArrayList<Integer> leconsNR = new ArrayList<>();
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT id FROM Lecon WHERE idEleve = ? AND Reglee = 0;");
        ps.setInt(1, idEleve);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            leconsNR.add(rs.getInt("id"));
        }
        return leconsNR;
    }

    public String getDateLecon(String idLecon) throws SQLException {
        /** Fonction qui va rechercher la date d'une lecon selon l'idLecon **/
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT date FROM lecon WHERE lecon.id = ?;");
        ps.setString(1, idLecon);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String datePermis = rs.getString("date");
        return datePermis;
    }

    public String getHeureLecon(String idLecon) throws SQLException {
        /** Fonction qui va rechercher l'heure d'une lecon selon l'idLecon **/
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT heure FROM lecon WHERE lecon.id = ?;");
        ps.setString(1, idLecon);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String datePermis = rs.getString("heure");
        return datePermis;
    }

    public void update(String idLecon) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("UPDATE lecon SET reglee = 1 WHERE id = ?;");
        ps.setString(1, idLecon);
        ps.executeUpdate();
    }

    public ArrayList<Integer> getLeconR(int idMoniteur) throws SQLException {
        ArrayList<Integer> leconsR = new ArrayList<>();
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT id FROM Lecon WHERE idMoniteur = ? AND Reglee = 1;");
        ps.setInt(1, idMoniteur);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            leconsR.add(rs.getInt("id"));
        }
        return leconsR;
    }

    public HashMap<String,Integer> getDataGraphiqueLeconRNR(int idMoniteur) throws SQLException {
        HashMap<String, Integer> datas = new HashMap();

        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT reglee, COUNT(id) as nblecon FROM Lecon WHERE idMoniteur = ? GROUP BY reglee;");
        preparedStatement.setInt(1, idMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            datas.put(resultSet.getString("reglee"), resultSet.getInt("nblecon"));
        }
        preparedStatement.close();
        resultSet.close();
        return datas;
    }

    public ArrayList<Utilisateur> getMoniteurNonDispo(Date date, String heure) throws SQLException {
        ArrayList<Utilisateur> moniteurNomDispo = new ArrayList<>();
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT idMoniteur FROM lecon WHERE date = ? AND heure = ?;");
        ps.setDate(1, date);
        ps.setString(2, heure);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            moniteurNomDispo.add(utilisateurRepository.findByCode(rs.getInt("idMoniteur")));
        }
        return moniteurNomDispo;
    }


    public HashMap<String, ArrayList<String>> graphLeconElevesByPermis(int idMoniteur) throws SQLException {
        HashMap<String, ArrayList<String>> datas = new HashMap();
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT permis.libelle, utilisateur.prenom, COUNT(lecon.id) as nblecon " +
                "FROM lecon " +
                "JOIN utilisateur on lecon.idEleve = utilisateur.code " +
                "JOIN permis on lecon.idPermis = permis.id " +
                "WHERE lecon.idMoniteur = ? " +
                "GROUP BY permis.libelle, utilisateur.prenom;");
        preparedStatement.setInt(1, idMoniteur);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            if(!datas.containsKey(resultSet.getString("libelle")))
            {
                ArrayList<String> lesEleves = new ArrayList<>();
                lesEleves.add(resultSet.getString("prenom"));
                lesEleves.add(resultSet.getString("nblecon"));
                datas.put(resultSet.getString("libelle"),lesEleves);
            }
            else
            {
                datas.get(resultSet.getString("libelle")).add(resultSet.getString("prenom"));
                datas.get(resultSet.getString("libelle")).add(resultSet.getString("nblecon"));
            }
        }
        preparedStatement.close();
        resultSet.close();
        return datas;
    }

    public void create(Date date, String heure, String immatriculation, int reglee, int idEleve, int idMoniteur, int idPermis)throws SQLException{
        PreparedStatement ps = connexion.prepareStatement("INSERT INTO lecon VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        ps.setDate(1, date);
        ps.setString(2, heure);
        ps.setString(3, immatriculation);
        ps.setInt(4, reglee);
        ps.setInt(5, idEleve);
        ps.setInt(6, idMoniteur);
        ps.setInt(7, idPermis);
        ps.executeUpdate();
    }


}
