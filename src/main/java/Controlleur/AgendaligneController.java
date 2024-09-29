/**
 * Sample Skeleton for 'agendaligne.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AgendaligneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="action"
    private Button action; // Value injected by FXMLLoader

    @FXML // fx:id="duree"
    private Label duree; // Value injected by FXMLLoader

    @FXML // fx:id="heure"
    private Label heure; // Value injected by FXMLLoader

    @FXML // fx:id="jour"
    private Label jour; // Value injected by FXMLLoader

    @FXML // fx:id="num_dossier"
    private Label num_dossier; // Value injected by FXMLLoader

    @FXML // fx:id="type"
    private Label type; // Value injected by FXMLLoader/ Value injected by FXMLLoader

    @FXML
    private ImageView plus;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert duree != null : "fx:id=\"duree\" was not injected: check your FXML file 'agendaligne.fxml'.";
        assert jour != null : "fx:id=\"jour\" was not injected: check your FXML file 'agendaligne.fxml'.";
        assert num_dossier != null : "fx:id=\"num_dossier\" was not injected: check your FXML file 'agendaligne.fxml'.";
        assert type != null : "fx:id=\"type\" was not injected: check your FXML file 'agendaligne.fxml'.";
        assert plus != null : "fx:id=\"type\" was not injected: check your FXML file 'agendaligne.fxml'.";


    }
    public  boolean vérifier_information(Patient patient){
        if (patient instanceof Adulte){
            if(patient.getLieu_naissance() !=null && patient.getAdresse()!= null && ((Adulte) patient).getDimplome()!= null && ((Adulte) patient).getProfession() != null &&patient.getDate_naissance()!=null && ((Adulte) patient).getNumero_personnel()!= 0){

                return true;

            }
        }else {
            if(patient.getLieu_naissance() !=null && patient.getAdresse()!= null && ((Enfant) patient).getClass_etude()!= null && ((Enfant) patient).getNumeroparent() != null &&patient.getDate_naissance()!=null){
                return true;

            }
        }
        return false;
    }

    public void remplir_tableau(Rendez_vous rd, Dossier dossier) {

        duree.setText(rd.getDuree());
        heure.setText(String.valueOf(rd.getHeure()));
        type.setText(rd.getType().toString());
        num_dossier.setText(rd.getPatientName());
        LocalDate date = rd.getDate();
        jour.setText(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))); // Format as needed
        String type_rd = rd.getType().toString();
        if (type_rd.equals("CONSULTATION") && !vérifier_information(dossier.getPatient()) ) {

            action.setVisible(true);  // Assurez-vous que le bouton est visible pour les consultations
            action.setOnAction(event -> {
                try {



                    if(dossier.getPatient() instanceof Adulte){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/info_adulte.fxml"));
                        Parent root = loader.load();
                        info_adulteController Controller = loader.getController();
                        Controller.setInfoData(rd,dossier);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1000, 670);
                        stage.setScene(scene);

                    }else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/info_enfant.fxml"));
                        Parent root = loader.load();
                        info_enfantController Controller = loader.getController();
                        Controller.setInfoData(rd,dossier);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 1000, 670);
                        stage.setScene(scene);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            action.setVisible(false);
            plus.setVisible(false);// Masquez le bouton pour les autres types de rendez-vous
        }
    }
}


