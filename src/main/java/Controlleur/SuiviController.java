package Controlleur; /**
 * Sample Skeleton for 'Suivi.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;


import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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
import javafx.scene.control.ComboBox;

public class SuiviController
{


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

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

    @FXML // fx:id="jourrror"
    private Label jourrror; // Value injected by FXMLLoader

    @FXML // fx:id="num_dossier"
    private TextField num_dossier; // Value injected by FXMLLoader

    @FXML // fx:id="numeroerror"
    private Label numeroerror; // Value injected by FXMLLoader

    @FXML // fx:id="profile"
    private Button profile; // Value injected by FXMLLoader

    @FXML // fx:id="retour"
    private Button retour; // Value injected by FXMLLoader

    @FXML // fx:id="type_deroulement"
    private ComboBox<String> type_deroulement;// Value injected by FXMLLoader

    private Label typeeerror;

    @FXML // fx:id="utilisateur"
    private Label utilisateur; // Value injected by FXMLLoader

    @FXML
    private Label typeerror;

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
    boolean chercher_dossier(KeyEvent event)
    {
        numeroerror.setText("");
        String num = num_dossier.getText();
        boolean existe = false;
        int numero=0;
        if (num.isEmpty()) {
            numeroerror.setText("Le champ nom ne doit pas être vide.");

        }else
        {
            try {numero= Integer.parseInt(num);
               Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
               if(dossier != null) {
                   existe = true;
               }else {
                   numeroerror.setText("Le numéro de dossier n'existe pas!.");
               }

            } catch (NumberFormatException e) {
                numeroerror.setText("Le numéro de dossier doit être un nombre valide.");
            }
        }
        return existe;
    }

    @FXML
    void enregistrer(ActionEvent event)
    {
        resetErrorMessages();

        String heureText = heure_consult.getText();
        LocalDate date = jour_consult.getValue();
        String dureeText = duree.getText();
        String num = num_dossier.getText();
        String selectedValue = type_deroulement.getValue();
        Deroulement_seance type;
        // Convert the selected value to the enum type
        System.out.println("Date sélectionnée : " + date);
        Orthophoniste user = OrthophonisteSessionManager.getCurrentOrthophonisteName();

        boolean allFieldsValid = true;

        // Vérifier que le champ nom n'est pas vide
        int numero=0;
        Dossier dossier = null;
        if (num.isEmpty())
        {
            numeroerror.setText("Le champ nom ne doit pas être vide.");
            allFieldsValid = false;

        }else
        {
            try {
                numero= Integer.parseInt(num);
                dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);

                if(dossier == null) {
                    numeroerror.setText("Le numéro de dossier n'existe pas!.");
                    allFieldsValid = false;

                }

            } catch (NumberFormatException e) {
                numeroerror.setText("Le numéro de dossier doit être un nombre valide.");
                allFieldsValid = false;
            }

        }

        // Vérifier que la date est fournie
        if (date == null) {
            jourrror.setText("La date ne doit pas être vide.");
            allFieldsValid = false;
        }else {
            if (date.isBefore(LocalDate.now())) {
                // La date est antérieure à aujourd'hui, afficher un message d'erreur
                jourrror.setText("La date est antérieure à aujourd'hui.");
                allFieldsValid = false;
            }

        }


        // Vérifier que l'heure de consultation est fournie
        if (heureText.isEmpty()) {
            heureerror.setText("L'heure  ne doit pas être vide.");
            allFieldsValid = false;
        }else {
            try {
                LocalTime.parse(heureText); // Essayer de parser l'heure
                if (user.getAgenda().existe(date, LocalTime.parse(heureText), LocalTime.parse(dureeText))) {
                    heureerror.setText("Vous avez déja un rendez-vous dans cet heure");
                    allFieldsValid =false;
                }

            } catch (Exception e) {
                heureerror.setText("Veuillez entrer une heure valide (HH:mm)");
                allFieldsValid = false;
            }
        }

        if (selectedValue == null || !(selectedValue.equals("en ligne") || selectedValue.equals("en présentiel"))) {
            typeerror.setText("Choisissez le type de la séance");
            allFieldsValid = false;}

        if (allFieldsValid) {

            if (selectedValue.equals("en ligne") )
            {
                type = Deroulement_seance.EN_LIGNE;
            }else
            {
                 type = Deroulement_seance.EN_LIGNE;
            }
            System.out.println(dureeText);
            dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
            Suivi suivi = new Suivi(date, LocalTime.parse(heureText), Type_rendez_vous.SUIVI, numero, type, dureeText);
            user.add_rendez_vous_patient(dossier.getNumero(),suivi);
            //user.getAgenda().add_rendez_vous(suivi);
            String PageRouter = "/com/example/tp_poo/Agenda.fxml";
            try {

                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
                Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

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
        String nom1 =OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getNom();
        System.out.println(nom1);
        String prenom1 =OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getPrenom();

        utilisateur.setText(nom1 + " " + prenom1);
        final ObservableList<String> strings = FXCollections.observableArrayList();
        strings.add("en ligne");
        strings.add("en présentiel");
        type_deroulement.getItems().addAll(strings);
        // Set the items for the ComboBox
        duree.setText("01:00");


    }
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void resetErrorMessages() {
        jourrror.setText("");
        numeroerror.setText("");
        heureerror.setText("");
        dureerror.setText("");
        typeerror.setText("");
    }
    private void afficherMessageSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
