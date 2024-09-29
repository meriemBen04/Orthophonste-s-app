/**
 * Sample Skeleton for 'atelier.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

public class AtelierController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="checcombox"
    private CheckComboBox<String> checcombox; // Value injected by FXMLLoader

    @FXML // fx:id="duree"
    private TextField duree; // Value injected by FXMLLoader

    @FXML // fx:id="dureerror"
    private Label dureerror; // Value injected by FXMLLoader

    @FXML // fx:id="enregistrer"
    private Button enregistrer; // Value injected by FXMLLoader

    @FXML // fx:id="heure_consult"
    private TextField heure_consult; // Value injected by FXMLLoader

    @FXML // fx:id="heureerror"
    private Label heureerror; // Value injected by FXMLLoader

    @FXML // fx:id="jour_consult"
    private DatePicker jour_consult; // Value injected by FXMLLoader

    @FXML // fx:id="jourerror"
    private Label jourerror; // Value injected by FXMLLoader

    @FXML // fx:id="numeroerror"
    private Label numeroerror; // Value injected by FXMLLoader

    @FXML // fx:id="profile"
    private Button profile; // Value injected by FXMLLoader

    @FXML // fx:id="retour"
    private Button retour; // Value injected by FXMLLoader

    @FXML // fx:id="thematiqueerror"
    private Label thematiqueerror; // Value injected by FXMLLoader

    @FXML // fx:id="theme"
    private TextField theme; // Value injected by FXMLLoader

    @FXML // fx:id="utilisateur1"
    private Label utilisateur1; // Value injected by FXMLLoader
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
    public void enregistrer(ActionEvent event) {

        resetErrorMessages();

        String heureText = heure_consult.getText();
        LocalDate date = jour_consult.getValue();
        String dureeText = duree.getText();
        String thematique = theme.getText();

        System.out.println("Date sélectionnée : " + date);
        Orthophoniste user = OrthophonisteSessionManager.getCurrentOrthophonisteName();

        boolean allFieldsValid = true;

        // Vérifier que le champ nom n'est pas vide


        // Vérifier que la date est fournie
        if (date == null) {
            jourerror.setText("La date ne doit pas être vide.");
            allFieldsValid = false;
        }else
        {

            if (date.isBefore(LocalDate.now())) {
                // La date est antérieure à aujourd'hui, afficher un message d'erreur
                jourerror.setText("La date est antérieure à aujourd'hui.");
                allFieldsValid = false;
            }
        }

        // Vérifier que l'heure de consultation est fournie
        if (heureText.isEmpty()) {
            heureerror.setText("L'heure  ne doit pas être vide.");
            allFieldsValid = false;
        }else {
            try {
                LocalTime.parse(heureText);
                if (user.getAgenda().existe(date, LocalTime.parse(heureText), LocalTime.parse(dureeText))) {
                    heureerror.setText("Vous avez déja un rendez-vous dans cet heure");
                    allFieldsValid=false;
                }// Essayer de parser l'heure

            } catch (Exception e) {
                heureerror.setText("Veuillez entrer une heure valide (HH:mm)");
                allFieldsValid = false;
            }
        }
        if (thematique.isEmpty()) {
            thematiqueerror.setText("La thématique ne doit pas être vide.");
            allFieldsValid = false;
        }
        ObservableList<String> selectedItems = checcombox.getCheckModel().getCheckedItems();
        if(selectedItems.isEmpty()) {
            // Aucun élément sélectionné, affiche un message d'erreur
            numeroerror.setText("Veuillez choisir des numéros de dossiers");
            allFieldsValid = false;
            System.out.println(selectedItems);
        }

        if (allFieldsValid) {

            // Initialize a list to hold the Dossier objects
            List<Dossier> dossiersList = new ArrayList<>();

            // Convert the selected items to integers and retrieve corresponding Dossier objects
            for (String selectedItem : selectedItems) {
                try {
                    int dossierNumber = Integer.parseInt(selectedItem);
                    Dossier dossier = user.rechercher_patient(dossierNumber);
                    if (dossier != null) {
                        dossiersList.add(dossier);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format: " + selectedItem);
                }
            }

            // Convert the list to an array
            Dossier[] dossiers = dossiersList.toArray(new Dossier[0]);
            LocalTime time = LocalTime.parse(heureText);

            Atelier atelier = new Atelier(date, time, Type_rendez_vous.ATELIER, thematique, dossiers, dureeText);

            user.getAgenda().add_rendez_vous(atelier);

            for (Dossier dossier : dossiers) {

                user.add_rendez_vous_patient(dossier.getNumero(), atelier);

            }

            String pageRouter = "/com/example/tp_poo/Agenda.fxml";
            try {
                Parent nextPage = FXMLLoader.load(getClass().getResource(pageRouter));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    void retour(ActionEvent event) {

        try {
            String PageRouter = "/com/example/tp_poo/Agenda.fxml";
            // Load the desired page
            Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        String nom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getNom();
        System.out.println(nom);
        String prenom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getPrenom();

        utilisateur1.setText(nom + " " + prenom);

        TreeMap<Integer,Dossier>  dossiers =  OrthophonisteSessionManager.getCurrentOrthophonisteName().getMes_dossiers();

        final ObservableList<String> strings = FXCollections.observableArrayList();
        Set<Integer> keys = dossiers.keySet();
        for (Integer key : keys) {
            strings.add(key.toString());
        }

        // Add all items from the observable list to the ComboBox
        checcombox.getItems().addAll(strings);

       duree.setText("01:00");

        assert checcombox != null : "fx:id=\"checcombox\" was not injected: check your FXML file 'atelier.fxml'.";
        assert duree != null : "fx:id=\"duree\" was not injected: check your FXML file 'atelier.fxml'.";
        assert dureerror != null : "fx:id=\"dureerror\" was not injected: check your FXML file 'atelier.fxml'.";
        assert enregistrer != null : "fx:id=\"enregistrer\" was not injected: check your FXML file 'atelier.fxml'.";
        assert heure_consult != null : "fx:id=\"heure_consult\" was not injected: check your FXML file 'atelier.fxml'.";
        assert heureerror != null : "fx:id=\"heureerror\" was not injected: check your FXML file 'atelier.fxml'.";
        assert jour_consult != null : "fx:id=\"jour_consult\" was not injected: check your FXML file 'atelier.fxml'.";
        assert jourerror != null : "fx:id=\"jourerror\" was not injected: check your FXML file 'atelier.fxml'.";
        assert numeroerror != null : "fx:id=\"numeroerror\" was not injected: check your FXML file 'atelier.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'atelier.fxml'.";
        assert retour != null : "fx:id=\"retour\" was not injected: check your FXML file 'atelier.fxml'.";
        assert thematiqueerror != null : "fx:id=\"thematiqueerror\" was not injected: check your FXML file 'atelier.fxml'.";
        assert theme != null : "fx:id=\"theme\" was not injected: check your FXML file 'atelier.fxml'.";
        assert utilisateur1 != null : "fx:id=\"utilisateur1\" was not injected: check your FXML file 'atelier.fxml'.";

    }
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void resetErrorMessages() {
        jourerror.setText("");
        numeroerror.setText("");
        heureerror.setText("");
        dureerror.setText("");
        thematiqueerror.setText("");
    }
    private void afficherMessageSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
