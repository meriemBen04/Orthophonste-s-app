/**
 * Sample Skeleton for 'info_enfant.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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

public class info_enfantController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adreseerror"
    private Label adreseerror; // Value injected by FXMLLoader

    @FXML // fx:id="adresse"
    private TextField adresse; // Value injected by FXMLLoader

    @FXML // fx:id="classe"
    private TextField classe; // Value injected by FXMLLoader

    @FXML // fx:id="classeerror"
    private Label classeerror; // Value injected by FXMLLoader

    @FXML // fx:id="date_naiss"
    private DatePicker date_naiss; // Value injected by FXMLLoader

    @FXML // fx:id="dateerror"
    private Label dateerror; // Value injected by FXMLLoader

    @FXML // fx:id="enregistrer"
    private Button enregistrer; // Value injected by FXMLLoader

    @FXML // fx:id="lieu"
    private TextField lieu; // Value injected by FXMLLoader

    @FXML // fx:id="lieuerror"
    private Label lieuerror; // Value injected by FXMLLoader

    @FXML // fx:id="nom"
    private TextField nom; // Value injected by FXMLLoader

    @FXML // fx:id="num_mere"
    private TextField num_mere; // Value injected by FXMLLoader

    @FXML // fx:id="num_mereerror"
    private Label num_mereerror; // Value injected by FXMLLoader

    @FXML // fx:id="num_pere"
    private TextField num_pere; // Value injected by FXMLLoader

    @FXML // fx:id="num_pereerror"
    private Label num_pereerror; // Value injected by FXMLLoader

    @FXML // fx:id="prenom"
    private TextField prenom; // Value injected by FXMLLoader

    @FXML // fx:id="profile"
    private Button profile; // Value injected by FXMLLoader

    @FXML // fx:id="retour"
    private Button retour; // Value injected by FXMLLoader

    @FXML // fx:id="utilisateur1"
    private Label utilisateur1; // Value injected by FXMLLoader

    private Dossier mon_dosier;

    @FXML
    void enregistrer(ActionEvent event) {

        resetErrorMessages();

        // Récupérer les valeurs des champs
        String loc = lieu.getText();
        String clas = classe.getText();
        LocalDate date =date_naiss.getValue();
        String num_mer = num_mere.getText();
        String num = num_pere.getText();
        String adress = adresse.getText();

        System.out.println("Date sélectionnée : " + date);


        // Variable pour vérifier si toutes les vérifications passent
        boolean allFieldsValid = true;

        if (adress.isEmpty()) {
            adreseerror.setText("Le champ adresse ne doit pas être vide.");
            allFieldsValid = false;
        }

        if (loc.isEmpty()) {
            lieuerror.setText("Le champ lieu ne doit pas être vide.");
            allFieldsValid = false;
        }

        int numero = 0;
        int numero2 = 0;
        if (num.isEmpty()) {
           num_pereerror.setText("Le champ téléphone ne doit pas être vide.");
            allFieldsValid = false;
        } else
        {
            try
            {
                numero = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                num_pereerror.setText("Le numéro  de téléphone doit être un nombre valide.");
                allFieldsValid = false;
            }
        }
        if (num_mer.isEmpty()) {
            num_mereerror.setText("Le champ téléphone ne doit pas être vide.");
            allFieldsValid = false;
        } else
        {
            try
            {
                numero2 = Integer.parseInt(num_mer);
            } catch (NumberFormatException e) {
                num_mereerror.setText("Le numéro  de téléphone doit être un nombre valide.");
                allFieldsValid = false;
            }
        }

        if (date == null) {
            dateerror.setText("La date de naissance ne doit pas être vide.");
            allFieldsValid = false;
        }

        if (clas.isEmpty()) {
            classeerror.setText("Le champ profession ne doit pas être vide.");
            allFieldsValid = false;
        }



        // Si toutes les vérifications passent

        if (allFieldsValid) {

            Enfant patient = (Enfant) this.mon_dosier.getPatient();
            patient.setAdresse(adress);
            patient.setLieu_naissance(loc);
            patient.setClass_etude(clas);
            int[] numo = new int[]{numero2, numero};
            patient.setNumeroparent(numo);
            patient.setDate_naissance(date);

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
        lieuerror.setText("");
        classeerror.setText("");
        adreseerror.setText("");
        num_mereerror.setText("");
        num_pereerror.setText("");

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
        assert adreseerror != null : "fx:id=\"adreseerror\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert classeerror != null : "fx:id=\"classeerror\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert date_naiss != null : "fx:id=\"date_naiss\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert dateerror != null : "fx:id=\"dateerror\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert enregistrer != null : "fx:id=\"enregistrer\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert lieu != null : "fx:id=\"lieu\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert lieuerror != null : "fx:id=\"lieuerror\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert retour != null : "fx:id=\"retour\" was not injected: check your FXML file 'info_enfant.fxml'.";
        assert utilisateur1 != null : "fx:id=\"utilisateur1\" was not injected: check your FXML file 'info_enfant.fxml'.";

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
        Patient enfant = dossier.getPatient();

        if( enfant instanceof Enfant)
        {
            nom.setText(((Enfant)enfant).getNom());
            prenom.setText(((Enfant)enfant).getPrenom());
            this.mon_dosier = dossier;
            if (enfant.getLieu_naissance() != null) {
                lieu.setText(enfant.getLieu_naissance());
            }

            if (((Enfant) enfant).getClass_etude() != null) {
                classe.setText(((Enfant) enfant).getClass_etude());
            }

            if (enfant.getDate_naissance() != null) {
                date_naiss.setValue(enfant.getDate_naissance());
            }

            int[] numpar = ((Enfant) enfant).getNumeroparent();
            if (numpar != null && numpar.length > 0) {
                if (numpar.length > 0 && numpar[0] != 0) {
                    num_mere.setText(String.valueOf(numpar[0]));
                }
                if (numpar.length > 1 && numpar[1] != 0) {
                    num_pere.setText(String.valueOf(numpar[1]));
                }
            }

            if (enfant.getAdresse() != null) {
                adresse.setText(enfant.getAdresse());
            }

        }



    }
}
