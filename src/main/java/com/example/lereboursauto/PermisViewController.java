package com.example.lereboursauto;

import com.example.lereboursauto.services.Session;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PermisViewController implements Initializable {

    /*
    *  La partie PermisViewController doit satisfaire ces différents besoins :
    *  - Le remplissage de la liste view des permis
    *  - affichage des stats (nb heure du permis si souscrit, graphique, progress bar jusqu'au nombre d'heure requis pour l'examen)
    *  - permettre la souscription a un permis (ajout dans licence avec une date d'obtention null
    *  - remplir la table view de l'eleve et du moniteur pour savoir avec qui et quel véhicule il a déja fait des leçons
    *
    */



    @javafx.fxml.FXML
    private BarChart graphElevePermisObtenu, graphMoniteurObtentionPermis;

    @javafx.fxml.FXML
    private TableView tvEleveVehicule,tvMoniteurVehicule;
    @javafx.fxml.FXML
    private TableColumn tcEleveMarque, tcEleveModele, tcMoniteurMarque, tcMoniteurModele;

    @javafx.fxml.FXML
    private TableView tvEleveMoniteur, tvMoniteurListeEleve;
    @javafx.fxml.FXML
    private TableColumn tcMoniteurNomPrenomEleve, tcEleveNomPrenom, tcMoniteurNbLeconPasse, tcEleveNbLecon;

    @javafx.fxml.FXML
    private Text lblEleveTempsPermis, lblMoniteurTempPasse;

    @javafx.fxml.FXML
    private AnchorPane apEleve, apMoniteur;

    @javafx.fxml.FXML
    private ListView lvEleveToutPermis, tvMoniteurToutPermis;

    @javafx.fxml.FXML
    private ImageView imgMoniteurVehicule;
    @javafx.fxml.FXML
    private ImageView imgVehicule;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



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
    public void changeApToProfil(Event event) throws IOException {
        Session.changerScene("profil.fxml", "Le Rebours Auto - Profil", event);
    }

    @javafx.fxml.FXML
    public void suggererLicence(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void demandeLicence(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void changeToApPermis(ActionEvent actionEvent) throws IOException {
        Session.changerScene("permis.fxml", "Le Rebours Auto - Permis", actionEvent);
    }


}
