package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Statut;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;

public class UtilisateurRepository {
    private Connection connexion;
    private StatutRepository statutRepository;

    public UtilisateurRepository() {
        connexion = ConnexionBDD.getCnx();
        statutRepository = new StatutRepository();
    }

    public Utilisateur findByCode(int code) throws SQLException {

        Utilisateur utilisateur = null;
        PreparedStatement preparedStatement = connexion.prepareStatement("SELECT code, nom, prenom, dateNaissance, telephone, sexe, codePostal, login, mdp, idStatut, adresse, ville FROM utilisateur WHERE code = ?");
        preparedStatement.setInt(1, code);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {

            int codeUSER = rs.getInt("code");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Date dateNaissance = rs.getDate("dateNaissance");
            String telephone = rs.getString("telephone");
            int sexe = rs.getInt("sexe");
            int codePostal = rs.getInt("codePostal");
            String ville = rs.getString("ville");
            String adresse = rs.getString("adresse");
            String mdp = rs.getString("mdp");
            int idStatut = rs.getInt("idStatut");
            String login = rs.getString("login");
            Statut statut = statutRepository.findById(idStatut);

            utilisateur = new Utilisateur(codeUSER, nom, prenom, dateNaissance, telephone, sexe, adresse, ville, codePostal, login, mdp, statut);

        }

        return utilisateur;

    }

    public void create(Utilisateur u) throws SQLException {
        String nom = u.getNom();
        String prenom = u.getPrenom();
        Date dateNaissance = u.getDateNaissance();
        String telephone = u.getTelephone();
        int sexe = u.getSexe();
        int codePostal = u.getCodePostal();
        String login = u.getLogin();
        String mdp = u.getMdp();
        Statut statut = u.getStatut();
        String adresse = u.getAdresse();
        String ville = u.getVille();

        PreparedStatement ps = connexion.prepareStatement("INSERT INTO utilisateur (nom, prenom, dateNaissance, telephone, sexe, codePostal, login, mdp, idStatut, adresse, ville) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setDate(3, dateNaissance);
        ps.setString(4, telephone);
        ps.setInt(5, sexe);
        ps.setInt(6, codePostal);
        ps.setString(7, login);
        ps.setString(8, mdp);
        ps.setInt(9, statut.getId());
        ps.setString(10, adresse);
        ps.setString(11, ville);
        ps.executeUpdate();
    }


    public void update(Utilisateur u) throws SQLException {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, dateNaissance = ?, telephone = ?, sexe = ?, codePostal = ?, login = ?, mdp = ?, idStatut = ?, adresse = ?, ville = ? WHERE code = ?";

        try (PreparedStatement ps = connexion.prepareStatement(sql)) {
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setDate(3, u.getDateNaissance());
            ps.setString(4, u.getTelephone());
            ps.setInt(5, u.getSexe());
            ps.setInt(6, u.getCodePostal());
            ps.setString(7, u.getLogin());
            ps.setString(8, u.getMdp());
            ps.setInt(9, u.getStatut().getId()); // Correction du champ statut
            ps.setString(10, u.getAdresse());
            ps.setString(11, u.getVille());
            ps.setInt(12, u.getCode());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Mise à jour réussie pour l'utilisateur : " + u.getCode());
            } else {
                System.out.println("Aucun utilisateur trouvé avec ce code.");
            }
        }
    }



    public void delete(Utilisateur u) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("DELETE FROM utilisateur WHERE code = ? ");
        ps.setInt(1, u.getCode());
        ps.executeUpdate();
    }


    // findBy
    public ArrayList<Utilisateur> getALlMoniteurAvecLicence(int idPremis) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("SELECT code FROM utilisateur " +
                "JOIN licence ON licence.idUser = utilisateur.code " +
                "WHERE utilisateur.idStatut = ? AND licence.codeCategorie = ? ");
        ps.setInt(1, 2);
        ps.setInt(2, idPremis);
        ResultSet rs = ps.executeQuery();
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        while (rs.next()) {
            utilisateurs.add(findByCode(rs.getInt("code")));
        }
        return utilisateurs;
    }

    public String getMoniteurLecon(String idLecon) throws SQLException {
        /** Fonction qui va rechercher le moniteur d'une lecon selon l'idLecon **/
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT nom, prenom FROM utilisateur JOIN lecon on utilisateur.code = lecon.idMoniteur WHERE lecon.id = ?;");
        ps.setString(1, idLecon);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String nomPrenomMoniteur = rs.getString("nom") + " " + rs.getString("prenom");
        return nomPrenomMoniteur;
    }

    public String getEleveLecon(String idLecon) throws SQLException {
        /** Fonction qui va rechercher le moniteur d'une lecon selon l'idLecon **/
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT nom, prenom FROM utilisateur JOIN lecon on utilisateur.code = lecon.idEleve WHERE lecon.id = ?;");
        ps.setString(1, idLecon);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String nomPrenomEleve = rs.getString("nom") + " " + rs.getString("prenom");
        return nomPrenomEleve;
    }

    public int findIdByName(String nom) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("SELECT code FROM utilisateur WHERE nom = ?");
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int code = rs.getInt("code");
        return code;
    }

}
