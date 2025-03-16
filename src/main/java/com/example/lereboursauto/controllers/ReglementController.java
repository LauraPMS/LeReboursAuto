package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.repository.UtilisateurRepository;
import com.example.lereboursauto.services.Session;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReglementController implements Initializable {

    /*

     * Le controlleur Reglement doit satisfaire ces besoins :
     * - Faire un recap de toute les leçons.
     * - coût de chaque permis
     * - payer les lecons non payé
     * - pour le moniteur il doit pouvoir visualiser son salaire en fonction des lecon que l'élève a déja réglée

     */

    ArrayList<AnchorPane> listeAp ;
    UtilisateurRepository utilRepo;
    Utilisateur u ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listeAp = new ArrayList<>();
        listeAp.add(apEleve);
        listeAp.add(apMoniteur);
        Session.changeAp(listeAp, apMoniteur);
        try {
            u = utilRepo.findByCode(Session.getCodeEleveActif());
            if (u.getStatut().equals(1)){
                Session.changeAp(listeAp, apEleve);
                // remplir les liste view appartenant a la vue eleve
            } else if (u.getStatut().equals(2)) {
                Session.changeAp(listeAp, apMoniteur);
                // remplir les liste view et graphique a la vue moniteur
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    private Text lblMarqueVéhicule, lblModeleVehicule, lblRevenuAnnuel, lblNomPrenomMoniteur, lblRevenuParMois, lblReglee;

    @javafx.fxml.FXML
    private Button majRevenu, btnregleeLecon;

    @javafx.fxml.FXML
    private AnchorPane apEleve, apMoniteur;

    @javafx.fxml.FXML
    private ListView lvMoniteurResumeLecon, lvResumeLecon;



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





}
