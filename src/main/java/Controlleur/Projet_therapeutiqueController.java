/**
 * Sample Skeleton for 'Projettherapeutique.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Projet_therapeutiqueController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="enregistrer"
    private Button enregistrer; // Value injected by FXMLLoader

    @FXML // fx:id="projet_thérapeutique"
    private TextArea projet_thérapeutique; // Value injected by FXMLLoader

    @FXML // fx:id="retour"
    private Button retour; // Value injected by FXMLLoader

    @FXML // fx:id="username1"
    private Label username1; // Value injected by FXMLLoader

    private Dossier dossier;
    private BO bilan;

    @FXML
    void enregistrer(ActionEvent event) {
        String texte = projet_thérapeutique.getText().trim();

        if (texte.isEmpty()) {
            // Show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le projet thérapeutique ne peut pas être vide.");
            alert.showAndWait();
        } else {
            Projet_therapeu projetTherapeu =new Projet_therapeu(texte);
            this.bilan.setProjet(projetTherapeu);
             OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(this.dossier.getNumero()).getBilans_orth().add(bilan);
            // Process the text (e.g., save it to the dossier or bilan)
            //bilan.setProjetTherapeutique(texte); // Assuming you have this method in your BO class

            // Proceed to the next steps (e.g., load the next page)
            // Load the next page if needed
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/Dossier.fxml"));
                Parent root = loader.load();
                DossierController dossierctrl = loader.getController();
                dossierctrl.setDossierData(this.dossier.getNumero());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 670);
                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    void handleRouting(MouseEvent event) {


    }

    @FXML
    void profile(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert enregistrer != null : "fx:id=\"enregistrer\" was not injected: check your FXML file 'Projettherapeutique.fxml'.";
        assert projet_thérapeutique != null : "fx:id=\"projet_thérapeutique\" was not injected: check your FXML file 'Projettherapeutique.fxml'.";
        assert retour != null : "fx:id=\"retour\" was not injected: check your FXML file 'Projettherapeutique.fxml'.";
        assert username1 != null : "fx:id=\"username1\" was not injected: check your FXML file 'Projettherapeutique.fxml'.";

    }

    public void setDossier(Dossier dossier) {

        this.dossier = dossier;

    }

    public Dossier getDossier() {
        return dossier;
    }

    public BO getBilan() {
        return bilan;
    }

    public void setBilan(BO bilan) {
        this.bilan = bilan;
    }
}
