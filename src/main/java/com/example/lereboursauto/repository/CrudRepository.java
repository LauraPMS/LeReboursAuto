package com.example.lereboursauto.repository;


import com.example.lereboursauto.models.Lecon;
import com.example.lereboursauto.models.Utilisateur;

import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudRepository {
    private Connection connection;

    public CrudRepository() {
        connection = ConnexionBDD.getCnx();
    }

    // Lecon
    public void createLecon(Lecon l) throws SQLException {
        int id = l.getId();

    }

    // Licence

    // Permis

    // Statut

    // Utilisateur

    public void createUtilisateur(Utilisateur u) throws SQLException {
        String nom = u.getNom();
        String prenom = u.getPrenom();
        Date dateNaissance = u.getDateNaissance();
        String telephone = u.getTelephone();
        int sexe = u.getSexe();
        int codePostal = u.getCodePostal();
        String login = u.getLogin();
        String mdp = u.getMdp();
        int statut = u.getStatut().getId();
        String adresse = u.getAdresse();
        String ville = u.getVille();

        PreparedStatement ps = connection.prepareStatement("INSERT INTO utilisateur (nom, prenom, dateNaissance, telephone, sexe, codePostal, login, mdp, idStatut, adresse, ville) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setDate(3, dateNaissance);
        ps.setString(4, telephone);
        ps.setInt(5, sexe);
        ps.setInt(6, codePostal);
        ps.setString(7, login);
        ps.setString(8, mdp);
        ps.setInt(9, statut);
        ps.setString(10, adresse);
        ps.setString(11, ville);
        ps.executeUpdate();
    }
    public void UpdateUtilisateur(Utilisateur u) throws SQLException {
        String nom = u.getNom();
        String prenom = u.getPrenom();
        Date dateNaissance = u.getDateNaissance();
        String telephone = u.getTelephone();
        int sexe = u.getSexe();
        int codePostal = u.getCodePostal();
        String login = u.getLogin();
        String mdp = u.getMdp();
        int statut = u.getStatut().getId();
        String adresse = u.getAdresse();
        String ville = u.getVille();

        PreparedStatement ps = connection.prepareStatement("Update utilisateur SET nom = ?, prenom = ?, dateNaissance = ?, telephone = ?, sexe = ?, codePostal = ?, login = ?, mdp = ?, statut = ?, adresse = ?, ville = ?" +
                " FROM utilisateur WHERE code = ?");
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setDate(3, dateNaissance);
        ps.setString(4, telephone);
        ps.setInt(5, sexe);
        ps.setInt(6, codePostal);
        ps.setString(7, login);
        ps.setString(8, mdp);
        ps.setInt(9, statut);
        ps.setString(10, adresse);
        ps.setString(11, ville);
        ps.setInt(12, u.getCode());

        ps.executeUpdate();
    }
    public void DeleteUtilisateur(Utilisateur u) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM utilisateur WHERE code = ? ");
        ps.setInt(1, u.getCode());
        ps.executeUpdate();
    }

    // Vehicule
}
