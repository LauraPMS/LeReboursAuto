package com.example.lereboursauto;

import com.example.lereboursauto.services.Session;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.io.IOException;

public class LeconViewController {


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
        // récupérer permis souscrit puis afficher

        // récuperer la date dans le dpDate

        // faire une liste avec les horraire en enlevant les horraire s'il y a deja une lecon sur le créneau

        // récupérer puis afficher les moniteurs et véhicule dispo a cette date (moniteur doit avoir la licence du permis et véhicule doit correspondre au permis et doit etre libre)

        // Créer la leçons avec un insert dans le repo (fonction à créer).

        // afficher une alert Session.creerAlert(....)

        // reset tout les champs
    }



}
