/**
 * Sample Skeleton for 'Bilan.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class BilanController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="creer_bilon"
    private Button creer_bilon; // Value injected by FXMLLoader

    @FXML // fx:id="patient_select"
    private ComboBox<String> patient_select; // Value injected by FXMLLoader

    @FXML // fx:id="username1"
    private Label username1; // Value injected by FXMLLoader

    @FXML // fx:id="patienterror"
    private Label patienterror; // Value injected by FXMLLoader
    Map<String, Integer> dossierNumberMap;
    private Dossier dossier;
    private BO bilan;
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
                Orthophoniste user= OrthophonisteSessionManager.getCurrentOrthophonisteName();
                String username =user.getCompte().getEmail();
                String filepath="./src/main/Userinformation/" + username + ".ser";
                Orthophoniste.serialize(filepath,user);
                newPage = true;
                PageRouter = "/com/example/tp_poo/Login.fxml";
                break;

            default:
                newPage = true;
                PageRouter = "/com/example/tp_poo/DefaultPage.fxml";
                break;
        }

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
    void profile(ActionEvent event) {

    }
    @FXML
    void creer_bilan(ActionEvent event) {

        String selectedItem = patient_select.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        boolean allFieldsValid = true;
        int num_dossier=0;

        if (selectedItem != null && this.dossierNumberMap.containsKey(selectedItem)) {
             num_dossier = dossierNumberMap.get(selectedItem);
        }else {
            patienterror.setText("Veuillez selectionner un patient");
            allFieldsValid =false;
        }
        if (allFieldsValid) {
            Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(num_dossier);
            if (dossier.getBilans_orth() != null) {

                int size_bilan = dossier.getBilans_orth().size();
                String PageRouter = "";

                if (size_bilan == 0) {

                    this.bilan = new BO_1();

                    if (dossier.getPatient() instanceof Adulte) {
                        PageRouter = "/com/example/tp_poo/ajouter_adulte_anam.fxml";
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(PageRouter));

                        try {
                            Parent root = loader.load();
                            ajouter_question_adulteController controller = loader.getController();
                            controller.setBilan(bilan);
                            controller.setDossier(dossier);

                            Scene scene = new Scene(root, 1000, 670);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {

                        PageRouter = "/com/example/tp_poo/ajouter_enfant_anam.fxml";
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(PageRouter));

                        try {
                            Parent root = loader.load();
                            ajouter_question_enfantController controller = loader.getController();
                            controller.setBilan(bilan);
                            controller.setDossier(dossier);

                            Scene scene = new Scene(root, 1000, 670);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    this.bilan = new BO();
                    PageRouter = "/com/example/tp_poo/troublebilan.fxml";
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(PageRouter));

                    try {
                        Parent root = loader.load();
                        troublebilanController controller = loader.getController();
                        controller.setBilan(bilan);
                        controller.setDossier(dossier);

                        Scene scene = new Scene(root, 1000, 670);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        }


    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        String nom = OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getNom();
        System.out.println(nom);
        String prenom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getPrenom();

        username1.setText(nom + " " + prenom);

        TreeMap<Integer, Dossier> dossiers =  OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_dossiers();
        Map<String, Integer> dossierNumberMap = new HashMap<>();


        final ObservableList<String> strings = FXCollections.observableArrayList();

        for (Dossier dossier : dossiers.values()) {
            String patient =dossier.getPatient().getNom() +" "+dossier.getPatient().getPrenom()+" Numéro de dossier: "+String.valueOf(dossier.getNumero());
            strings.add(patient);
            dossierNumberMap.put(patient, dossier.getNumero());
        }

        this.dossierNumberMap =dossierNumberMap;


        // Add all items from the observable list to the ComboBox
        patient_select.getItems().addAll(strings);
        assert creer_bilon != null : "fx:id=\"creer_bilon\" was not injected: check your FXML file 'Bilan.fxml'.";
        assert patient_select != null : "fx:id=\"patient_select\" was not injected: check your FXML file 'Bilan.fxml'.";
        assert username1 != null : "fx:id=\"username1\" was not injected: check your FXML file 'Bilan.fxml'.";

    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public BO getBilan() {
        return bilan;
    }

    public void setBilan(BO bilan) {
        this.bilan = bilan;
    }
}
