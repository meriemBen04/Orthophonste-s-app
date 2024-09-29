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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class QuestionnaireController implements Initializable {

    static boolean ajouter =true;
    static String titrestatic;
    static int size;
    @FXML
    private TextField capacityTextField;

    @FXML
    private Label capacitylabel;

    @FXML
    private VBox container;

    @FXML
    private Button enregistrer;

    @FXML
    private Button generer;

    @FXML
    private TextField titre;

    @FXML
    private Label username1;

    public void initialize(URL location, ResourceBundle resources) {

        if (!ajouter) {
            capacitylabel.setVisible(false);
            capacityTextField.setVisible(false);
            generer.setVisible(false);
            int existingViews = container.getChildren().size() - 1;
            if (existingViews > 0) {
                container.getChildren().remove(0, existingViews + 1);
            }
            int capacity = QuestionnaireController.size;
            titre.setText(titrestatic);
            for (int i = 0; i < capacity; i++) {
                loadQuestionView(i);

            }
        } else {
            capacitylabel.setVisible(true);
            capacityTextField.setVisible(true);
            generer.setVisible(true);
        }
    }

    @FXML
    public void generateTextFields(ActionEvent event) {
        int existingViews = container.getChildren().size() - 1;
        if (existingViews > 0) {
            container.getChildren().remove(0, existingViews + 1);
        }
        int capacity = Integer.parseInt(capacityTextField.getText());
        for (int i = 0; i < capacity; i++) {
            System.out.println(i);
            loadQuestionView(i);
        }
    }

    private void loadQuestionView(int i) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/QCM_QCU.fxml"));
        try {
            VBox questionView = loader.load();
            QuestionController controller = loader.getController();
            controller.getQuestionLabel().setText("Question " + (i + 1) + " :");
            controller.initializeComboBox();
            container.getChildren().add(questionView);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void create() {

        List<Question> questionList = new ArrayList<>();
        int capacity = ajouter ? Integer.parseInt(capacityTextField.getText()) : size;

        for (int i = 0; i < capacity; i++) {
            VBox questionView = (VBox) container.getChildren().get(i);

            // Assume that the structure of VBox is known and it contains an AnchorPane
            AnchorPane anchorPane = (AnchorPane) questionView.getChildren().get(0);

            // Retrieve the question text field
            TextField questionTextField = (TextField) anchorPane.lookup("#questionTextField");

            // Retrieve the type of question combo box
            ComboBox<String> typeQuestionComboBox = (ComboBox<String>) anchorPane.lookup("#type_question");

            // Retrieve the choice text fields if the question type is QCM or QCU
            TextField choice1TextField = (TextField) anchorPane.lookup("#choice1TextField");
            TextField choice2TextField = (TextField) anchorPane.lookup("#choice2TextField");
            TextField choice3TextField = (TextField) anchorPane.lookup("#choice3TextField");

            // Get the type and question statement
            String type = typeQuestionComboBox.getValue();
            String enonce = questionTextField.getText().trim();

            Question question = null;

            switch (type) {
                case "QCM":
                    question = new QCM();
                    question.setEnonce(questionTextField.getText());

                    List<String> choicesQCM = new ArrayList<>();
                    choicesQCM.add(choice1TextField.getText());
                    choicesQCM.add(choice2TextField.getText());
                    choicesQCM.add(choice3TextField.getText());
                    ((QCM) question).setChoix(choicesQCM);
                    break;

                case "QCU":
                    question = new QCU();
                    question.setEnonce(questionTextField.getText());

                    List<String> choicesQCU = new ArrayList<>();
                    choicesQCU.add(choice1TextField.getText());
                    choicesQCU.add(choice2TextField.getText());
                    choicesQCU.add(choice3TextField.getText());
                    ((QCU) question).setChoix(choicesQCU);
                    break;

                case "question_libre":
                    question = new question_libre();
                    question.setEnonce(questionTextField.getText());
                    break;
            }

            // Set the question statement and add it to the list
            if (question != null) {
                question.setEnonce(enonce);
                questionList.add(question);
            }
        }

        // Assuming you have a way to handle the list of questions, e.g., saving to a questionnaire
        // For demonstration, print the questions
        for (Question q : questionList) {
            System.out.println("Enonce: " + q.getEnonce());
            if (q instanceof QCM) {
                System.out.println("Choices: " + ((QCM) q).getChoix());
            } else if (q instanceof QCU) {
                System.out.println("Choices: " + ((QCU) q).getChoix());
            }
        }
    }


    @FXML
    void profile(ActionEvent event){

        try {
            String PageRouter = "/com/example/tp_poo/Profile.fxml";
            Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
            Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
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

}
