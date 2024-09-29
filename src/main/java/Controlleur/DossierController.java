package Controlleur;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class DossierController implements Initializable {


    @FXML
    private AnchorPane Infopersonnelle;

    @FXML
    private Label dossierusername;

    @FXML
    private AnchorPane patientbo;

    @FXML
    private AnchorPane patientfiche;

    @FXML
    private AnchorPane patientrendezvous;

    @FXML
    private Label utilisateur1;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Orthophoniste user= OrthophonisteSessionManager.getCurrentOrthophonisteName();

        utilisateur1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());

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
    void retour(ActionEvent event)
    {
        try {
            String PageRouter = "/com/example/tp_poo/Patients.fxml";
            // Load the desired page
            Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    void retour(ActionEvent event)
//    {
//        try {
//            String PageRouter = "/com/example/tp_poo/Patient.fxml";
//            // Load the desired page
//            Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
//            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
//            Scene.setScene(scene);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void setDossierData(int num_dossier) throws IOException, ClassNotFoundException
    {
        Orthophoniste user=OrthophonisteSessionManager.getCurrentOrthophonisteName();
        Dossier dossier = user.rechercher_patient(num_dossier);
        if (dossier != null)
        {

            Patient patient = dossier.getPatient();
            dossierusername.setText(patient.getNom()+" "+patient.getPrenom());



                Infopersonnelle.setOnMouseClicked(event ->
                {
                    try
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/Infopersonnelle.fxml"));
                        Parent root = loader.load();
                        InfopersonnelleController InfoController = loader.getController();
                        InfoController.setInfoData(patient);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1000, 670);
                        stage.setScene(scene);

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });

                patientfiche.setOnMouseClicked(event ->
                {
                    try
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/Fiche.fxml"));
                        Parent root = loader.load();
                        FicheController fiche = loader.getController();
                        fiche.setficheData(dossier);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1000, 670);
                        stage.setScene(scene);

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });

                patientrendezvous.setOnMouseClicked(event ->
                {
                    try
                    {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/RendezvousPatient.fxml"));
                        Parent root = loader.load();
                        RendezvousPatientController rend = loader.getController();
                        rend.setficheData(dossier);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1000, 670);
                        stage.setScene(scene);

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
            patientbo.setOnMouseClicked(event ->
            {
                try
                {


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/BO.fxml"));
                    Parent root = loader.load();
                    BoController rend = loader.getController();
                    rend.setficheData(dossier);
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root, 1000, 670));
//                    stage.show();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 1000, 670);
                    stage.setScene(scene);

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });
        }
    }



}
