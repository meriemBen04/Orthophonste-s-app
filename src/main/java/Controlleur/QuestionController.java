/**
 * Sample Skeleton for 'QCM_QCU.fxml' Controller Class
 */

package Controlleur;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import Model.Dossier;
import Model.OrthophonisteSessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;

public class QuestionController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="choice1TextField"
    private TextField choice1TextField; // Value injected by FXMLLoader

    @FXML // fx:id="choice2TextField"
    private TextField choice2TextField; // Value injected by FXMLLoader

    @FXML // fx:id="choice3TextField"
    private TextField choice3TextField; // Value injected by FXMLLoader

    @FXML // fx:id="questionLabel"
    private Label questionLabel; // Value injected by FXMLLoader

    @FXML // fx:id="questionLabel1"
    private Label questionLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="questionTextField"
    private TextField questionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="type_question"
    private ComboBox<String> type_question; // Value injected by FXMLLoader
@FXML
    public void initilize() {

    ObservableList<String> options = FXCollections.observableArrayList(
            "QCM",
            "QCU",
            "question_libre");
            type_question.setOnAction(e -> handleQuestionTypeChange());
          handleQuestionTypeChange();


            type_question.getItems().clear();
            // Set the items for the ComboBox
            type_question.setItems(options);
}
    public void initializeComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList("QCM", "QCU", "question_libre");
        type_question.setItems(options);
        type_question.getSelectionModel().selectFirst(); // Set default selection if needed
        type_question.setOnAction(e -> handleQuestionTypeChange());
        handleQuestionTypeChange();
    }
    private void handleQuestionTypeChange() {


        String selectedType = type_question.getValue();

        if (!"QCM".equals(selectedType) && !"QCU".equals(selectedType)) {
           choice1TextField.setVisible(false);
           choice2TextField.setVisible(false);
           choice3TextField.setVisible(false);
        }else {
            choice1TextField.setVisible(true);
            choice2TextField.setVisible(true);
            choice3TextField.setVisible(true);
        }
    }
    public Labeled getQuestionLabel()
    {
        return this.questionLabel;
    }
    public TextField getQuestionTextField()
    {
        return this.questionTextField;
    }
    public TextField getChoice1TextField()
    {
        return this.choice1TextField;
    }
    public TextField getChoice2TextField()
    {
        return this.choice2TextField;
    }
    public TextField getChoice3TextField()
    {
        return this.choice3TextField;
    }
    public ComboBox<String> getTypeQuestion() {
        return type_question;
    }

}


