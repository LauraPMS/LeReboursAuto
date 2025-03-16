package com.example.lereboursauto.repository;

import com.example.lereboursauto.models.Permis;
import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermisRepository {
    Connection connection;
    public PermisRepository() {
        connection = ConnexionBDD.getCnx();
    }

    public Permis findByCode(int id) throws SQLException {
        Permis p = null;
        PreparedStatement ps = connection.prepareStatement("Select id, libelle, prix FROM permis where id = ? ");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = new Permis(rs.getInt(id), rs.getString("libelle"), rs.getFloat("prix"));
        }
        return p;
    }
}
