package com.example.lereboursauto.controllers;

import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.repository.ConnexionRepository;
import com.example.lereboursauto.repository.UtilisateurRepository;
import com.example.lereboursauto.services.ConnexionServices;
import com.example.lereboursauto.services.Session;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {


    @javafx.fxml.FXML
    private AnchorPane apProfil, apModifier;

    @javafx.fxml.FXML
    private Pane empMoto, empBateau, empTrain, empCamion, empVoiture;

    @javafx.fxml.FXML
    private Text lblCp, lblAdresse, lblVille, lblAge, lblPrenom, lblNom, lblTelephone ;

    @javafx.fxml.FXML
    private TextField txtCodePostal, txtAdresse, txtTelephone, txtVille;

    @javafx.fxml.FXML
    private TableColumn tcHorraireProchaine, tcCategorieProchaine, tcDateProchaine;


    // service utilisé ici


    int numEleve = Session.getCodeEleveActif();
    Utilisateur utilisateur;
    ArrayList<AnchorPane> listeAp;
    UtilisateurRepository uRepo;
    HashMap<Integer, Integer> nbLeconPermis;
    ConnexionServices connexionServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(numEleve);
        listeAp = new ArrayList<>();
        listeAp.add(apProfil);
        listeAp.add(apModifier);
        connexionServices = new ConnexionServices();
        nbLeconPermis = new HashMap<>();

        uRepo = new UtilisateurRepository();

        try {
            utilisateur = uRepo.findByCode(numEleve);
            majProfil(utilisateur);
            changeApToProfil(new Event(Event.ANY));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @javafx.fxml.FXML
    public void btnMajInfo(ActionEvent actionEvent) throws SQLException {
        // Mettre à jour l'objet utilisateur
        utilisateur.setTelephone(txtTelephone.getText().isEmpty() ? utilisateur.getTelephone() : txtTelephone.getText());
        utilisateur.setAdresse(txtAdresse.getText().isEmpty() ? utilisateur.getAdresse() : txtAdresse.getText());
        utilisateur.setVille(txtVille.getText().isEmpty() ? utilisateur.getVille() : txtVille.getText());

        // Gestion des valeurs numériques avec vérification
        try {
            utilisateur.setCodePostal(txtCodePostal.getText().isEmpty() ? utilisateur.getCodePostal() : Integer.parseInt(txtCodePostal.getText()));
        } catch (NumberFormatException e) {
            Session.creerAlert(Alert.AlertType.ERROR, "Erreur", "Le code postal doit être un nombre valide.");
            return;
        }

        // Mettre à jour la base de données
        uRepo.update(utilisateur);

        // Mettre à jour les labels affichés
        majProfil(utilisateur);
    }

    public void majProfil(Utilisateur u){
        lblTelephone.setText(u.getTelephone());
        lblAdresse.setText(u.getAdresse());
        lblVille.setText(u.getVille());
        lblNom.setText(u.getNom());
        lblPrenom.setText(u.getPrenom());
        lblCp.setText(String.valueOf(u.getCodePostal()));

        LocalDate dateNaissance = u.getDateNaissance().toLocalDate(); // Si c'est un java.sql.Date
        int age = Period.between(dateNaissance, LocalDate.now()).getYears();

        lblAge.setText(String.valueOf(age));
    }





    @javafx.fxml.FXML
    public void changeApToProfil(Event event) throws SQLException {
        Session.changeAp(listeAp, apProfil);
        // procédé a la vérification du passage de permis possible
        HashMap<Integer, Integer> nbLeconPermis = connexionServices.countNbLeconParPermis((numEleve));

        // Utilisation d'une liste pour stocker les IDs des permis atteignant 15 leçons
        ArrayList<Integer> permisAvec15Lecons = new ArrayList<>();

        for (Integer idPermis : nbLeconPermis.keySet()) {
            if (nbLeconPermis.get(idPermis) >= 5) {
                permisAvec15Lecons.add(idPermis);
            }
        }

        // Affichage d'un message si un permis atteint 15 leçons
        if (!permisAvec15Lecons.isEmpty()) {
            StringBuilder message = new StringBuilder("Vous avez atteint 15 leçons pour les permis suivants :\n");

            for (Integer idPermis : permisAvec15Lecons) {
                message.append("- Permis ID ").append(idPermis).append("\n");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nombre de leçons atteint");
            alert.setHeaderText(null);
            alert.setContentText(message.toString());
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void changeToApPermis(ActionEvent actionEvent) throws IOException {changeApToPermis(actionEvent);}


    // changement de scene (onglet)

    @javafx.fxml.FXML
    public void changeToApPlanning(ActionEvent actionEvent) throws IOException {
        Session.changerScene("planning.fxml", "Le Rebours Auto - Planning -", actionEvent);
    }


    @javafx.fxml.FXML
    public void seDeconnecter(ActionEvent actionEvent) throws IOException {
        Session.setCodeEleveActif(0);
        Session.changerScene("connexion.fxml", "Le Rebours Auto", actionEvent);
    }


    @javafx.fxml.FXML
    public void changeToApRéglement(ActionEvent actionEvent) throws IOException {
        Session.changerScene("lecon.fxml", "Prendre une Lecon", actionEvent);
    }


    @javafx.fxml.FXML
    public void changeApToModifier(ActionEvent actionEvent) {
        Session.changeAp(listeAp, apModifier);
    }


    @javafx.fxml.FXML
    public void changeApToLecon(ActionEvent actionEvent) throws IOException {
        Session.changerScene("lecon.fxml", "Prendre une Lecon", actionEvent);
    }


    @javafx.fxml.FXML
    public void changeApToPermis(ActionEvent actionEvent) throws IOException {
        Session.changerScene("permis.fxml", "Permis", actionEvent);
    }



}
