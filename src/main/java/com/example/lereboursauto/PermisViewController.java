package com.example.lereboursauto;

import com.example.lereboursauto.controllers.LeconController;
import com.example.lereboursauto.controllers.LicenceController;
import com.example.lereboursauto.controllers.PermisController;
import com.example.lereboursauto.controllers.VehiculeController;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.repository.UtilisateurRepository;
import com.example.lereboursauto.services.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private BarChart graphMoniteurObtentionPermis;

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

    UtilisateurRepository uRepo;
    PermisController permisController;
    LeconController leconController;
    VehiculeController vehiculeController;
    Utilisateur u ;
    ArrayList<AnchorPane> listeAp;
    @javafx.fxml.FXML
    private BarChart graphEleveHeuresVehicules;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // instancier les variable / services ici
        uRepo = new UtilisateurRepository();
        permisController = new PermisController();
        leconController = new LeconController();
        vehiculeController = new VehiculeController();

        listeAp = new ArrayList<>();
        listeAp.add(apEleve);
        listeAp.add(apMoniteur);

        try {
            u = uRepo.findByCode(Session.getCodeEleveActif());
            System.out.println(u.getStatut().getLibelle());
            if(u.getStatut().getId() == 1){
                Session.changeAp(listeAp, apEleve);

                /**
                 *
                 * remplir la vue Permis eleve ici
                 *
                 */

                /** initialisation de la listeView listePermisEleve au lancement de la session **/
                lvEleveToutPermis.setItems(FXCollections.observableList(permisController.getLicence(Session.getCodeEleveActif())));
                lvEleveToutPermis.getSelectionModel().selectFirst();

                /** initialisation du label nombreHeuresTotal au lancement de la session **/

                String libellePermis = lvEleveToutPermis.getSelectionModel().getSelectedItem().toString();

                int numeroPermis = permisController.getIdPermisByLibelle(libellePermis);

                int totalHorraire = leconController.getTotalHeuresByLicence(Session.getCodeEleveActif(), numeroPermis);

                lblEleveTempsPermis.setText(String.valueOf(totalHorraire)+ " heures");

                /** initialisation du treeView liste des véhicules utilisés au lancement de la session **/

                /**
                for (String marques : vehiculeController.getVehiculesByPermis(Session.getCodeEleveActif(), numeroPermis).keySet())
                {
                    tvEleveVehicule.setItems(FXCollections.observableList(vehiculeController.getVehiculesByPermis(Session.getCodeEleveActif(), numeroPermis)));
                }
                 **/

                /** initialisation du treeView liste des véhicules utilisés au lancement de la session **/

                graphEleveHeuresVehicules.getData().clear();

                XYChart.Series<Object, Object> serieGraph = new XYChart.Series<>();
                serieGraph.setName("Modèles");
                for (String valeur : leconController.getDataGraphiquePermis().keySet())
                {
                    serieGraph.getData().add(new XYChart.Data<>(valeur,leconController.getDataGraphiquePermis().get(valeur)));
                }

                graphEleveHeuresVehicules.getData().add(serieGraph);




            } else if (u.getStatut().getId() == 2) {
                Session.changeAp(listeAp, apMoniteur);
                // remplir les tableau listeview etc du moniteur ici
                tvMoniteurToutPermis.setItems(FXCollections.observableList(permisController.getLicence(Session.getCodeEleveActif())));
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
