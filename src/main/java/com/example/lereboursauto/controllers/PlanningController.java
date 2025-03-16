package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.repository.LeconRepository;
import com.example.lereboursauto.repository.UtilisateurRepository;
import com.example.lereboursauto.services.Session;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlanningController implements Initializable {

    /*
     * Le controller Planning doit satisfaire ces besoins :
     * - Pour l'élève toutes les lecons sont affichés
     * - Pour le moniteur les lecons sont affichés sur 7 jours
     * - L'élève doit pouvoir prendre une leçon (donc acceder a la page lecon)
     *
     */

    @javafx.fxml.FXML
    private TableColumn tcNomEleveMoniteur, tcModeleVehiculeEleve, tcModeleVehiculeMoniteur, tcHeureMoniteur, tcDateEleve, tcDateMoniteur, tcPermisEleve, tcRegleeEleve, tcMoniteurEleve, tcHorraireEleve, tcPermisMoniteur, tcImmatriculationMoniteur;

    @javafx.fxml.FXML
    private AnchorPane apEleve, apMoniteur;

    @javafx.fxml.FXML
    private TableView tvPlanningMoniteur, tvPlanningEleve;

    UtilisateurRepository uRepo;
    LeconRepository lrepo;
    Utilisateur u ;
    ArrayList<AnchorPane> listeAp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // instancier les variable / services ici
        uRepo = new UtilisateurRepository();
        lrepo = new LeconRepository();
        listeAp = new ArrayList<>();
        listeAp.add(apEleve);
        listeAp.add(apMoniteur);

        // initialisation des colonnes ELeve
        tcDateEleve.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcHorraireEleve.setCellValueFactory(new PropertyValueFactory<>("heure"));
        tcPermisEleve.setCellValueFactory(new PropertyValueFactory<>("nomPermis"));
        tcMoniteurEleve.setCellValueFactory(new PropertyValueFactory<>("nomPrenomMoniteur"));
        tcModeleVehiculeEleve.setCellValueFactory(new PropertyValueFactory<>("modeleVehicule"));
        tcRegleeEleve.setCellValueFactory(new PropertyValueFactory<>("reglee"));

        // initialisation des colonnes Moniteur
        tcDateMoniteur.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcHeureMoniteur.setCellValueFactory(new PropertyValueFactory<>("heure"));
        tcModeleVehiculeMoniteur.setCellValueFactory(new PropertyValueFactory<>("modeleVehicule"));
        tcImmatriculationMoniteur.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        tcPermisMoniteur.setCellValueFactory(new PropertyValueFactory<>("nomPermis"));
        tcNomEleveMoniteur.setCellValueFactory(new PropertyValueFactory<>("nomPrenomEleve"));

        try {

            u = uRepo.findByCode(Session.getCodeEleveActif());
            System.out.println(u.getStatut().getLibelle());
            if(u.getStatut().getId() == 1){
                Session.changeAp(listeAp, apEleve);
                // remplir la vu eleve ici
                tvPlanningEleve.setItems(FXCollections.observableList(lrepo.getAllLeconForEleve(Session.getCodeEleveActif())));
            } else if (u.getStatut().getId() == 2) {
                Session.changeAp(listeAp, apMoniteur);
                // remplir les tableau listeview etc du moniteur ici
                tvPlanningMoniteur.setItems(FXCollections.observableList(lrepo.getAllLeconForMoniteur(Session.getCodeEleveActif())));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     ----------------  Navigation  ------------------
     */


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
    public void changeToApLecon(ActionEvent actionEvent) throws IOException {
        Session.changerScene("lecon.fxml", "Le Rebours Auto - Lecon", actionEvent);
    }

    /*
            Fonction de remplissage des controlleurs graphiques
    */

    // Retourne les valeurs



}
