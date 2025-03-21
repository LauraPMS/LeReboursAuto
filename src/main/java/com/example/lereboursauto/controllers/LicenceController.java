package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Licence;
import com.example.lereboursauto.models.Permis;
import com.example.lereboursauto.services.LicenceServices;

import java.sql.SQLException;
import java.util.ArrayList;

public class LicenceController {

    private LicenceServices licenceServices;

    public LicenceController() {
        licenceServices = new LicenceServices();
    }

    public ArrayList<Licence> getAllLicencesEleve(int code) throws SQLException {
        return licenceServices.getAllLicencesEleve(code);
    }

    public ArrayList<Licence> getAllLicencesMoniteur(int code) throws SQLException {
        return licenceServices.getAllLicencesMoniteur(code);
    }


}
