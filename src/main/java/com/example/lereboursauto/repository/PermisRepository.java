package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Permis;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PermisRepository {
    Connection connexion;
    public PermisRepository() {
        connexion = ConnexionBDD.getCnx();
    }

    public Permis findByCode(int id) throws SQLException {
        Permis p = null;
        PreparedStatement ps = connexion.prepareStatement("Select id, libelle, prix FROM permis where id = ? ");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = new Permis(rs.getInt("id"), rs.getString("libelle"), rs.getFloat("prix"));
        }
        return p;
    }

    public ArrayList<String> getLicence(int statutUser) throws SQLException {

        /** Fonction qui va rechercher la liste des permis de l'utilisateur **/

        ArrayList<String> libelleLicenceEleve = new ArrayList<>();
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT permis.libelle\n" +
                "FROM permis\n" +
                "JOIN licence on permis.id = licence.codeCategorie\n" +
                "WHERE idUser = ?;");
        ps.setInt(1, statutUser);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            libelleLicenceEleve.add(rs.getString("libelle"));
        }
        return libelleLicenceEleve;
    }

    public int getIdPermisByLibelle(String licenceUser) throws SQLException {
        /** Fonction qui va rechercher un permis selon son libelle **/
        int numPermis = 0;
        PreparedStatement ps;
        ps = connexion.prepareStatement("Select id\n" +
                "FROM permis\n" +
                "WHERE libelle = ?;");
        ps.setString(1, licenceUser);
        ResultSet rs = ps.executeQuery();
        rs.next();
        numPermis = rs.getInt("id");
        return numPermis;
    }

    public int getIdByNom (String nom) throws SQLException{
        PreparedStatement ps = connexion.prepareStatement("SELECT id FROM permis where libelle = ?;");
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

}
