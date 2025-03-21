package com.example.lereboursauto.services;

import com.example.lereboursauto.models.Licence;
import com.example.lereboursauto.repository.LicenceRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class LicenceServices {

    private LicenceRepository licenceRepository;

    public LicenceServices() {
        licenceRepository = new LicenceRepository();
    }

    public ArrayList<Licence> getAllLicencesEleve(int code) throws SQLException {
        return licenceRepository.getAllLicencesEleve(code);
    }

    public ArrayList<Licence> getAllLicencesMoniteur(int code) throws SQLException {
        return licenceRepository.getAllLicencesMoniteur(code);
    }

    public void createLicence(Licence licence) throws SQLException {
        licenceRepository.createLicence(licence);
    }



    }
