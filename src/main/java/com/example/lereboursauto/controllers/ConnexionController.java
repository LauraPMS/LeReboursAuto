package com.example.lereboursauto.controllers;

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
import com.example.lereboursauto.tools.ConnexionBDD;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnexionController implements Initializable {


    @FXML
    private TableColumn tcAction;
    @FXML
    private AnchorPane apInscription;
    @FXML
    private TextField txtInscriptionTelephone;
    @FXML
    private TextField txtInscriptionNom;
    @FXML
    private AnchorPane apConnexion;
    @FXML
    private ToggleGroup rdoStaut;
    @FXML
    private AnchorPane apAccueil;
    @FXML
    private TextField txtInscriptionAdresse;
    @FXML
    private RadioButton rdoGenreHomme;
    @FXML
    private TextField txtConnexionPassword;
    @FXML
    private TextField txtInscriptionLogin;
    @FXML
    private TextField txtInscriptionPassword;
    @FXML
    private RadioButton rdoEleve;
    @FXML
    private TextField txtInscriptionVille;
    @FXML
    private RadioButton rdoGenreFemme;
    @FXML
    private ToggleGroup rdoGenre;
    @FXML
    private TableColumn tcPrixPermis;
    @FXML
    private TextField txtInscriptionPrenom;
    @FXML
    private TextField txtInscriptionCP;
    @FXML
    private DatePicker dpInscriptionDateNaissance;
    @FXML
    private RadioButton rdoMoniteur;
    @FXML
    private TableColumn tcLibelleCategorie;
    @FXML
    private TextField txtConnexionLogin;

    ConnexionBDD connexionBDD;




    @FXML
    public void inscription(ActionEvent actionEvent) {

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

    }

    @FXML
    public void connexion(ActionEvent actionEvent) {

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

    }


    // fonction réccurrente
    public void clearAll(){
        apInscription.setVisible(false);
        apConnexion.setVisible(false);
    }

    public void changeAp(AnchorPane ap){
        clearAll();
        ap.setVisible(true);
    }

    @FXML
    public void changeApConnexion(ActionEvent actionEvent) {changeAp(apConnexion);}

    @FXML
    public void changeApInscription(ActionEvent actionEvent) {changeAp(apInscription);}

    @FXML
    public void changeApAccueil(Event event) {clearAll();}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            connexionBDD = new ConnexionBDD();

            changeAp(apAccueil);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
