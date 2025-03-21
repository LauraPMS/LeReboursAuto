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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
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
    HashMap<String,Integer> dataGraphiqueLeconRNR;
    XYChart.Series<String,Number> serieGraph;

    @javafx.fxml.FXML
    private Text lblPrixLecon;
    @javafx.fxml.FXML
    private Text lblMoniPrixLecon;
    @javafx.fxml.FXML
    private Text lblNomPrenomEleveLecon;
    @javafx.fxml.FXML
    private Text lblMoniModeleVehiculeLecon;
    @javafx.fxml.FXML
    private Text lblMoniPermisLecon;
    @javafx.fxml.FXML
    private Text lblMoniHeureLecon;
    @javafx.fxml.FXML
    private Text lblMoniDateLecon;
    @javafx.fxml.FXML
    private Text lblMoniMarqueVehiculeLecon;
    @javafx.fxml.FXML
    private PieChart graphLeconRNR;
    @javafx.fxml.FXML
    private BarChart graphRevenusParMois;

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

                /** initialisation de la listeView listeLeconEleve au lancement de la session **/
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

                /**
                 *
                 * remplir la vue Permis moniteur ici
                 *
                 */

                /** initialisation de la listeView listeLeconMoniteur au lancement de la session **/
                lvMoniteurResumeLecon.setItems(FXCollections.observableList(leconController.getLeconR(Session.getCodeEleveActif())));
                lvMoniteurResumeLecon.getSelectionModel().selectFirst();

                String idPermisByLecon = lvMoniteurResumeLecon.getSelectionModel().getSelectedItem().toString();

                /** initialisation du label permis **/

                lblMoniPermisLecon.setText(permisController.getPermisByIdLecon(idPermisByLecon));

                /** initialisation du label date **/

                lblMoniDateLecon.setText(leconController.getDateLecon(idPermisByLecon));

                /** initialisation du label heure **/

                lblMoniHeureLecon.setText(leconController.getHeureLecon(idPermisByLecon));

                /** initialisation du label moniteur **/

                lblNomPrenomEleveLecon.setText(utilisateurController.getMoniteurLecon(idPermisByLecon));

                /** initialisation du label marque vehicule **/

                lblMoniMarqueVehiculeLecon.setText(vehiculeController.getMarqueVehiculeLecon(idPermisByLecon));

                /** initialisation du label modele vehicule **/

                lblMoniModeleVehiculeLecon.setText(vehiculeController.getModeleVehiculeLecon(idPermisByLecon));

                /** initialisation du label modele vehicule **/
                String prix = String.valueOf(permisController.getPrixLeconByIdLecon(idPermisByLecon)) + " euros";
                lblMoniPrixLecon.setText(prix);

                /** initialisation du graphique pourcentage de leçons réglées / non réglées **/

                graphiqueLeconMoniteur(Session.getCodeEleveActif());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    /** PARTIE ELEVE **/

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

            lblDateLecon.setText("");
            lblHeureLecon.setText("");
            lblNomPrenomMoniteurLecon.setText("");
            lblMarqueVehiculeLecon.setText("");
            lblModeleVehiculeLecon.setText("");
            lblPrixLecon.setText("");
            lblPermisLecon.setText("");

        }
    }

    /** FIN PARTIE ELEVE **/




    /** PARTIE MONITEUR **/

    @javafx.fxml.FXML
    public void onLvLeconRClicked(Event event) throws SQLException {

        String idLvLecon = lvMoniteurResumeLecon.getSelectionModel().getSelectedItem().toString();

        lblMoniPermisLecon.setText(permisController.getPermisByIdLecon(idLvLecon));

        lblMoniDateLecon.setText(leconController.getDateLecon(idLvLecon));

        lblMoniHeureLecon.setText((leconController.getHeureLecon(idLvLecon)));

        lblNomPrenomEleveLecon.setText(utilisateurController.getEleveLecon(idLvLecon));

        lblMoniMarqueVehiculeLecon.setText(vehiculeController.getMarqueVehiculeLecon(idLvLecon));

        lblMoniModeleVehiculeLecon.setText(vehiculeController.getModeleVehiculeLecon(idLvLecon));

        String prixLecon = String.valueOf(permisController.getPrixLeconByIdLecon(idLvLecon)) + " euros";
        lblMoniPrixLecon.setText(prixLecon);

    }

    public void graphiqueLeconMoniteur(int idMoniteur) throws SQLException {
        /** graphique des lecons réglées/non réglées **/

        graphLeconRNR.getData().clear();

        dataGraphiqueLeconRNR = leconController.getDataGraphiqueLeconRNR(idMoniteur);

        for (String reglee : dataGraphiqueLeconRNR.keySet())
        {
            if (Objects.equals(reglee, "0"))
            {
                graphLeconRNR.getData().add(new PieChart.Data("non réglées", dataGraphiqueLeconRNR.get(reglee)));
            }
            else {
                graphLeconRNR.getData().add(new PieChart.Data("réglées", dataGraphiqueLeconRNR.get(reglee)));
            }
        }
        for (PieChart.Data entry : graphLeconRNR.getData()) {
            Tooltip t = new Tooltip(entry.getPieValue()+ " : "+entry.getName());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }

    }

    public void graphiqueRevenusParMois(int idMoniteur) throws SQLException
    {
        /** graphique des revenus par mois générés par le moniteur **/

        /**
        graphRevenusParMois.getData().clear();

        serieGraph = new XYChart.Series<>();
        serieGraph.setName("Mois");
        for (String valeur : graphRevenusParMois.getDatasGraphique4().keySet())
        {
            serieGraph.getData().add(new XYChart.Data<>(valeur,graphRevenusParMois.getDatasGraphique4().get(valeur)));
        }

        graphRevenusParMois.getData().add(serieGraph);

         **/

    }

    /** FIN PARTIE MONITEUR **/

}
