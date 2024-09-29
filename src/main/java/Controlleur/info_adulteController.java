/**
 * Sample Skeleton for 'info_adulte.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import Model.*;
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

public class info_adulteController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adresse"
    private TextField adresse; // Value injected by FXMLLoader

    @FXML // fx:id="adresseerror"
    private Label adresseerror; // Value injected by FXMLLoader

    @FXML // fx:id="date_naiss"
    private DatePicker date_naiss; // Value injected by FXMLLoader

    @FXML // fx:id="dateerror"
    private Label dateerror; // Value injected by FXMLLoader

    @FXML // fx:id="diplome"
    private TextField diplome; // Value injected by FXMLLoader

    @FXML // fx:id="diplomeerror"
    private Label diplomeerror; // Value injected by FXMLLoader

    @FXML // fx:id="enregistrer"
    private Button enregistrer; // Value injected by FXMLLoader

    @FXML // fx:id="lieu"
    private TextField lieu; // Value injected by FXMLLoader

    @FXML // fx:id="lieuerror"
    private Label lieuerror; // Value injected by FXMLLoader

    @FXML // fx:id="nom"
    private TextField nom; // Value injected by FXMLLoader

    @FXML // fx:id="num_tel"
    private TextField num_tel; // Value injected by FXMLLoader

    @FXML // fx:id="prenom"
    private TextField prenom; // Value injected by FXMLLoader

    @FXML // fx:id="profesion"
    private TextField profesion; // Value injected by FXMLLoader

    @FXML // fx:id="profesionerror"
    private Label profesionerror; // Value injected by FXMLLoader

    @FXML // fx:id="profile"
    private Button profile; // Value injected by FXMLLoader

    @FXML // fx:id="retour"
    private Button retour; // Value injected by FXMLLoader

    @FXML // fx:id="telephoneerror"
    private Label telephoneerror; // Value injected by FXMLLoader

    @FXML // fx:id="utilisateur1"
    private Label utilisateur1; // Value injected by FXMLLoader
    private Dossier mon_dosier;

    @FXML
    void enregistrer(ActionEvent event) {

        resetErrorMessages();

        // Récupérer les valeurs des champs
        String loc = lieu.getText();
        String job = profesion.getText();
        LocalDate date =date_naiss.getValue();
        String diplom = diplome.getText();
        String num = num_tel.getText();
        String adress = adresse.getText();

        System.out.println("Date sélectionnée : " + date);


        // Variable pour vérifier si toutes les vérifications passent
        boolean allFieldsValid = true;

        // Vérifier que le champ nom n'est pas vide
        if (adress.isEmpty()) {
            adresseerror.setText("Le champ adresse ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que le champ prénom n'est pas vide
        if (loc.isEmpty()) {
            lieuerror.setText("Le champ lieu ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que l'âge est fourni et est un entier valide
        int numero = 0;
        if (num.isEmpty()) {
            telephoneerror.setText("Le champ téléphone ne doit pas être vide.");
            allFieldsValid = false;
        } else
        {
            try
            {
                numero = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                telephoneerror.setText("Le numéro  de téléphone doit être un nombre valide.");
                allFieldsValid = false;
            }
        }

        // Vérifier que la date de consultation est fournie
        if (date == null) {
            dateerror.setText("La date de naissance ne doit pas être vide.");
            allFieldsValid = false;
        }
        if (job.isEmpty()) {
           profesionerror.setText("Le champ profession ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Vérifier que l'heure de consultation est fournie
        if (diplom.isEmpty()) {
           diplomeerror.setText("Le champ diplome ne doit pas être vide.");
            allFieldsValid = false;
        }

        // Si toutes les vérifications passent, procéder à la création du dossier

        if (allFieldsValid) {

            Adulte patient = (Adulte) this.mon_dosier.getPatient();
            patient.setAdresse(adress);
            patient.setLieu_naissance(loc);
            patient.setProfession(job);
            patient.setDimplome(diplom);
            patient.setDate_naissance(date);
            patient.setNumero_personnel(numero);

            try {
                // Load the desired page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/Dossier.fxml"));
                Parent root = loader.load();
                DossierController dossierctrl = loader.getController();
                dossierctrl.setDossierData(patient.getNum_dossier());
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
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void resetErrorMessages() {
        dateerror.setText("");
        profesionerror.setText("");
        diplomeerror.setText("");
        adresseerror.setText("");
        lieuerror.setText("");
        telephoneerror.setText("");

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
        //  PageRouter = "/com/example/tp_poo/Login.fxml";

        if (newPage) {
            try {
                // Load the desired page
                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
                // You need to set the new page in the current scene or open a new window
                // Example for setting the new page in the current scene:
                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
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
        assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert adresseerror != null : "fx:id=\"adresseerror\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert date_naiss != null : "fx:id=\"date_naiss\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert dateerror != null : "fx:id=\"dateerror\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert diplome != null : "fx:id=\"diplome\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert diplomeerror != null : "fx:id=\"diplomeerror\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert enregistrer != null : "fx:id=\"enregistrer\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert lieu != null : "fx:id=\"lieu\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert lieuerror != null : "fx:id=\"lieuerror\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert num_tel != null : "fx:id=\"num_tel\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert profesion != null : "fx:id=\"profesion\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert profesionerror != null : "fx:id=\"profesionerror\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert retour != null : "fx:id=\"retour\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert telephoneerror != null : "fx:id=\"telephoneerror\" was not injected: check your FXML file 'info_adulte.fxml'.";
        assert utilisateur1 != null : "fx:id=\"utilisateur1\" was not injected: check your FXML file 'info_adulte.fxml'.";

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
    public void setInfoData(Rendez_vous rd, Dossier dossier)
    {
       Patient patient = dossier.getPatient();

        if( patient instanceof Adulte)
        {
            nom.setText(((Adulte)patient).getNom());
            prenom.setText(((Adulte)patient).getPrenom());
            this.mon_dosier = dossier;
            if (patient.getLieu_naissance() != null) {
                lieu.setText(patient.getLieu_naissance());
            }

            if (((Adulte) patient).getProfession() != null) {
                profesion.setText(((Adulte) patient).getProfession());
            }
            if (((Adulte) patient).getDimplome() != null) {
                diplome.setText(((Adulte) patient).getDimplome());
            }

            if (patient.getDate_naissance() != null) {
                date_naiss.setValue(patient.getDate_naissance());
            }

            long numpar = ((Adulte) patient).getNumero_personnel();
            if (numpar > 0) {

                    num_tel.setText(String.valueOf(numpar));
            }

            if (patient.getAdresse() != null) {
                adresse.setText(patient.getAdresse());
            }

        }


    }

}
