package Controlleur;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Model.Type_objectif.COURT_TERME;

public class FicheController implements Initializable

{

    @FXML
    private Label numfichetot;

    @FXML
    private Label numobject;

    @FXML
    private VBox patientslay;

    @FXML
    private Label username1;

    @FXML
    void profile(ActionEvent event){

        try {
            String PageRouter = "/com/example/tp_poo/Profile.fxml";
            Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
            Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleRouting(MouseEvent event) {

        Label label = (Label) event.getSource();
        String labelText = label.getText();


        String PageRouter = "/com/example/tp_poo/DefaultPage.fxml"; // Chemin par défaut
        boolean newPage = false;

        switch (labelText) {
            case "Patients":
                newPage = true;
                PageRouter = "/com/example/tp_poo/Patients.fxml";
                break;

            case "Agenda":
                newPage = true;
                PageRouter = "/com/example/tp_poo/Agenda.fxml";
                break;

            case "BO":
                newPage = true;
                PageRouter = "/com/example/tp_poo/Bilan.fxml";
                break;

            case "Fiche de suivi":
                newPage = true;
                PageRouter = "/com/example/tp_poo/CreerFichesuivi.fxml";
                break;

            case "Testes":
                newPage = true;
                PageRouter = "/com/example/tp_poo/Testes.fxml";
                break;

            case "Votre profile":
                newPage = true;
                PageRouter = "/com/example/tp_poo/Profile.fxml"; // Chemin vers la page de profil
                break;

            case "Se déconnecter":
                newPage = true;
                PageRouter = "/com/example/tp_poo/Logout.fxml";
                break;

            default:
                newPage = true;
                PageRouter = "/com/example/tp_poo/DefaultPage.fxml";
                break;
        }
        //  PageRouter = "/com/example/tp_poo/Login.fxml";

        if (newPage) {
            try {
                // Load the desired page
                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));

                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Orthophoniste user=OrthophonisteSessionManager.getCurrentOrthophonisteName();
        username1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());
    }
    public void setficheData(Dossier dossier)
    {
        List<Fiche_suivi> ficheSuivis = dossier.getFiches_suivi();
        int numfich1=0;
        int numobj=0;
        if(ficheSuivis!=null) {
            for (int i = 0; i < ficheSuivis.size(); i++) {
               numfich1++;
                for (Objectif objectif : ficheSuivis.get(i).getObjectifs()) {
                    numobj++;
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/example/tp_poo/ficheelement.fxml"));
                    try {
                        BorderPane hBox = fxmlLoader.load();
                        FicheelementController cic = fxmlLoader.getController();
                        cic.setData(ficheSuivis.get(i), objectif,numfich1);
                        patientslay.getChildren().add(hBox);
                        numfichetot.setText(String.valueOf(numfich1));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
        numfichetot.setText(String.valueOf(ficheSuivis.size()));
        numobject.setText(String.valueOf(numobj));


    }


}
