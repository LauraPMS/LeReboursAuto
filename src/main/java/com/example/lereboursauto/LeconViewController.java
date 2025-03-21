package com.example.lereboursauto;

import com.example.lereboursauto.controllers.*;
import com.example.lereboursauto.models.*;
import com.example.lereboursauto.services.Session;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LeconViewController implements Initializable {


    /*
     * Le Controller Lecon doit satisfaire ces besoins
     *  - Prendre une lecon
     *  - Sélectionner et afficher les permis auquel l'eleve est souscrit
     *  - Sélectionner et afficher les moniteurs et véhicule disponible pour le permis et la date et l'horaire donnée
     *  - Enregistrer la leçon
     */



    @javafx.fxml.FXML
    private ListView lvVehiculeDispo, lvMoniteurDispo, lvPermis, lvHorraire;

    @javafx.fxml.FXML
    private DatePicker dpDate;

    LicenceController licenceController;
    LeconController leconController;
    UtilisateurController utilisateurController;
    VehiculeController vehiculeController;
    PermisController permisController;

    ArrayList<Licence> licences;
    int idPermisConcernee = 0;
    String immatriculationConcernee = "";
    int idMoniteurConcernee = 0;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        licenceController = new LicenceController();
        utilisateurController = new UtilisateurController();
        vehiculeController = new VehiculeController();
        leconController = new LeconController();
        permisController = new PermisController();
        licences = new ArrayList<>();

        // Récupérer tt les licences auquels l'utilisateur a souscrit
        try {
            licences = licenceController.getAllLicencesEleve(Session.getCodeEleveActif());
            ArrayList<String> nomLicence = new ArrayList<>();
            nomLicence = permisController.getLicence(Session.getCodeEleveActif());

            lvPermis.setItems(FXCollections.observableList(nomLicence));
            dpDate.setValue(LocalDate.now());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @javafx.fxml.FXML
    public void prendreLecon(ActionEvent actionEvent) throws SQLException {
//        int idPermis = idPermisConcernee;
//        Utilisateur idEleve = utilisateurController.findByCode(Session.getCodeEleveActif());
//        Utilisateur idMoniteur = utilisateurController.findByCode(idMoniteurConcernee);
//        Date date = null;
//        String heure = null;
//        int reglee = 0;
//        String immatriculation = null;
//        if(lvPermis.getSelectionModel().getSelectedItem() != null) {
//            idPermis = idPermisConcernee;
//        }
//        else {
//            Session.creerAlert(Alert.AlertType.ERROR, "A", "Veuillez selectionner un permis");
//        }
//
//        if(lvVehiculeDispo.getSelectionModel().getSelectedItem() != null) {
//            immatriculation = immatriculationConcernee;
//        }
//        else {
//            Session.creerAlert(Alert.AlertType.ERROR, "A", "Veuillez selectionner un véhicule");
//        }
//
//        if(lvMoniteurDispo.getSelectionModel().getSelectedItem() != null) {
//            idMoniteur = idMoniteurConcernee;
//        }
//        else {
//            Session.creerAlert(Alert.AlertType.ERROR, "A", "Veuillez selectionner un moniteur");
//        }
//
//        if(lvHorraire.getSelectionModel().getSelectedItem() != null) {
//
//        }

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


    @javafx.fxml.FXML
    public void updateMoniteur_Vehicule(Event event) throws SQLException {

        // Partie affichage des monniteurs
        System.out.println(lvHorraire.getSelectionModel().getSelectedItem());
        ArrayList<Utilisateur> moniteurs = utilisateurController.getALlMoniteurAvecLicence(idPermisConcernee);
        moniteurs.removeAll(leconController.getMoniteurNonDispo(Date.valueOf(dpDate.getValue()), lvHorraire.getSelectionModel().getSelectedItem().toString()));
        System.out.println("Contenue de moniteur après extraction : " + moniteurs.toString());
        ArrayList<String> nomMoniteur = new ArrayList<>();
        for (Utilisateur moniteur : moniteurs){
            System.out.println(moniteur.getNom());
            nomMoniteur.add(moniteur.getNom());
        }

        lvMoniteurDispo.setItems(FXCollections.observableList(nomMoniteur));

        // Partie affichage des véhicules

        ArrayList<Vehicule> vehicules = vehiculeController.getAllVehiculeByIdPermis(idPermisConcernee);
        ArrayList<String> modeleNonDispo = vehiculeController.getVehiculeNonDispo(Date.valueOf(dpDate.getValue()), lvHorraire.getSelectionModel().getSelectedItem().toString());
        ArrayList<String> allModeleVehicule = new ArrayList<>();
        for (Vehicule vehicule : vehicules){
            allModeleVehicule.add(vehicule.getModele());
        }
        allModeleVehicule.removeAll(modeleNonDispo);
        lvVehiculeDispo.setItems(FXCollections.observableList(allModeleVehicule));

    }

    @javafx.fxml.FXML
    public void updateHoraire(ActionEvent actionEvent) throws SQLException {

        ArrayList<String> horraires = new ArrayList<>();

        for (int heure = 9; heure <= 17; heure++) {
            String h = String.format("%02d:00:00", heure);
            horraires.add(h);
        }

        String date = dpDate.getValue().toString();
        int idPermis = permisController.getIdPermisByLibelle(lvPermis.getSelectionModel().getSelectedItem().toString());
        idPermisConcernee = idPermis;

        // horraires.removeAll(leconController.getHorrairesNonDispo(idPermis, date));
        lvHorraire.setItems(FXCollections.observableList(horraires));

    }

    @javafx.fxml.FXML
    public void updateMoniteurConcernee(Event event) throws SQLException {
        immatriculationConcernee = vehiculeController.getIdByModele(lvVehiculeDispo.getSelectionModel().getSelectedItem().toString());
    }

    @javafx.fxml.FXML
    public void updateVehiculeConcernee(Event event) throws SQLException {
        idMoniteurConcernee = utilisateurController.findIdByName(lvMoniteurDispo.getSelectionModel().getSelectedItem().toString());
    }




}
