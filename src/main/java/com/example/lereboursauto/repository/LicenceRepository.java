package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Licence;
import com.example.lereboursauto.models.Permis;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;


public class LicenceRepository {
    Connection connexion;
    PermisRepository permisRepository;
    UtilisateurRepository utilisateurRepository;

    public LicenceRepository() {
        connexion = ConnexionBDD.getCnx();
        permisRepository = new PermisRepository();
        utilisateurRepository = new UtilisateurRepository();
    }

    public ArrayList<Licence> getAllLicencesEleve(int code) throws SQLException {
        ArrayList<Licence> licences = new ArrayList<>();
        String sql = "SELECT id, idUser, codeCategorie, dateObtention FROM licence WHERE idUser = ?";
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1, code);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int idUser = rs.getInt("idUser");
            Utilisateur u = utilisateurRepository.findByCode(idUser);
            int codeCategorie = rs.getInt("codeCategorie");
            Permis p = permisRepository.findByCode(codeCategorie);
            Date dateObtention = rs.getDate("dateObtention");
            Licence l = new Licence(id,p, u, dateObtention );
            licences.add(l);
        }
        return licences;
    }

    public ArrayList<Licence> getAllLicencesMoniteur(int code) throws SQLException {
        ArrayList<Licence> licences = new ArrayList<>();
        String sql = "SELECT id, idUser, codeCategorie, dateObtention FROM licence WHERE idUser = ?";
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1, code);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int idUser = rs.getInt("idUser");
            Utilisateur u = utilisateurRepository.findByCode(idUser);
            int codeCategorie = rs.getInt("codeCategorie");
            Permis p = permisRepository.findByCode(codeCategorie);
            Date dateObtention = rs.getDate("dateObtention");
            Licence l = new Licence(id,p, u, dateObtention );
            licences.add(l);
        }
        return licences;
    }

    public void createLicence(Licence licence) throws SQLException {
        PreparedStatement ps = connexion.prepareStatement("INSERT INTO licence (idUser, codeCategorie, dateObtention) VALUES (?, ?, ?)");
        ps.setInt(1, licence.getIdUtilisateur().getCode());
        ps.setInt(2, licence.getIdCategorie().getId());
        ps.setDate(3, licence.getDateObtention());
        ps.executeUpdate();
    }

}
