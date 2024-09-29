/**
 * Sample Skeleton for 'epreuves.fxml' Controller Class
 */

package Controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class EpreuvesController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="patientslay"
    private VBox patientslay; // Value injected by FXMLLoader

    @FXML // fx:id="username1"
    private Label username1; // Value injected by FXMLLoader

    @FXML
    void handleRouting(MouseEvent event) {

    }
    @FXML
    void nextpage(ActionEvent event) {

    }

    @FXML
    void profile(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert patientslay != null : "fx:id=\"patientslay\" was not injected: check your FXML file 'epreuves.fxml'.";
        assert username1 != null : "fx:id=\"username1\" was not injected: check your FXML file 'epreuves.fxml'.";

    }

}
