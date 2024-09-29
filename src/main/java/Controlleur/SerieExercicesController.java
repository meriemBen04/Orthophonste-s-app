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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SerieExercicesController implements Initializable {
    static boolean ajouter=true;
    static String titrestatic;
    static String[][] tab1;
    static int size;
    @FXML
    private Label Agenda;
    @FXML
    private Label Patients;
    @FXML
    private Label BO;
    @FXML
    private Label FicheSuivi;
    @FXML
    private Label Testes;
    @FXML
    private Label Profile;
    @FXML
    private Label deconnecter;
    @FXML
    private Label ajoutertest;
    @FXML
    private Label Consultertest;
    @FXML
    private Label username1;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Orthophoniste user= OrthophonisteSessionManager.getCurrentOrthophonisteName();
        username1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());

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

    @FXML
    private TextField titre;
    @FXML
    private VBox container;
    @FXML
    private TextField capacityTextField;
    @FXML
    private Button generer;
    @FXML
    private Label capacityLabel;

    public void initialize()
    {
        if(!ajouter)
        {
            capacityLabel.setVisible(false);
            generer.setVisible(false);
            capacityTextField.setVisible(false);
            int existingViews = container.getChildren().size() - 1;

            // Remove existing views only if necessary
            if (existingViews > 0) {
                container.getChildren().remove(0, existingViews + 1); // Remove from index 1 (inclusive) to existingViews (exclusive)
            }
            titre.setText(titrestatic);
            // Iterate through the loop based on capacity
            for (int i = 0; i < size; i++) {
                // Load the FXML for the exercise view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Exercice.fxml")); // Assuming "exercice.fxml" is the filename

                try {
                    VBox exerciceView = (VBox) loader.load(); // Load the FXML and get the root node (VBox)
                    // Access elements within the exercise view (assuming IDs)
                    Label consigneLabel = (Label) exerciceView.getChildren().get(0);
                    TextField consigneField = (TextField) exerciceView.getChildren().get(1);
                    HBox detailsBox = (HBox) exerciceView.getChildren().get(2); // Assuming details are in the third child (HBox)
                    consigneField.setText(tab1[i][0]);
                    TextField materielField = (TextField) detailsBox.getChildren().get(1); // Assuming "Materiel" label is the first child
                    TextField nbrFoisField = (TextField) detailsBox.getChildren().get(3); // Assuming "Nombre de fois" label is the third child
                    materielField.setText(tab1[i][1]);
                    nbrFoisField.setText(tab1[i][2]);
                    // Add the loaded exercise view to the container
                    container.getChildren().add(exerciceView);

                } catch (IOException e) {
                    e.printStackTrace(); // Handle potential exception during FXML loading
                    // Consider displaying a user-friendly error message
                }
            }
        }else{
            capacityLabel.setVisible(true);
            generer.setVisible(true);
            capacityTextField.setVisible(true);
        }
    }

    @FXML
    public void generateExerciceViews(ActionEvent event) {

        int existingViews = container.getChildren().size() - 1;

        // Remove existing views only if necessary
        if (existingViews > 0) {
            container.getChildren().remove(0, existingViews + 1); // Remove from index 1 (inclusive) to existingViews (exclusive)
        }
        int capacity = Integer.parseInt(capacityTextField.getText()); // Assuming a capacity field exists

        // Iterate through the loop based on capacity
        for (int i = 0; i < capacity; i++) {
            // Load the FXML for the exercise view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_poo/Exercice.fxml")); // Assuming "exercice.fxml" is the filename

            try {
                VBox exerciceView = (VBox) loader.load(); // Load the FXML and get the root node (VBox)

                // Access elements within the exercise view (assuming IDs)
                Label consigneLabel = (Label) exerciceView.getChildren().get(0);
                TextField consigneField = (TextField) exerciceView.getChildren().get(1);
                HBox detailsBox = (HBox) exerciceView.getChildren().get(2); // Assuming details are in the third child (HBox)

                TextField materielField = (TextField) detailsBox.getChildren().get(1); // Assuming "Materiel" label is the first child
                TextField nbrFoisField = (TextField) detailsBox.getChildren().get(3); // Assuming "Nombre de fois" label is the third child

                // Add the loaded exercise view to the container
                container.getChildren().add(exerciceView);

            } catch (IOException e) {
                e.printStackTrace(); // Handle potential exception during FXML loading
                // Consider displaying a user-friendly error message
            }
        }
    }

    @FXML
    private Button enregistrer;

    @FXML
    public void create() {
        if(ajouter)
        {
            ArrayList<Exercice> testList = new ArrayList<>();

            for (int i = 0; i < container.getChildren().size(); i++) {
                VBox nestedVBox = (VBox) container.getChildren().get(i);

                TextField consigneTextField = (TextField) nestedVBox.getChildren().get(1);
                HBox hbox = (HBox) nestedVBox.getChildren().get(2);
                TextField materielTextField = (TextField) hbox.getChildren().get(1);
                TextField nombreDeFoisTextField = (TextField) hbox.getChildren().get(3);

                String consigne = consigneTextField.getText().trim();
                String materiel = materielTextField.getText().trim();
                String nombreDeFois = nombreDeFoisTextField.getText().trim();

                System.out.println("Consigne: " + consigne);
                System.out.println("Materiel: " + materiel);
                System.out.println("Nombre de fois: " + nombreDeFois);

                // Create a Test_exercice object and add it to the list (adjust as needed)
                Exercice testExercice = new Exercice(consigne, materiel, Integer.parseInt(nombreDeFois));
                testList.add(testExercice);
                Test_exercice et =new Test_exercice("titre",5,testList);
                Test[] t = new  Test[4];
                t[0]=et;

                Epreuve_clinique eh =new Epreuve_clinique(new String[]{"nnnn", "my observation"},t);
                Epreuve_clinique[]  rr= new Epreuve_clinique[2];
                rr[0]=eh;
                //OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(1).getBilans_orth().get(1).setEpreuves_cliniques(rr);


            }

            Test_exercice test = new Test_exercice(titre.getText(),container.getChildren().size(),testList);
            OrthophonisteSessionManager.getCurrentOrthophonisteName().getTestes().getSerieExercices().add(test);
           Orthophoniste o = OrthophonisteSessionManager.getCurrentOrthophonisteName();

        }else
        {
            String[][] tab;
            // Allocate space for the 2D array based on capacity (adjust columns for options)
            tab = new String[size][3]; // Adjust number of columns based on your number of choices

            // Iterate through the dynamically generated custom views
            for (int i = 0; i < size; i++) {
                VBox questionView = (VBox) container.getChildren().get(i); // Assuming first element is not a view

                // Access elements within the custom view (assuming IDs)
                Label questionLabel = (Label) questionView.getChildren().get(0);
                TextField questionTextField = (TextField) questionView.getChildren().get(1);
                HBox choicesBox = (HBox) questionView.getChildren().get(2); // Assuming choices are in the third child

                String question = questionTextField.getText();

                // Store question in the first column of the array
                tab[i][0] = question;

                // Iterate through choice TextFields within the HBox
                for (int j = 1; j < choicesBox.getChildren().size(); j++) { // Start from index 1 (assuming "Choix" label is the first child)
                    TextField choiceField = (TextField) choicesBox.getChildren().get(j);
                    String choice = choiceField.getText().trim(); // Trim leading/trailing whitespace

                    // Store only non-empty choices (optional, adjust as needed)
                    if (!choice.isEmpty()) {
                        System.out.println(i+" "+j);
                        tab[i][j] = choice;
                    }
                    j=3;
                }
            }
            Test_exercice serie = new Test_exercice();
            Testes testes = OrthophonisteSessionManager.getCurrentOrthophonisteName().getTestes();
            serie.setTitre(titre.getText());
            serie.setExercice(tab);



        }
        try {
            Parent next = (Parent)FXMLLoader.load(this.getClass().getResource("/com/example/tp_poo/Testes.fxml"));
            Scene currentScene = this.enregistrer.getScene();
            currentScene.setRoot(next);

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
}
