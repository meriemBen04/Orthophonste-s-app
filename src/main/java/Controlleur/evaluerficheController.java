package Controlleur;

import Model.*;
import com.example.tp_poo.tpApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class evaluerficheController implements Initializable {

    @FXML
    private Label creerfiche;

    @FXML
    private Button edit_profile;

    @FXML
    private Button enregistrer;

    @FXML
    private VBox listeobject;

    @FXML
    private TextField num_dossier;

    @FXML
    private Label numeroerror;

    @FXML
    private Label numeroerror1;

    @FXML
    private Button retour;

    @FXML
    private ComboBox<Rendez_vous> rend;

    @FXML
    private Label utilisateur1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Orthophoniste user = OrthophonisteSessionManager.getCurrentOrthophonisteName();
        utilisateur1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());
        rend.setDisable(true);

        // Add listener to the TextField


        // Add listener to the TextField
        num_dossier.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                rend.setDisable(true);
                rend.getItems().clear();
            } else {
                try {
                    int numero = Integer.parseInt(num_dossier.getText());
                    Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
                    if (dossier != null) {
                        Set<Rendez_vous> rendou = dossier.getRendez_vous();
                        updateComboBox(rendou);
                    }
                    rend.setDisable(false);
                } catch (NumberFormatException e) {
                    afficherMessagewarning("Veuillez entrer un numéro de dossier valide.");
                    rend.setDisable(true);
                    rend.getItems().clear();
                }
            }
        });

        // Add a listener to the ComboBox to enable/disable the Label
        rend.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            creerfiche.setDisable(newValue == null);
        });

        // Set the StringConverter for the ComboBox
        rend.setConverter(new StringConverter<Rendez_vous>()
        {
            @Override
            public String toString(Rendez_vous rendezVous) {
                return Dossier.rendezVousToString(rendezVous);
            }

            @Override
            public Rendez_vous fromString(String string)
            {
                return rend.getItems().stream()
                        .filter(rv -> Dossier.rendezVousToString(rv).equals(string))
                        .findFirst().orElse(null);
            }
        });
    }
    public void updateComboBox(Set<Rendez_vous> filteredRendezVouss) {
        TreeSet<Rendez_vous> filteredRendezVous = Dossier.filterRendezVous(filteredRendezVouss);

        if (filteredRendezVous.size() == 0) {
            afficherMessagewarning("IL YA PAS DES SEANCES DE SUIVI POUR CE PATEINT ");
        } else {
            rend.getItems().setAll(filteredRendezVous);
        }
    }
    @FXML
    boolean chercher_dossier(KeyEvent event)
    {
        numeroerror.setText("");
        String num = num_dossier.getText();
        boolean existe = false;
        int numero=0;
        if (num.isEmpty()) {
            rend.setDisable(true);
            numeroerror.setText("Le champ nom ne doit pas être vide.");


        }else
        {
            try {numero= Integer.parseInt(num);
                Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
                if(dossier != null) {
                    existe = true;
                }else {
                    rend.setDisable(true);

                    numeroerror.setText("Le numéro de dossier n'existe pas!.");
                }

            } catch (NumberFormatException e) {
                rend.setDisable(true);
                numeroerror.setText("Le numéro de dossier doit être un nombre valide.");
            }
        }
        return existe;
    }
    boolean chercher_rendez(KeyEvent event)
    {
        numeroerror1.setText("");
        String num = num_dossier.getText();
        boolean existe = false;
        int numero=0;
        if (rend.hasProperties())
        {



        }else
        {

        }
        return existe;
    }


    private void resetErrorMessages()
    {
        numeroerror.setText("");
    }

    @FXML
    void creerfiche(MouseEvent event)
    {
        resetErrorMessages();
        String num = num_dossier.getText();
        Orthophoniste user = OrthophonisteSessionManager.getCurrentOrthophonisteName();

        boolean allFieldsValid = true;

        // Vérifier que le champ nom n'est pas vide
        int numero=0;
        Dossier dossier = null;
        if (num.isEmpty())
        {
            numeroerror.setText("Le champ nom ne doit pas être vide.");
            allFieldsValid = false;

        }else
        {
            try {
                numero= Integer.parseInt(num);
                dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);

                if(dossier == null) {
                    numeroerror.setText("Le numéro de dossier n'existe pas!.");
                    allFieldsValid = false;

                }

            } catch (NumberFormatException e) {
                numeroerror.setText("Le numéro de dossier doit être un nombre valide.");
                allFieldsValid = false;
            }
        }

        // Vérifier que la date est fournie

        if (allFieldsValid)
        {
            numero= Integer.parseInt(num);
            dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
            if(dossier.getFiches_suivi().size()!=0)
            {
                Fiche_suivi last_fiche =dossier.getFiches_suivi().getLast();
                if(last_fiche.isDone())
                {
                    afficherMessageSucces("les objectifs de la derniere fiche de suivi  ce patient sont  atteint ,Veillez creer une nouvelle Fiche de suivi");

                    System.out.println("User chose to continue");

                }
                else
                {
                    //afficherMessagesuivi("les objectifs de la derniere fiche de suivi  ce patient ne sont pas atteint vous pouvez l'evaluer dans la page precedente   Est ce que vous etes sure de vouloir creer une nouvelle fiche de suivi ");

                    creerfiche.setDisable(true);
                    num_dossier.setDisable(true);
                    System.out.println(listeobject);
                    for (Objectif objectif : last_fiche.getObjectifs())
                    {

                            try
                            {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/com/example/tp_poo/evaobj.fxml"));
                                HBox hBox = fxmlLoader.load();
                                evaluerobjectifController cic = fxmlLoader.getController();
                                cic.setData(objectif);
                                listeobject.getChildren().add(hBox);

                            } catch (IOException e)
                            {
                                throw new RuntimeException(e);
                            }
                    }
                }

            }else
            {
                afficherMessagewarning("Il ya pas une Fiche de suivi  pour ce patient ,creer une nouvelle fiche de suivi dans la page précédente");
            }
        }
    }
    @FXML
    public void enregistrer(ActionEvent event)
    {

        VBox vbox = listeobject;
        boolean hasError = false;

        // Use ArrayList to temporarily store Objectif objects
        List<Objectif> objectifsList = new ArrayList<>();

        for (int i = 0; i < vbox.getChildren().size(); i++)
        {
            HBox hbox = (HBox) vbox.getChildren().get(i);
            AnchorPane nomObjectifFieldanch = (AnchorPane) hbox.getChildren().get(0);
                Label nomObjectifField= (Label) nomObjectifFieldanch.getChildren().get(0);
            Label typeObjectifComboBox =(Label) nomObjectifFieldanch.getChildren().get(2);
            ComboBox<String> note =(ComboBox<String>)nomObjectifFieldanch.getChildren().get(1);
            CheckBox atteint =(CheckBox) nomObjectifFieldanch.getChildren().get(3);
            Label error=(Label) nomObjectifFieldanch.getChildren().get(4);

            if (nomObjectifField.getText().trim().isEmpty() || note.getValue() == null || note.getValue().trim().isEmpty()) {
                error.setText("erreur");
                hasError = true;
            } else {
                error.setText(""); // Clear previous error message
            }

            if (!hasError) {
                int note1 = Integer.parseInt(note.getValue().trim());
                boolean atteindre = atteint.isSelected();
                String nomObjectif = nomObjectifField.getText().trim();
                String typeObjectif = typeObjectifComboBox.getText().trim();

                System.out.println(nomObjectif);
                System.out.println(typeObjectif);

                Type_objectif type = null;
                switch (typeObjectif) {
                    case "COURT_TERME":
                        type = Type_objectif.COURT_TERME;
                        break;
                    case "MOYEN_TERME":
                        type = Type_objectif.MOYEN_TERME;
                        break;
                    case "LONG_TERME":
                        type = Type_objectif.LONG_TERME;
                        break;
                    default:
                        // Handle unexpected type values (optional)
                        break;
                }
                Objectif objectif = new Objectif(nomObjectif, type, note1, atteindre);
                objectifsList.add(objectif);
            }
        }

        // If there's any validation error, stop further execution
        if (hasError) {
            return;
        }

        Objectif[] objectifsArray = objectifsList.toArray(new Objectif[0]);
        Fiche_suivi ficheSuivi = new Fiche_suivi(objectifsArray);

        int numero= Integer.parseInt(num_dossier.getText());
        Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
        dossier.getFiches_suivi().add(ficheSuivi);

        Rendez_vous Rend= dossier.findRendezVous(rend.getValue());
        if (Rend != null && Rend instanceof Suivi){

           Fiche_suivi ficheSuivi1= ((Suivi) dossier.findRendezVous(Rend)).getFiche_seance();
           dossier.getFiches_suivi().remove(ficheSuivi1);
            ((Suivi) dossier.findRendezVous(Rend)).setFiche(ficheSuivi);


        }

        afficherMessageSucces("Evaluation sauvegarder avec succes");
        String PageRouter = "/com/example/tp_poo/CreerFichesuivi.fxml";
        try {

            Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
            Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void handleRouting(MouseEvent event)
    {

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
                PageRouter = "/com/example/tp_poo/BO.fxml";
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

        if (newPage)
        {
            try
            {
                Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
                Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
                javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
                Scene.setScene(scene);

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void retour(ActionEvent event) {
        try {
            String PageRouter = "/com/example/tp_poo/CreerFichesuivi.fxml";
            // Load the desired page
            Parent nextPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(PageRouter)));
            Stage Scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
            javafx.scene.Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void afficherMessageSucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        // Remove the default icon
        alert.setGraphic(null);
        // Optional: apply a custom icon (comment out if you don't want any icon)
        Image iconImage = new Image(getClass().getResourceAsStream("/images/Dossier/utilisateur.png"));
        // Create an ImageView for the icon and set its size
        ImageView customIcon = new ImageView(iconImage);
        customIcon.setFitWidth(32);  // Set the desired width
        customIcon.setFitHeight(32); // Set the desired height
        customIcon.setPreserveRatio(true); // Preserve the aspect ratio

        // Set the custom icon in the alert
        alert.setGraphic(customIcon);

        // Showing the alert
        dialogPane.setPrefSize(400, 200); // Set the preferred width and height


        alert.getDialogPane().getStylesheets().add(getClass().getResource("/directory/design.css").toExternalForm());


        ButtonType buttonTypeContinue = new ButtonType("Continue");

        // Add buttons to the alert
        alert.getButtonTypes().setAll(buttonTypeContinue);

        // Show the alert and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeContinue) {
            // Handle the continue action



        }



    }
    private void afficherMessagewarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        // Remove the default icon
        alert.setGraphic(null);
        // Optional: apply a custom icon (comment out if you don't want any icon)
        Image iconImage = new Image(getClass().getResourceAsStream("/images/Dossier/utilisateur.png"));
        // Create an ImageView for the icon and set its size
        ImageView customIcon = new ImageView(iconImage);
        customIcon.setFitWidth(32);  // Set the desired width
        customIcon.setFitHeight(32); // Set the desired height
        customIcon.setPreserveRatio(true); // Preserve the aspect ratio

        // Set the custom icon in the alert
        alert.setGraphic(customIcon);

        // Showing the alert
        dialogPane.setPrefSize(400, 200); // Set the preferred width and height


        alert.getDialogPane().getStylesheets().add(getClass().getResource("/directory/design.css").toExternalForm());


        ButtonType buttonTypeContinue = new ButtonType("Continue");

        // Add buttons to the alert
        alert.getButtonTypes().setAll(buttonTypeContinue);

        // Show the alert and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeContinue)
        {
            // Handle the continue action



        }

    }


    private void affichercreatesucces(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();


        // Remove the default icon
        alert.setGraphic(null);

        // Optional: apply a custom icon (comment out if you don't want any icon)
        Image iconImage = new Image(getClass().getResourceAsStream("/images/Dossier/utilisateur.png"));

        // Create an ImageView for the icon and set its size
        ImageView customIcon = new ImageView(iconImage);
        customIcon.setFitWidth(32);  // Set the desired width
        customIcon.setFitHeight(32); // Set the desired height
        customIcon.setPreserveRatio(true); // Preserve the aspect ratio

        // Set the custom icon in the alert
        alert.setGraphic(customIcon);

        // Showing the alert
        dialogPane.setPrefSize(400, 200); // Set the preferred width and height


        alert.getDialogPane().getStylesheets().add(getClass().getResource("/directory/design.css").toExternalForm());

        ButtonType buttonTypeContinue = new ButtonType("Continue");

        alert.getButtonTypes().setAll(buttonTypeContinue);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeContinue) {}

    }
    private void afficherMessagesuivi(String message)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerte");
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();

        Image iconImage = new Image(getClass().getResourceAsStream("/images/Dossier/utilisateur.png"));

        // Create an ImageView for the icon and set its size
        ImageView customIcon = new ImageView(iconImage);
        customIcon.setFitWidth(32);  // Set the desired width
        customIcon.setFitHeight(32); // Set the desired height
        customIcon.setPreserveRatio(true); // Preserve the aspect ratio

        // Set the custom icon in the alert
        alert.setGraphic(customIcon);

        // Showing the alert
        dialogPane.setPrefSize(400, 200); // Set the preferred width and height


        alert.getDialogPane().getStylesheets().add(getClass().getResource("/directory/design.css").toExternalForm());




        ButtonType buttonTypeContinue = new ButtonType("Continue",ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);


        // Add buttons to the alert
        alert.getButtonTypes().setAll(buttonTypeContinue, buttonTypeCancel);

        // Show the alert and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeContinue) {

            creerfiche.setDisable(true);
            num_dossier.setDisable(true);
            System.out.println(listeobject);
            try
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/tp_poo/ajoutobjec.fxml"));
                HBox hBox = fxmlLoader.load();
                ajouterobjectifController cic = fxmlLoader.getController();
                cic.setData(listeobject);
                listeobject.getChildren().add(hBox);

            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        } else {



            try
            {
                String PageRouter = "/com/example/tp_poo/CreerFichesuivi.fxml";
                // Load the desired page
                FXMLLoader fxmlLoader = new FXMLLoader(tpApplication.class.getResource("CreerFichesuivi.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 670);
                Stage stage = (Stage) enregistrer.getScene().getWindow();

                stage.setResizable(false);
                stage.setTitle("TP POO!");
                stage.setScene(scene);
                stage.show();

            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }




        }
    }



}
