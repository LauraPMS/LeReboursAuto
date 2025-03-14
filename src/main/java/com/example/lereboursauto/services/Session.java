package com.example.lereboursauto.services;
import com.example.lereboursauto.HelloApplication;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;import javafx.scene.image.ImageView;import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Session {
    private static int codeEleveActif;

    public static int getCodeEleveActif()
    {
        return codeEleveActif;
    }

    public static void setCodeEleveActif(int numCompteActif)
    {
        Session.codeEleveActif = numCompteActif;
    }

    public static void changerScene(String strFXML, String titre, Event event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(strFXML));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(titre);
        stage.setScene(scene);
        stage.show();
    }

    public static void creerAlert(Alert.AlertType a, String title, String header){
        Alert alert = new Alert(a);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    public static void changeAp(ArrayList<AnchorPane> arrayAp, AnchorPane ap){
        for(AnchorPane a : arrayAp){
            a.setVisible(false);
        }
        ap.setVisible(true);
    }
}