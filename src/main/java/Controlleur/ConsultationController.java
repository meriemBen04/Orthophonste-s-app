/**
 * Sample Skeleton for 'Consultation.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;


public class ConsultationController {


    @FXML
    private TextField Age;

    @FXML
    private Label ageerror;

    @FXML
    private Label utilisateur1;

    @FXML
    private TextField duree;

    @FXML
    private Label dureerror;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField heure_consult;

    @FXML
    private Label houreerror11;

    @FXML
    private DatePicker jour_consult;

    @FXML
    private Label nameerror;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private Label prenomerror;

    @FXML
    private Button profile;
    @FXML
    private Label jourrror;
    @FXML
    private Button retour;
    @FXML

    void calcule_durée(KeyEvent event)
    {

        ageerror.setText("");

        String age = Age.getText();
        try {
            int a  = Integer.parseInt(age);
            if(a>=18){
                duree.setText("01:30");

            }else {
                duree.setText("02:30");
            }

        } catch (NumberFormatException e) {
            ageerror.setText("L'âge doit être un nombre valide.");
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
    @FXML
    public void enregistrer(ActionEvent event)
    {
        resetErrorMessages();

        // Récupérer les valeurs des champs
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String ageText = Age.getText();
        String heureText = heure_consult.getText();
        LocalDate date = jour_consult.getValue();
        String dureeText = duree.getText();
        System.out.println("Date sélectionnée : " + date);
        Orthophoniste user =OrthophonisteSessionManager.getCurrentOrthophonisteName();


        // Variable pour vérifier si toutes les vérifications passent
        boolean allFieldsValid = true;

        // Vérifier que le champ nom n'est pas vide
        if (Nom.isEmpty()) {
            nameerror.setText("Le champ nom ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que le champ prénom n'est pas vide
        if (Prenom.isEmpty()) {
            prenomerror.setText("Le champ prénom ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que l'âge est fourni et est un entier valide
        int age = 0;
        if (ageText.isEmpty()) {
            ageerror.setText("Le champ âge ne doit pas être vide.");
            allFieldsValid = false;
        } else
        {
            try
            {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                ageerror.setText("L'âge doit être un nombre valide.");
                allFieldsValid = false;
            }
        }

        // Vérifier que la date de consultation est fournie
        if (date == null) {
            jourrror.setText("La date de consultation ne doit pas être vide.");
            allFieldsValid = false;
        }else {
            if (date.isBefore(LocalDate.now())) {
                // La date est antérieure à aujourd'hui, afficher un message d'erreur
                jourrror.setText("La date est antérieure à aujourd'hui.");
                allFieldsValid = false;
            }
        }


        if (dureeText.isEmpty()) {
            dureerror.setText("La durée de consultation ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que l'heure de consultation est fournie
        if (heureText.isEmpty()) {
            houreerror11.setText("L'heure de consultation ne doit pas être vide.");
            allFieldsValid = false;
        }else {
            try {
                LocalTime.parse(heureText); // Essayer de parser l'heure
                if (user.getAgenda().existe(date, LocalTime.parse(heureText), LocalTime.parse(dureeText))) {
                    houreerror11.setText("Vous avez déja un rendez-vous dans cet heure");
                    allFieldsValid =false;
                }

            } catch (Exception e) {
                houreerror11.setText("Veuillez entrer une heure valide (HH:mm)");
                allFieldsValid = false;
            }
        }

        // Si toutes les vérifications passent, procéder à la création du dossier

        if (allFieldsValid) {
            Dossier dossier = new Dossier();
            int num = dossier.getNumero();
            Patient patient;
            if (age >= 18) {
                patient = new Adulte(Nom, Prenom,num);
            } else {
                patient = new Enfant(Nom, Prenom,num);
            }
            dossier.setPatient(patient);
            LocalTime time =LocalTime.parse(heureText);
            Consultation consultation =new Consultation(date,time,Type_rendez_vous.CONSULTATION,Nom,Prenom,age,dureeText);

            dossier.add_rendez_vous(consultation);
            OrthophonisteSessionManager.getCurrentOrthophonisteName().add_patient(dossier);

            user.getAgenda().add_rendez_vous(consultation);
            afficherMessageSucces("La consultation est ajouter avec succés");



            try {

                // Load the desired page
                String PageRouter ="/com/example/tp_poo/Agenda.fxml";
                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));

                // You need to set the new page in the current scene or open a new window
                // Example for setting the new page in the current scene:
                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void resetErrorMessages() {
        nameerror.setText("");
        prenomerror.setText("");
        ageerror.setText("");
        dureerror.setText("");
        houreerror11.setText("");
        jourrror.setText("");
    }
    private void afficherMessageSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleRouting(MouseEvent event)
    {

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

        if (newPage)
        {
            try {
                // Load the desired page
                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
                // You need to set the new page in the current scene or open a new window
                // Example for setting the new page in the current scene:
                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void retour(ActionEvent event)
    {
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

        assert Age != null : "fx:id=\"Age\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert duree != null : "fx:id=\"duree\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert enregistrer != null : "fx:id=\"enregistrer\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert heure_consult != null : "fx:id=\"heure_consult\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert jour_consult != null : "fx:id=\"jour_consult\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Consultation.fxml'.";
        assert retour != null : "fx:id=\"retour\" was not injected: check your FXML file 'Consultation.fxml'.";

    }

}
