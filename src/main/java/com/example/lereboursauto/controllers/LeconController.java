package com.example.lereboursauto.controllers;

import com.example.lereboursauto.services.Session;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.io.IOException;

public class LeconController {


    /*
     * Le Controller Lecon doit satisfaire ces besoins
     *  - Prendre une lecon
     *  - Sélectionner et afficher les permis auquel l'eleve est souscrit
     *  - Sélectionner et afficher les moniteurs et véhicule disponible pour le permis et la date et l'horaire donnée
     *  - Enregistrer la leçon
     */



    @javafx.fxml.FXML
    private ListView lvVehiculeDispo;
    @javafx.fxml.FXML
    private ListView lvMoniteurDispo;
    @javafx.fxml.FXML
    private ListView lvPermis;
    @javafx.fxml.FXML
    private DatePicker dpDate;
    @javafx.fxml.FXML
    private ListView lvHorraire;

    @javafx.fxml.FXML
    public void changeToApPlanning(ActionEvent actionEvent) throws IOException {
        Session.changerScene("planning.fxml", "Le Rebours Auto - Planning", actionEvent);
    }

    @javafx.fxml.FXML
    public void seDeconnecter(ActionEvent actionEvent) throws IOException {
        Session.changerScene("connexion.fxml", "Le Rebours Auto - Connexion", actionEvent);
    }

    @javafx.fxml.FXML
    public void changeToApRéglement(ActionEvent actionEvent) throws IOException {
        Session.changerScene("reglement.fxml", "Le Rebours Auto - Reglement", actionEvent);
    }

    @javafx.fxml.FXML
    public void changeApToProfil(ActionEvent actionEvent) throws IOException {
        Session.changerScene("profil.fxml", "Le Rebours Auto - Profil", actionEvent);
    }

    @javafx.fxml.FXML
    public void changeToApPermis(ActionEvent actionEvent) throws IOException {
        Session.changerScene("permis.fxml", "Le Rebours Auto - Permis", actionEvent);
    }

    @javafx.fxml.FXML
    public void prendreLecon(ActionEvent actionEvent) {
    }



}
