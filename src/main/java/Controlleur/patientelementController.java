package Controlleur;

import Model.Patient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class patientelementController implements Initializable {

    @FXML
    private Label date;

    @FXML
    private Button dossier;

    @FXML
    private Label nom;

    @FXML
    private Label num_dossier;

    @FXML
    private Label prenom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }


    public EventHandler<ActionEvent> getdossierm(Object e)
    {

        return null;
    }

    public void setData(Patient patient)
    {

        num_dossier.setText(String.valueOf(patient.getNum_dossier()));
        nom.setText(patient.getNom());
        prenom.setText(patient.getPrenom());
        if(patient.getDate_naissance() != null){
            date.setText(String.valueOf(patient.getDate_naissance()));
        }


        dossier.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/Dossier.fxml"));
                Parent root = loader.load();

                DossierController dossierController = loader.getController();
                dossierController.setDossierData(patient.getNum_dossier());


                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 670);

                stage.setScene(scene);

//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();

                // Close the current stage if needed
                // ((Stage)dossier.getScene().getWindow()).close();

            } catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        });





    }


}
