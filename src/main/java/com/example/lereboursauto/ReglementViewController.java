package com.example.lereboursauto;

import com.example.lereboursauto.controllers.LeconController;
import com.example.lereboursauto.controllers.PermisController;
import com.example.lereboursauto.controllers.UtilisateurController;
import com.example.lereboursauto.controllers.VehiculeController;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.repository.UtilisateurRepository;
import com.example.lereboursauto.services.Session;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReglementViewController implements Initializable {

    /*

     * Le controlleur Reglement doit satisfaire ces besoins :
     * - Faire un recap de toute les leçons.
     * - coût de chaque permis
     * - payer les lecons non payées
     * - pour le moniteur il doit pouvoir visualiser son salaire en fonction des lecon que l'élève a déja réglée

     */
    @javafx.fxml.FXML
    private Text lblDateLecon;
    @javafx.fxml.FXML
    private Text lblMarqueVehiculeLecon;
    @javafx.fxml.FXML
    private Text lblHeureLecon;
    @javafx.fxml.FXML
    private Text lblRevenuParMois;
    @javafx.fxml.FXML
    private Text lblModeleVehiculeLecon;
    @javafx.fxml.FXML
    private Button btnReglerLecon;
    @javafx.fxml.FXML
    private Text lblPermisLecon;
    @javafx.fxml.FXML
    private Text lblNomPrenomMoniteurLecon;
    @javafx.fxml.FXML
    private Text lblRevenuAnnuel;
    @javafx.fxml.FXML
    private Button majRevenu;
    @javafx.fxml.FXML
    private AnchorPane apEleve, apMoniteur;
    @javafx.fxml.FXML
    private ListView lvMoniteurResumeLecon, lvResumeLecon;

    ArrayList<AnchorPane> listeAp ;
    UtilisateurRepository utilRepo;
    LeconController leconController;
    PermisController permisController;
    UtilisateurController utilisateurController;
    VehiculeController vehiculeController;
    Utilisateur u ;
    @javafx.fxml.FXML
    private Text lblPrixLecon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // instancier les variable / services ici
        utilRepo = new UtilisateurRepository();
        leconController = new LeconController();
        permisController = new PermisController();
        utilisateurController = new UtilisateurController();
        vehiculeController = new VehiculeController();

        listeAp = new ArrayList<>();
        listeAp.add(apEleve);
        listeAp.add(apMoniteur);

        try {

            u = utilRepo.findByCode(Session.getCodeEleveActif());

            if (u.getStatut().getId() == 1)
            {
                Session.changeAp(listeAp, apEleve);

                /**
                 *
                 * remplir la vue Permis eleve ici
                 *
                 */

                /** initialisation de la listeView listePermisEleve au lancement de la session **/
                lvResumeLecon.setItems(FXCollections.observableList(leconController.getLeconNR(Session.getCodeEleveActif())));
                lvResumeLecon.getSelectionModel().selectFirst();

                String idPermisByLecon = lvResumeLecon.getSelectionModel().getSelectedItem().toString();

                /** initialisation du label permis **/

                lblPermisLecon.setText(permisController.getPermisByIdLecon(idPermisByLecon));

                /** initialisation du label date **/

                lblDateLecon.setText(leconController.getDateLecon(idPermisByLecon));

                /** initialisation du label heure **/

                lblHeureLecon.setText(leconController.getHeureLecon(idPermisByLecon));

                /** initialisation du label moniteur **/

                lblNomPrenomMoniteurLecon.setText(utilisateurController.getMoniteurLecon(idPermisByLecon));

                /** initialisation du label marque vehicule **/

                lblMarqueVehiculeLecon.setText(vehiculeController.getMarqueVehiculeLecon(idPermisByLecon));

                /** initialisation du label modele vehicule **/

                lblModeleVehiculeLecon.setText(vehiculeController.getModeleVehiculeLecon(idPermisByLecon));

                /** initialisation du label modele vehicule **/
                String prix = String.valueOf(permisController.getPrixLeconByIdLecon(idPermisByLecon)) + " euros";
                lblPrixLecon.setText(prix);

            }

            else if (u.getStatut().getId() == 2)
            {
                Session.changeAp(listeAp, apMoniteur);
                // remplir les liste view et graphique a la vue moniteur
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    @Deprecated
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
    public void changeToApReglement(ActionEvent actionEvent) throws IOException {
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
    public void onLvLeconNRClicked(Event event) throws SQLException {

        String idLvLecon = lvResumeLecon.getSelectionModel().getSelectedItem().toString();

        lblPermisLecon.setText(permisController.getPermisByIdLecon(idLvLecon));

        lblDateLecon.setText(leconController.getDateLecon(idLvLecon));

        lblHeureLecon.setText((leconController.getHeureLecon(idLvLecon)));

        lblNomPrenomMoniteurLecon.setText(utilisateurController.getMoniteurLecon(idLvLecon));

        lblMarqueVehiculeLecon.setText(vehiculeController.getMarqueVehiculeLecon(idLvLecon));

        lblModeleVehiculeLecon.setText(vehiculeController.getModeleVehiculeLecon(idLvLecon));

        String prixLecon = String.valueOf(permisController.getPrixLeconByIdLecon(idLvLecon)) + " euros";
        lblPrixLecon.setText(prixLecon);



    }

    @javafx.fxml.FXML
    public void onBtnReglerLeconClicked(Event event) throws SQLException {
        if (lvResumeLecon.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez selectionner une lecon !");
            alert.showAndWait();
        }

        else
        {
            String idLecon = lvResumeLecon.getSelectionModel().getSelectedItem().toString();
            leconController.update(idLecon);
            lvResumeLecon.setItems(FXCollections.observableList(leconController.getLeconNR(Session.getCodeEleveActif())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("Votre paiement à bien été accepté !");
            alert.showAndWait();
        }
    }

}
