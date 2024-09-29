/**
 * Sample Skeleton for 'Profile.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Model.Deroulement_seance;
import Model.Dossier;
import Model.Orthophoniste;
import Model.OrthophonisteSessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;

public class ProfileController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adresse"
    private CustomTextField adresse; // Value injected by FXMLLoader

    @FXML // fx:id="adresseerror"
    private Label adresseerror; // Value injected by FXMLLoader

    @FXML // fx:id="current_pasword"
    private CustomTextField current_pasword; // Value injected by FXMLLoader

    @FXML // fx:id="enregitrer"
    private Button enregitrer; // Value injected by FXMLLoader

    @FXML // fx:id="new_paword"
    private CustomTextField new_paword; // Value injected by FXMLLoader

    @FXML // fx:id="newpass_error"
    private Label newpass_error; // Value injected by FXMLLoader

    @FXML // fx:id="nom"
    private CustomTextField nom; // Value injected by FXMLLoader

    @FXML // fx:id="nomerror"
    private Label nomerror; // Value injected by FXMLLoader

    @FXML // fx:id="num_tel"
    private CustomTextField num_tel; // Value injected by FXMLLoader

    @FXML // fx:id="numerror"
    private Label numerror; // Value injected by FXMLLoader

    @FXML // fx:id="pass_error"
    private Label pass_error; // Value injected by FXMLLoader

    @FXML // fx:id="prenom"
    private CustomTextField prenom; // Value injected by FXMLLoader

    @FXML // fx:id="prenomerror"
    private Label prenomerror; // Value injected by FXMLLoader

    @FXML // fx:id="profile"
    private Button profile; // Value injected by FXMLLoader

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
    void enregistrer(ActionEvent event) {
        resetErrorMessages();

        String name = nom.getText();
        String fullname = prenom.getText();
        String num = num_tel.getText();
        String adress = adresse.getText();
        String password = current_pasword.getText();
        String newpas = new_paword.getText();

        Orthophoniste user = OrthophonisteSessionManager.getCurrentOrthophonisteName();
        String mot_de_passe = user.getCompte().getMot_pass();

        boolean allFieldsValid = true;
        int numero =0;

        if (num.isEmpty())
        {
            numerror.setText("Le champ de numéro de téléphone  ne doit pas être vide.");

            allFieldsValid = false;

        }else
        {
            try {
                numero = Integer.parseInt(num);
                if(numero == 0){
                    numerror.setText("Numéro non valide !");
                }

            } catch (NumberFormatException e) {
                numerror.setText("Le numéro doit être un nombre valide.");
                allFieldsValid = false;
            }

            }
        if (name.isEmpty()) {
            nomerror.setText("Le nom  ne doit pas être vide.");
            allFieldsValid = false;
        }
        if (fullname.isEmpty()) {
            prenomerror.setText("Le prénom  ne doit pas être vide.");
            allFieldsValid = false;
        }
        if (adress.isEmpty()) {
            adresseerror.setText("L'adresse  ne doit pas être vide.");
            allFieldsValid = false;
        }
        if (password.isEmpty()&& !newpas.isEmpty()) {
            pass_error.setText("Le mot de passe  ne doit pas être vide.");
            allFieldsValid = false;
        }
        if (!password.isEmpty()&& newpas.isEmpty()) {
            newpass_error.setText("Le nouveau mot de passe  ne doit pas être vide.");
            allFieldsValid = false;
        }
        if (!password.isEmpty() && !password.equals(mot_de_passe)){
            pass_error.setText("Le mot de passe  est incorrect! veuillez essayer une autre fois .");
            allFieldsValid = false;
        }
        if (allFieldsValid) {
            OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().setNom(name);
            OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().setPrenom(fullname);
            OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().setAdresse(adress);
            if(numero!=0){
            OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().setNum_tlf(numero);
            }
            if(!newpas.isEmpty())
            OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().setMot_pass(newpas);
            String PageRouter = "/com/example/tp_poo/Profile.fxml";
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


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        Orthophoniste user = OrthophonisteSessionManager.getCurrentOrthophonisteName();
        adresse.setText(user.getCompte().getAdresse());
        nom.setText(user.getCompte().getNom());
        prenom.setText(user.getCompte().getPrenom());
        num_tel.setText(String.valueOf(user.getCompte().getNum_tlf()));

        String nom = OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getNom();
        System.out.println(nom);
        String prenom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getPrenom();

        utilisateur1.setText(nom + " " + prenom);

        assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'Profile.fxml'.";
        assert adresseerror != null : "fx:id=\"adresseerror\" was not injected: check your FXML file 'Profile.fxml'.";
        assert current_pasword != null : "fx:id=\"current_pasword\" was not injected: check your FXML file 'Profile.fxml'.";
        assert enregitrer != null : "fx:id=\"enregitrer\" was not injected: check your FXML file 'Profile.fxml'.";
        assert new_paword != null : "fx:id=\"new_paword\" was not injected: check your FXML file 'Profile.fxml'.";
        assert newpass_error != null : "fx:id=\"newpass_error\" was not injected: check your FXML file 'Profile.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'Profile.fxml'.";
        assert nomerror != null : "fx:id=\"nomerror\" was not injected: check your FXML file 'Profile.fxml'.";
        assert num_tel != null : "fx:id=\"num_tel\" was not injected: check your FXML file 'Profile.fxml'.";
        assert numerror != null : "fx:id=\"numerror\" was not injected: check your FXML file 'Profile.fxml'.";
        assert pass_error != null : "fx:id=\"pass_error\" was not injected: check your FXML file 'Profile.fxml'.";
        assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'Profile.fxml'.";
        assert prenomerror != null : "fx:id=\"prenomerror\" was not injected: check your FXML file 'Profile.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Profile.fxml'.";
        assert utilisateur1 != null : "fx:id=\"utilisateur1\" was not injected: check your FXML file 'Profile.fxml'.";

    }
    private void resetErrorMessages() {

        numerror.setText("");
        nomerror.setText("");
        prenomerror.setText("");
        adresseerror.setText("");
        pass_error.setText("");
        newpass_error.setText("");
    }
}
