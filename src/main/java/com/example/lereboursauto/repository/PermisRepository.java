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
                "FROM licence\n" +
                "JOIN permis ON licence.codeCategorie = permis.id\n" +
                "WHERE licence.idUser = ?;\n");
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

    public String getPermisByIdLecon(String idLecon) throws SQLException {
        /** Fonction qui va rechercher un permis selon son libelle **/
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT libelle\n" +
                "FROM permis\n" +
                "JOIN lecon on permis.id = lecon.idPermis\n" +
                "WHERE lecon.id = ?");
        ps.setString(1, idLecon);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String libellePermis = rs.getString("libelle");
        return libellePermis;
    }

    public int getPrixLeconByIdLecon(String idPermis) throws SQLException {
        /** Fonction qui va rechercher le prix d'une lecon selon l'idLecon **/
        int prixLecon = 0;
        PreparedStatement ps;
        ps = connexion.prepareStatement("SELECT prix FROM permis JOIN lecon on permis.id = lecon.idPermis WHERE lecon.id = ?;");
        ps.setString(1, idPermis);
        ResultSet rs = ps.executeQuery();
        rs.next();
        prixLecon = rs.getInt("prix");
        return prixLecon;
    }

}
