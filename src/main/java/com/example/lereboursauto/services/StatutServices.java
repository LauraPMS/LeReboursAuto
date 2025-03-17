package com.example.lereboursauto.services;

import com.example.lereboursauto.models.Statut;
import com.example.lereboursauto.repository.StatutRepository;

import java.sql.SQLException;

public class StatutServices {

    private StatutRepository statutRepository;
    public StatutServices() {
        statutRepository = new StatutRepository();
    }

    public Statut findById(int id) throws SQLException {
        return statutRepository.findById(id);
    }

}
