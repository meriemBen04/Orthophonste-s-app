/**
 * Sample Skeleton for 'ajouttrouble.fxml' Controller Class
 */

package Controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ajoutertroubleController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ajouterobjectif"
    private Button ajouterobjectif; // Value injected by FXMLLoader


    @FXML // fx:id="nomtrouble"
    private TextField nomtrouble; // Value injected by FXMLLoader

    @FXML // fx:id="nomtroubleerror"
    private Label nomtroubleerror; // Value injected by FXMLLoader

    @FXML // fx:id="type_trouble"
    private ComboBox<String> type_trouble; // Value injected by FXMLLoader

    @FXML // fx:id="typetroubleerreur"
    private Label typetroubleerreur; // Value injected by FXMLLoader

    @FXML
    private VBox listtrouble;


    @FXML
    void ajoutertrouble(ActionEvent event) {

        nomtroubleerror.setText("");
        typetroubleerreur.setText("");

        if (validateAllTroubles()) {
            addNewHBox();
            // Clear error labels after successful validation (optional)
            nomtroubleerror.setText("");
            typetroubleerreur.setText("");
        } else {
            System.out.println("Please fill in all fields before adding a new objective.");
        }
    }
    public void setData(VBox listeobject) {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Deglutition",
                "Neuro developpementaux",
                "Cognitifs"
        );
        type_trouble.getItems().clear();

        // Set the items for the ComboBox
        type_trouble.setItems(options);
        this.listtrouble = listeobject;
    }

    private void addNewHBox() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/ajouttrouble.fxml"));
            HBox newHBox = fxmlLoader.load();
            ajoutertroubleController controller = fxmlLoader.getController();
            controller.setData(listtrouble); // Set the VBox in the new controller instance
            listtrouble.getChildren().add(newHBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        assert ajouterobjectif != null : "fx:id=\"ajouterobjectif\" was not injected: check your FXML file 'ajouttrouble.fxml'.";
        assert nomtrouble != null : "fx:id=\"nomtrouble\" was not injected: check your FXML file 'ajouttrouble.fxml'.";
        assert nomtroubleerror != null : "fx:id=\"nomtroubleerror\" was not injected: check your FXML file 'ajouttrouble.fxml'.";
        assert type_trouble != null : "fx:id=\"type_trouble\" was not injected: check your FXML file 'ajouttrouble.fxml'.";
        assert typetroubleerreur != null : "fx:id=\"typetroubleerreur\" was not injected: check your FXML file 'ajouttrouble.fxml'.";

    }
    private boolean validateAllTroubles() {
        boolean hasEmptyFields = false; // Track if any empty fields exist
        for (Node node : listtrouble.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                for (Node childNode : hBox.getChildren()) {
                    if (childNode instanceof AnchorPane) {
                        AnchorPane anchorPane = (AnchorPane) childNode;
                        for (Node grandChildNode : anchorPane.getChildren()) {
                            if (grandChildNode instanceof TextField) {
                                TextField textField = (TextField) grandChildNode;

                                if (textField.getText().isEmpty()) {
                                    hasEmptyFields = true;
                                    // Set error labels for empty fields
                                    if (textField == nomtrouble) {
                                        nomtroubleerror.setText("Veuillez entrer le nom de trouble");
                                    }
                                }
                            } else if (grandChildNode instanceof ComboBox) {
                                ComboBox<String> comboBox = (ComboBox<String>) grandChildNode;
                                String selectedValue = comboBox.getValue();

                                if (selectedValue == null) {
                                    hasEmptyFields = true;
                                    typetroubleerreur.setText("Choisissez le type de trouble");
                                }
                            }
                        }
                    } else if (childNode instanceof TextField) {
                        TextField textField = (TextField) childNode;

                        if (textField.getText().isEmpty()) {
                            hasEmptyFields = true;
                            // Set error labels for empty fields
                            if (textField == nomtrouble) {
                                nomtroubleerror.setText("Veuillez entrer le nom de trouble");
                            }
                        }
                    }
                }
            }
        }

        return !hasEmptyFields; // Return true only if all fields are filled
    }





}
