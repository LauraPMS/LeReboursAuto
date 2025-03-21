package com.example.lereboursauto;

import com.example.lereboursauto.controllers.*;
import com.example.lereboursauto.models.Utilisateur;
import com.example.lereboursauto.services.Session;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
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
import java.util.HashMap;
import java.util.ResourceBundle;

public class PermisViewController implements Initializable {

    @javafx.fxml.FXML
    private TableView tvEleveVehicule;
    @javafx.fxml.FXML
    private TableColumn tcEleveMarque, tcEleveModele;

    @javafx.fxml.FXML
    private TableView tvMoniteurListeEleve;

    @javafx.fxml.FXML
    private Text lblEleveTempsPermis;

    @javafx.fxml.FXML
    private AnchorPane apEleve, apMoniteur;

    @javafx.fxml.FXML
    private ListView lvEleveToutPermis;

    @javafx.fxml.FXML
    private ImageView imgMoniteurVehicule;
    @javafx.fxml.FXML
    private ImageView imgVehicule;
    @javafx.fxml.FXML
    private TableColumn tcMoniteurNbLeconPasse;
    @javafx.fxml.FXML
    private TableColumn tcMoniteurNomPrenomEleve;

    @javafx.fxml.FXML
    private BarChart graphEleveHeuresVehicules;

    XYChart.Series<String,Number> serieGraph1;

    @javafx.fxml.FXML
    private LineChart graphEleveHeuresMoniteurs;


    UtilisateurController utilisateurController;
    PermisController permisController;
    LeconController leconController;
    VehiculeController vehiculeController;
    Utilisateur u ;
    ArrayList<AnchorPane> listeAp;
    @javafx.fxml.FXML
    private BarChart graphMoniteurHeuresEleves;
    @javafx.fxml.FXML
    private ListView lvMoniteurToutPermis;
    @javafx.fxml.FXML
    private Text lblMoniteurTempsPasse;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // instancier les variable / services ici
        utilisateurController = new UtilisateurController();
        permisController = new PermisController();
        leconController = new LeconController();
        vehiculeController = new VehiculeController();

        listeAp = new ArrayList<>();
        listeAp.add(apEleve);
        listeAp.add(apMoniteur);

        try {
            u = utilisateurController.findByCode(Session.getCodeEleveActif());
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

                majLabelEleveHeuresByPermis(permisController.getIdPermisByLibelle(lvEleveToutPermis.getSelectionModel().getSelectedItem().toString()));

                /** initialisation du listView liste des véhicules utilisés au lancement de la session **/

                /**
                for (String marques : vehiculeController.getVehiculesByPermis(Session.getCodeEleveActif(), numeroPermis).keySet())
                {
                    tvEleveVehicule.setItems(FXCollections.observableList(vehiculeController.getVehiculesByPermis(Session.getCodeEleveActif(), numeroPermis)));
                }
                 **/


                /** initialisation du graphique des heures par véhicules au lancement de la session **/
                majGraphique1(Session.getCodeEleveActif(), permisController.getIdPermisByLibelle(lvEleveToutPermis.getSelectionModel().getSelectedItem().toString()));


                /** initialisation du graphique des heures par moniteurs au lancement de la session **/

                majGraphique2(permisController.getIdPermisByLibelle(lvEleveToutPermis.getSelectionModel().getSelectedItem().toString()));


            }
            else if (u.getStatut().getId() == 2)
            {
                Session.changeAp(listeAp, apMoniteur);
                /**
                 *
                 * remplir la vue Permis eleve ici
                 *
                 */

                /** initialisation de la  listeView des licences du moniteur **/

                lvMoniteurToutPermis.setItems(FXCollections.observableList(permisController.getLicence(Session.getCodeEleveActif())));

                lvMoniteurToutPermis.getSelectionModel().selectFirst();


                /** initialisation du label des heuresTotales du moniteur au lancement de la session **/

                majLabelMoniteurHeuresByPermis(permisController.getIdPermisByLibelle(lvMoniteurToutPermis.getSelectionModel().getSelectedItem().toString()));

                /** initialisation du graphique des heures/lecon par élèves par permis au lancement de la session **/

                graphiqueMoniteur();

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
        // si c'est un moniteur
            // on demande l'autorisation a l'admin (la date est a 01-01-2001 01:01 (sera afficher sur la page admin tt les licence avec cette date d'obtention pourquoi pas)
        // si c'est un élève
            // on ajoute sa licence avec un date d'obtention a null
    }

    @javafx.fxml.FXML
    public void changeToApPermis(ActionEvent actionEvent) throws IOException {
        Session.changerScene("permis.fxml", "Le Rebours Auto - Permis", actionEvent);
    }

    /**--------------- PARTIE ELEVE -------------------**/

    @javafx.fxml.FXML
    public void onLvPermisEleve(Event event) throws SQLException {

        String libellePermis = lvEleveToutPermis.getSelectionModel().getSelectedItem().toString();

        int numeroPermis = permisController.getIdPermisByLibelle(libellePermis);

        majGraphique1(Session.getCodeEleveActif(), numeroPermis);

        majLabelEleveHeuresByPermis(numeroPermis);

        majGraphique2(numeroPermis);

    }

    public void majLabelEleveHeuresByPermis(int numPermisEleve) throws SQLException {

        /** mise à jour du label nombreHeuresTotal**/

        int totalHorraire = leconController.getTotalHeuresEleveByPermis(Session.getCodeEleveActif(), numPermisEleve);

        lblEleveTempsPermis.setText(String.valueOf(totalHorraire)+ " heures");
        /** ---------------------------------------------------------------------------- **/

    }

    public void majGraphique1(int idUser, int idPermis) throws SQLException {
        /** mise a jour du graphique des heures par véhicules **/

        graphEleveHeuresVehicules.getData().clear();

        XYChart.Series<Object, Object> serieGraph = new XYChart.Series<>();
        serieGraph.setName("Modèles");
        for (String valeur : leconController.getDataGraphiquePermisVehicules(idUser, idPermis).keySet())
        {
            serieGraph.getData().add(new XYChart.Data<>(valeur,leconController.getDataGraphiquePermisVehicules(idUser, idPermis).get(valeur)));
        }

        graphEleveHeuresVehicules.getData().add(serieGraph);

    }

    public void majGraphique2(int numPermisEleve) throws SQLException {
        /** mise a jour du graphique des heures par moniteurs **/

        // initialisation des colonnes ELeve

        serieGraph1 = new XYChart.Series();
        graphEleveHeuresMoniteurs.getData().clear();

        HashMap<String, Integer> datas = new HashMap<>();
        datas = leconController.getHeuresByMoniteurs(Session.getCodeEleveActif(), numPermisEleve);
        // Remplir la série nécessaire au graphique à partir des données provenant de la HashMap
        for (String nomMoniteurs : datas.keySet())
        {
            serieGraph1.getData().add(new XYChart.Data(nomMoniteurs, datas.get(nomMoniteurs)));
        }

        graphEleveHeuresMoniteurs.getData().add(serieGraph1);

        for (XYChart.Data<String,Number> entry : serieGraph1.getData()) {
            Tooltip t = new Tooltip(entry.getYValue().toString()+ " : "+entry.getXValue().toString());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }
    }

    /**--------------- FIN PARTIE ELEVE -------------------**/

    /**---------------  PARTIE MONITEUR -------------------**/

    @javafx.fxml.FXML
    public void onLvPermisMoniteurClicked(Event event) throws SQLException {

        String libellePermisMoniteur = lvMoniteurToutPermis.getSelectionModel().getSelectedItem().toString();

        int numeroPermisMoniteur = permisController.getIdPermisByLibelle(libellePermisMoniteur);

        majLabelMoniteurHeuresByPermis(numeroPermisMoniteur);

    }

    public void majLabelMoniteurHeuresByPermis(int numPermisMoniteur) throws SQLException {

        /** mise à jour du label nombreHeuresTotal**/

        int totalHorraireMoniteur = leconController.getTotalHeuresMoniteurByPermis(Session.getCodeEleveActif(), numPermisMoniteur);

        lblMoniteurTempsPasse.setText(String.valueOf(totalHorraireMoniteur)+ " heures");
        /** ---------------------------------------------------------------------------- **/

    }

    public void graphiqueMoniteur() throws SQLException
    {
        graphMoniteurHeuresEleves.getData().clear();

        HashMap<String, ArrayList<String>> datasGraphiqueMoniteur =  leconController.graphLeconElevesByPermis(Session.getCodeEleveActif());

        for (String valeur : datasGraphiqueMoniteur.keySet())
        {
            XYChart.Series<Object, Object> serieGraphMoniteur = new XYChart.Series<>();
            serieGraphMoniteur.setName(valeur);
            for(int i = 0;i< datasGraphiqueMoniteur.get(valeur).size();i+=2)
            {
                serieGraphMoniteur.getData().add(new XYChart.Data<>(datasGraphiqueMoniteur.get(valeur).get(i),Integer.parseInt(datasGraphiqueMoniteur.get(valeur).get(i+1))));
            }
            graphMoniteurHeuresEleves.getData().add(serieGraphMoniteur);
            //serieGraph3 = new XYChart.Series<>();
        }

    }

    /**--------------- FIN PARTIE MONITEUR -------------------**/


}
