package com.example.lereboursauto.repository;

import com.example.lereboursauto.tools.ConnexionBDD;
import java.sql.Connection;

public class ConnexionRepository {
    private Connection connexion;
    public ConnexionRepository() {
        connexion = ConnexionBDD.getCnx();
    }

    // connexion



}
