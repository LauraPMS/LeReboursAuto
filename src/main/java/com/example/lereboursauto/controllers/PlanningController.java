package com.example.lereboursauto.controllers;

import com.example.lereboursauto.services.Session;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlanningController {
    @javafx.fxml.FXML
    private TableColumn tcDateEleve;
    @javafx.fxml.FXML
    private TableColumn tcDateMoniteur;
    @javafx.fxml.FXML
    private TableColumn tcPermisEleve;
    @javafx.fxml.FXML
    private AnchorPane apEleve;
    @javafx.fxml.FXML
    private TableColumn tcRegleeEleve;
    @javafx.fxml.FXML
    private TableColumn tcMoniteurEleve;
    @javafx.fxml.FXML
    private TableView tvPlanningMoniteur;
    @javafx.fxml.FXML
    private TableColumn tcHorraireEleve;
    @javafx.fxml.FXML
    private TableColumn tcPermisMoniteur;
    @javafx.fxml.FXML
    private TableColumn tcImmatriculationMoniteur;
    @javafx.fxml.FXML
    private TableColumn tcHeureMoniteur;
    @javafx.fxml.FXML
    private TableColumn tcModeleVehiculeMoniteur;
    @javafx.fxml.FXML
    private TableView tvPlanningEleve;
    @javafx.fxml.FXML
    private TableColumn tcModeleVehiculeEleve;
    @javafx.fxml.FXML
    private TableColumn tcNomEleveMoniteur;
    @javafx.fxml.FXML
    private AnchorPane apMoniteur;

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
    public void changeToApLecon(ActionEvent actionEvent) {
    }








    /*
    * Le controller Planning doit satisfaire ces besoins :
    * - Pour l'élève toutes les lecons sont affichés
    * - Pour le moniteur les lecons sont affichés sur 7 jours
    * - L'élève doit pouvoir prendre une leçon (donc acceder a la page lecon)
    *
     */
}
