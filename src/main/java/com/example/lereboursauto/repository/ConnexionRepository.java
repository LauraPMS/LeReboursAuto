package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.tools.ConnexionBDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnexionRepository {
    private Connection connexion;
    public ConnexionRepository() {
        connexion = ConnexionBDD.getCnx();
    }

    // connexion
    public int LoginMdpEstValide(String login, String password) throws SQLException {
        int codeEleve = 0;
        PreparedStatement ps = connexion.prepareStatement("SELECT code FROM utilisateur WHERE login = ? AND mdp = ?");
        ps.setString(1, login);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
             codeEleve = rs.getInt("code");
        }
        return codeEleve;
    }

    public HashMap<Integer, Integer> nbLeconParPermis (int idUtilisateur) throws SQLException {
        HashMap<Integer, Integer> nbLeconParPermis = new HashMap<>();
        for( int i=1; i<=5; i++){
            PreparedStatement ps = connexion.prepareStatement("SELECT Count(id) FROM lecon WHERE idEleve = ? AND idPermis = ? ");
            ps.setInt(1, idUtilisateur);
            ps.setInt(2, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nbLeconParPermis.put(i, rs.getInt(1));
            }
        }
        return nbLeconParPermis;
    }


}
