/**
 * Sample Skeleton for 'Testes.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Orthophoniste;
import Model.OrthophonisteSessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TesteController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Consultertest"
    private Label Consultertest; // Value injected by FXMLLoader

    @FXML // fx:id="ajoutertest"
    private Label ajoutertest; // Value injected by FXMLLoader

    @FXML // fx:id="username1"
    private Label username1; // Value injected by FXMLLoader
    @FXML
    public void ajoutertest(MouseEvent event)
    {
        try {
           /* AnamneseAdulteController.ajouter = true;
            AnamneseEnfantController.ajouter = true;
            QuestionnaireController.ajouter = true;
            QuestionnaireLibreController.ajouter = true;
            SerieExercicesController.ajouter = true;*/
            String PageRouter = "testtype.fxml";
            Parent nextPage = FXMLLoader.load(getClass().getResource("/com/example/tp_poo/testtype.fxml"));

            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void consultertest(MouseEvent event)
    {
        try {
            /*AnamneseAdulteController.ajouter = false;
            AnamneseEnfantController.ajouter = false;
            QuestionnaireController.ajouter = false;
            QuestionnaireLibreController.ajouter = false;
            SerieExercicesController.ajouter = false;*/
            String PageRouter = "testexist.fxml";
            Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));

            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
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



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Consultertest != null : "fx:id=\"Consultertest\" was not injected: check your FXML file 'Testes.fxml'.";
        assert ajoutertest != null : "fx:id=\"ajoutertest\" was not injected: check your FXML file 'Testes.fxml'.";
        assert username1 != null : "fx:id=\"username1\" was not injected: check your FXML file 'Testes.fxml'.";

    }

       @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Orthophoniste user= OrthophonisteSessionManager.getCurrentOrthophonisteName();

        username1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());

    }
}
