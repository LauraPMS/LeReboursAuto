package com.example.lereboursauto.controllers;

import com.example.lereboursauto.services.Session;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ReglementController {
    @javafx.fxml.FXML
    private Text lblMarqueVéhicule;
    @javafx.fxml.FXML
    private Button majRevenu;
    @javafx.fxml.FXML
    private AnchorPane apEleve;
    @javafx.fxml.FXML
    private Button btnregleeLecon;
    @javafx.fxml.FXML
    private Text lblModeleVehicule;
    @javafx.fxml.FXML
    private ListView lvResumeLecon;
    @javafx.fxml.FXML
    private ListView lvMoniteurResumeLecon;
    @javafx.fxml.FXML
    private Text lblRevenuAnnuel;
    @javafx.fxml.FXML
    private Text lblNomPrenomMoniteur;
    @javafx.fxml.FXML
    private Text lblReglee;
    @javafx.fxml.FXML
    private Text lblRevenuParMois;
    @javafx.fxml.FXML
    private AnchorPane apMoniteur;


    @javafx.fxml.FXML
    public void reglerLecon(ActionEvent actionEvent) {
    }

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
    /*

    * Le controlleur Reglement doit satisfaire ces besoins :
    * - Faire un recap de toute les leçons.
    * - coût de chaque permis
    * - payer les lecons non payé
    * - pour le moniteur il doit pouvoir visualiser son salaire en fonction des lecon que l'élève a déja réglée

    */
}
