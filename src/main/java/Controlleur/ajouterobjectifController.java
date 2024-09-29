package Controlleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ajouterobjectifController {

    @FXML
    private Button ajouterobjectif;

    @FXML
    private TextField nomobjectif;

    @FXML
    private Label nomobjerreur;

    @FXML
    private ComboBox<String> type_objectif;

    @FXML
    private Label typeobjerreur;

    @FXML
    private VBox listeobject;

    public void setData(VBox listeobject) {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Court Terme",
                "Moyen Terme",
                "Long Terme"
        );
        type_objectif.getItems().clear();

        // Set the items for the ComboBox
        type_objectif.setItems(options);
        this.listeobject = listeobject;
    }

    @FXML
    void ajouterobjectif(ActionEvent event) {
        if (validateAllObjectives()) {
            addNewHBox();
            // Clear error labels after successful validation (optional)
            nomobjerreur.setText("");
            typeobjerreur.setText("");
        } else {
            System.out.println("Please fill in all fields before adding a new objective.");
        }
    }

    private boolean validateAllObjectives() {
        boolean hasEmptyFields = false; // Track if any empty fields exist

        for (Node node : listeobject.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                for (Node childNode : hBox.getChildren()) {
                    if (childNode instanceof AnchorPane) { // Check for AnchorPane
                        AnchorPane anchorPane = (AnchorPane) childNode;
                        for (Node grandChildNode : anchorPane.getChildren()) {
                            if (grandChildNode instanceof TextField) {
                                TextField textField = (TextField) grandChildNode;
                                String selectedValue = type_objectif.getValue();

                                if (textField.getText().isEmpty()) {
                                    hasEmptyFields = true;
                                    // Set error labels for empty fields
                                    if (textField == nomobjectif) {
                                        nomobjerreur.setText(" nom objective!");
                                    } else if (selectedValue == null || !(selectedValue.equals("Court Terme") || !(selectedValue.equals("Long Terme")) || !(selectedValue.equals("Moyen Terme"))))
                                    {
                                        typeobjerreur.setText("Choisissez le type de la séance");
                                    }



                                } else {
                                    // No need to clear labels here, handled elsewhere
                                }
                            }
                        }
                    } else if (childNode instanceof TextField) { // Handle TextFields directly in HBox
                        TextField textField = (TextField) childNode;
                        String selectedValue = type_objectif.getValue();

                        if (textField.getText().isEmpty()) {
                            hasEmptyFields = true;
                            // Set error labels for empty fields
                            if (textField == nomobjectif) {
                                nomobjerreur.setText(" nom objective!");
                            } else if (selectedValue == null || !(selectedValue.equals("Court Terme") || !(selectedValue.equals("Long Terme")) || !(selectedValue.equals("Moyen Terme"))))
                            {
                                typeobjerreur.setText("Choisissez le type de la séance");
                            }
                        } else {
                            // No need to clear labels here, handled elsewhere
                        }
                    }
                }
            }
        }

        // Clear error labels only if validation is successful (all fields filled)
        if (!hasEmptyFields) {
            nomobjerreur.setText("");
            typeobjerreur.setText("");
        }

        return !hasEmptyFields; // Return true only if all fields are filled
    }


    private void addNewHBox() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/ajoutobjec.fxml"));
            HBox newHBox = fxmlLoader.load();
            ajouterobjectifController controller = fxmlLoader.getController();
            controller.setData(listeobject); // Set the VBox in the new controller instance
            listeobject.getChildren().add(newHBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
