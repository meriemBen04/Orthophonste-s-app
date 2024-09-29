/**
 * Sample Skeleton for 'Rendez-vous.fxml' Controller Class
 */
package Controlleur;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class AgendaController  {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="agendaligne"
    private VBox agendaligne; // Value injected by FXMLLoader

    @FXML // fx:id="editprofile"
    private Button editprofile; // Value injected by FXMLLoader

    @FXML // fx:id="utilisateur1"
    private Label utilisateur1; // Value injected by FXMLLoader

    @FXML
    private void handleRouting(MouseEvent event) {

        Label label = (Label) event.getSource();
        String labelText = label.getText();


        String PageRouter = "/com/example/tp_poo/DefaultPage.fxml"; // Chemin par défaut
        boolean newPage = false;

        switch (labelText)
        {
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
                user.getMes_patients().get(1).getPatient();
                user.getMes_patients().get(1).getRendez_vous();
                user.getMes_patients().get(1).getFiches_suivi();


                String username =user.getCompte().getEmail();
                String filepath="./src/main/Userinformation/" + username + ".ser";
                Orthophoniste.serialize(filepath,user);
                Orthophoniste user1=OrthophonisteSessionManager.getCurrentOrthophonisteName();

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
                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void page_atelier(ActionEvent event) {
        try {
            // Load the FXML file for the signup page
            Parent signupRoot = FXMLLoader.load(getClass().getResource("/com/example/tp_poo/atelier.fxml"));
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Create a new scene with the signup root
            Scene scene = new Scene(signupRoot, 1000, 670);

            // Set the scene on the stage
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void page_consultation(ActionEvent event) {
        try {
            // Load the FXML file for the signup page
            Parent signupRoot = FXMLLoader.load(getClass().getResource("/com/example/tp_poo/Consultation.fxml"));
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Create a new scene with the signup root
            Scene scene = new Scene(signupRoot, 1000, 670);

            // Set the scene on the stage
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void page_suivi(ActionEvent event) {
        try {
            // Load the FXML file for the signup page
            Parent signupRoot = FXMLLoader.load(getClass().getResource("/com/example/tp_poo/Suivi.fxml"));
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Create a new scene with the signup root
            Scene scene = new Scene(signupRoot, 1000, 670);

            // Set the scene on the stage
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
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
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ClassNotFoundException {

        String nom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getNom();
        System.out.println(nom);
        String prenom =OrthophonisteSessionManager.getCurrentOrthophonisteName().getCompte().getPrenom();

        utilisateur1.setText(nom + " " + prenom);


        TreeMap<Rendez_vous, Dossier> futureRendezVous = rendezVous();

        for (Map.Entry<Rendez_vous, Dossier> entry : futureRendezVous.entrySet()) {
            Rendez_vous rendezVous = entry.getKey();
            Dossier dossier = entry.getValue();

            // Charger l'interface FXML pour chaque rendez-vous
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/tp_poo/Agendaligne.fxml"));
            try {
                HBox hBox = fxmlLoader.load();

                // Remplir le tableau avec les informations du rendez-vous et du dossier
                AgendaligneController cic = fxmlLoader.getController();
                cic.remplir_tableau(rendezVous, dossier);

                // Ajouter le HBox à l'interface principale
                agendaligne.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private TreeMap<Rendez_vous, Dossier> rendezVous() throws IOException, ClassNotFoundException {

        Orthophoniste users =OrthophonisteSessionManager.getCurrentOrthophonisteName();

        TreeMap<Integer, Dossier> dossiers = users.getMes_patients();


        LocalDate now = LocalDate.now();

        TreeMap<Rendez_vous, Dossier> futureRendezVous = new RendezVousManager().getFutureRendezVous(dossiers, now);


        return futureRendezVous;
    }

    // Stub pour comparer les rendez-vous par date uniquement
    public class RendezVousManager {

        public static TreeMap<Rendez_vous, Dossier> getFutureRendezVous(TreeMap<Integer, Dossier> dossiers, LocalDate now) {
            return dossiers.entrySet().stream()
                    .flatMap(entry -> entry.getValue().getRendez_vous().stream()
                            .filter(rv -> !rv.getDate().isBefore(now))
                            .map(rv -> Map.entry(rv, entry.getValue())))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            TreeMap::new));
        }

    }
}