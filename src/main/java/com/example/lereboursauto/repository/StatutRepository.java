package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Statut;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatutRepository {

    private Connection connexion;

    public StatutRepository() {
        connexion = ConnexionBDD.getCnx();
    }

    public Statut findById(int id) throws SQLException {
        Statut statut = null;
        PreparedStatement statement = connexion.prepareStatement("select id, libelle FROM statut WHERE id = ?");
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            statut = new Statut(rs.getInt("id"),rs.getString("libelle"));
        }
        return statut;
    }
}
