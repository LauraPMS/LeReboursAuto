package com.example.lereboursauto.repository;

import com.example.lereboursauto.tools.ConnexionBDD;

import java.sql.Connection;

public class LicenceRepository {
    Connection connexion;

    public LicenceRepository() {
        connexion = ConnexionBDD.getCnx();
    }

}
