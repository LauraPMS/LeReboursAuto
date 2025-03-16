package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Statut;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.services.ConnexionServices;
import com.example.lereboursauto.services.Session;
import com.example.lereboursauto.services.StatutServices;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.example.lereboursauto.controllers.*;
import com.example.lereboursauto.repository.*;
import com.example.lereboursauto.tools.ConnexionBDD;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static com.example.lereboursauto.services.Session.changeAp;
import static com.example.lereboursauto.services.Session.setCodeEleveActif;

public class ConnexionController implements Initializable {
    /*
    * La page connexion doit satisfaire ces besoins :
    * - La connexion d'un utilisateur
    * - L'inscription d'un nouvel utilisateur
    * - Afficher la liste des permis et leur prix
    * */


    @FXML
    private TableColumn tcAction,tcPrixPermis,tcLibelleCategorie;
    @FXML
    private AnchorPane apInscription, apConnexion,apAccueil;
    @FXML
    private TextField txtInscriptionTelephone, txtInscriptionNom,txtInscriptionAdresse, txtInscriptionLogin,txtInscriptionPassword, txtInscriptionVille,txtInscriptionPrenom, txtInscriptionCP;
    @FXML
    private TextField txtConnexionPassword, txtConnexionLogin;
    @FXML
    private ToggleGroup rdoStaut, rdoGenre;
    @FXML
    private RadioButton rdoEleve, rdoGenreFemme, rdoMoniteur, rdoGenreHomme;
    @FXML
    private DatePicker dpInscriptionDateNaissance;


    ConnexionBDD connexionBDD;
    ConnexionServices connexionServices;
    StatutServices statutServices;

    ArrayList<AnchorPane> listeAp;
    HashMap<Integer, Integer> nbLeconPermis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            connexionBDD = new ConnexionBDD();
            connexionServices = new ConnexionServices();
            statutServices = new StatutServices();
            nbLeconPermis = new HashMap<>();

            listeAp = new ArrayList<>();
            listeAp.add(apConnexion);
            listeAp.add(apAccueil);
            listeAp.add(apInscription);

            changeAp(listeAp, apAccueil);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    public void inscription(ActionEvent actionEvent) throws SQLException {

        /*
        *  Vérifié si le login existe deja dans la base de données
        *  Si le login existe
        *       On affiche une alerte pour indiquer a l'utilisateur qu'il existe deja un compte avec ce login
        *  Si le login n'existe pas
        *       On vérifie que tout les champs sont rempli correctement
        *       Sinon on affiche une erreur en précisant ce qui manque, ce qui n'est pas normal
        *       Si tout est ok on crée l'utilisateur dans la base de données en metant a jour son statut
        *       On renvoie ensuite vers la page connexion
        */

        if(txtInscriptionLogin.getText().isEmpty() || txtInscriptionPassword.getText().isEmpty()) {
            Session.creerAlert(Alert.AlertType.ERROR, "Champs manquants", "Saisissez votre login et mot de passe");

        } else if (txtInscriptionVille.getText().isEmpty() || txtInscriptionCP.getText().isEmpty() || txtInscriptionAdresse.getText().isEmpty()) {
            Session.creerAlert(Alert.AlertType.ERROR, "Champs manquants", "Saisissez votre adresse bien séparé avec la ville et le code postal");

        } else if (txtInscriptionNom.getText().isEmpty() || txtInscriptionPrenom.getText().isEmpty()) {
            Session.creerAlert(Alert.AlertType.ERROR, "Champs manquants", "Saisissez votre nom et prénom correctement");

        } else if (txtInscriptionTelephone.getText().isEmpty()) {
            Session.creerAlert(Alert.AlertType.ERROR, "Champs manquants", "Saisissez votre numéro de téléphone");

        } else if (!rdoGenreFemme.isSelected() && !rdoGenreHomme.isSelected()) {
            Session.creerAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez selectionner votre sexe");

        } else if (!rdoEleve.isSelected() && !rdoMoniteur.isSelected()) {
            Session.creerAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez selectionner votre statut (eleve ou moniteur)");

        } else if (dpInscriptionDateNaissance.getValue().toString().isEmpty()) {
            Session.creerAlert(Alert.AlertType.ERROR, "Champs manquants", "Veuillez selectionner votre date de naissance");

        } else{
            // tt les champs sont remplis, on procède à l'inscription
            int sexe;
            
            sexe = 0;

            if (rdoGenreFemme.isSelected()) {
                sexe = 1;
            }
            int statut = 1;

            if (rdoMoniteur.isSelected()) {
                statut = 2;
            }
            Statut s = statutServices.findById(statut);
            System.out.println(s.getId());
            Utilisateur u = new Utilisateur(0, txtInscriptionNom.getText(), txtInscriptionPrenom.getText(), Date.valueOf(dpInscriptionDateNaissance.getValue()), txtInscriptionTelephone.getText(), sexe, txtInscriptionAdresse.getText(), txtInscriptionVille.getText(), Integer.parseInt(txtInscriptionCP.getText()),txtConnexionLogin.getText(), txtInscriptionPassword.getText(),s);
            System.out.println(Integer.toString(u.getStatut().getId()));

            connexionServices.inscrireUtilisateur(u);

            changeAp(listeAp, apConnexion);

        }
        changeAp(listeAp, apAccueil);
    }

    @FXML
    public void connexion(ActionEvent actionEvent) throws SQLException, IOException {

        /*
        *  Si le login et le mot de passe ne correspondent pas :
        *       afficher une alerte
        *       nettoyer puis renvoyer sur le formulaire de connexion
        *  Si les 2 existent et correspondent :
        *       Récupérer le statut
        *       Si c'est un eleve
        *           récupérer toutes les données de l'eleve
        *           Si des champs sont vides ou pas valides :
        *               Mettre des valeurs par défaut pour pas faire planter le programme
        *           renvoyer vers la fxml de l'eleve
        *       Si c'est un moniteur
        *           récupérer toutes les données du moniteur
        *           renvoyer vers le fxml du moniteur
        *
        */

        if (txtConnexionLogin.getText().equals(null) || txtConnexionPassword.getText().equals(null))
        {
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType);
            alert.initOwner(apAccueil.getScene().getWindow());
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs login et mot de passe requis !");
        }
        else {
            String login = txtConnexionLogin.getText();
            String password = txtConnexionPassword.getText();
            Utilisateur u = connexionServices.verifUser(login, password);

            if (u != null) {
                setCodeEleveActif(u.getCode());

                // Redirection en fonction du statut
                if (u.getStatut().getId() == 1) {
                    Session.changerScene("profil.fxml", "Le ReboursAuto - Élève - ProfilController", actionEvent);
                } else if (u.getStatut().getId() == 2) {
                    Session.changerScene("profil.fxml", "Le ReboursAuto - Moniteur - ProfilController", actionEvent);
                } else if (u.getStatut().getId() == 3) {
                    Session.changerScene("profil.fxml", "Le ReboursAuto - Admin - ProfilController", actionEvent);
                }
            }
        }
    }

    @FXML
    public void changeApConnexion(ActionEvent actionEvent) {
        changeAp(listeAp, apConnexion);}

    @FXML
    public void changeApInscription(ActionEvent actionEvent) {changeAp(listeAp, apInscription);}

    @FXML
    public void changeApAccueil(Event event) {Session.changeAp(listeAp, apAccueil);}



}
