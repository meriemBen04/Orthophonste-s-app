/**
 * Sample Skeleton for 'anam_enfant_elemnt.fxml' Controller Class
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

public class anam_enfant_elemntController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ajouterquestion"
    private Button ajouterquestion; // Value injected by FXMLLoader

    @FXML // fx:id="categorie"
    private ComboBox<String> categorie; // Value injected by FXMLLoader

    @FXML // fx:id="categorieerror"
    private Label categorieerror; // Value injected by FXMLLoader

    @FXML // fx:id="question"
    private TextField question; // Value injected by FXMLLoader

    @FXML // fx:id="questionerror"
    private Label questionerror; // Value injected by FXMLLoader
    @FXML
    private VBox listquestion;
    private  boolean enfant ;
    @FXML
    void ajouterobjectif(ActionEvent event) {

        if (validateAllTroubles()) {
            addNewHBox();
            // Clear error labels after successful validation (optional)
            questionerror.setText("");
            categorieerror.setText("");
        } else {
            System.out.println("Please fill in all fields before adding a new objective.");
        }
    }
    private boolean validateAllTroubles() {
        boolean hasEmptyFields = false; // Track if any empty fields exist
        for (Node node : listquestion.getChildren()) {
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
                                    if (textField == question) {
                                       questionerror.setText("Veuillez entrer la question");
                                    }
                                }
                            } else if (grandChildNode instanceof ComboBox) {
                                ComboBox<String> comboBox = (ComboBox<String>) grandChildNode;
                                String selectedValue = comboBox.getValue();

                                if (selectedValue == null) {
                                    hasEmptyFields = true;
                                    categorieerror.setText("Choisissez la catégorie de la question");
                                }
                            }
                        }
                    } else if (childNode instanceof TextField) {
                        TextField textField = (TextField) childNode;

                        if (textField.getText().isEmpty()) {
                            hasEmptyFields = true;
                            // Set error labels for empty fields
                            if (textField ==question) {
                               questionerror.setText("Veuillez entrer la question");
                            }
                        }
                    }
                }
            }
        }

        return !hasEmptyFields; // Return true only if all fields are filled
    }

    public void setData_enfant(VBox listquestions) {

        this.enfant =true;
        ObservableList<String> options = FXCollections.observableArrayList(
               " Strecture familiale",
                "Antecedents familiaux",
                "Conditions natales",
                "Developpement psychomoteur",
                "Developpement langagier",
                "Caractere comportement"
        );
        categorie.getItems().clear();

        // Set the items for the ComboBox
        categorie.setItems(options);
        this.listquestion = listquestions;
    }
    public void setData_adulte(VBox listquestions) {
        this.enfant =false;


        ObservableList<String> options = FXCollections.observableArrayList(
                "Histoire_de_maladie",
                "Suivi_médical"
        );

        categorie.getItems().clear();
        // Set the items for the ComboBox
        categorie.setItems(options);
        this.listquestion = listquestions;

    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {


        assert ajouterquestion != null : "fx:id=\"ajouterquestion\" was not injected: check your FXML file 'anam_enfant_elemnt.fxml'.";
        assert categorie != null : "fx:id=\"categorie\" was not injected: check your FXML file 'anam_enfant_elemnt.fxml'.";
        assert categorieerror != null : "fx:id=\"categorieerror\" was not injected: check your FXML file 'anam_enfant_elemnt.fxml'.";
        assert question != null : "fx:id=\"question\" was not injected: check your FXML file 'anam_enfant_elemnt.fxml'.";
        assert questionerror != null : "fx:id=\"questionerror\" was not injected: check your FXML file 'anam_enfant_elemnt.fxml'.";

    }
    private void addNewHBox() {
        if (enfant == true) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/anam_enfant_elemnt.fxml"));
                HBox newHBox = fxmlLoader.load();
                anam_enfant_elemntController controller = fxmlLoader.getController();
                controller.setData_enfant(listquestion); // Set the VBox in the new controller instance
                listquestion.getChildren().add(newHBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/anam_enfant_elemnt.fxml"));
                HBox newHBox = fxmlLoader.load();
                anam_enfant_elemntController controller = fxmlLoader.getController();
                controller.setData_adulte(listquestion); // Set the VBox in the new controller instance
                listquestion.getChildren().add(newHBox);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
