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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class ajouterficheController implements Initializable
{

    @FXML
    private Label creerfiche;

    @FXML
    private Button edit_profile;

    @FXML
    private Button enregistrer;

    @FXML
    private  VBox listeobject;

    @FXML
    private TextField num_dossier;

    @FXML
    private Button retour;

    @FXML
    private Label utilisateur1;

    @FXML
    private Label numeroerror;

    @FXML
    private ComboBox<Rendez_vous> rend;

    @FXML
    private Label errorrend;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Orthophoniste user= OrthophonisteSessionManager.getCurrentOrthophonisteName();
        utilisateur1.setText(user.getCompte().getNom() + " " + user.getCompte().getPrenom());
        rend.setDisable(true);
        errorrend.setText("");
        numeroerror.setText((""));

        // Add listener to the TextField
        num_dossier.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty() ) {
                rend.setDisable(true);
                rend.getItems().clear();
            } else {

                try {
                    int numero = Integer.parseInt(num_dossier.getText());
                    Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
                    if (dossier != null) {
                        Set<Rendez_vous> rendou = dossier.getRendez_vous();
                        updateComboBox(rendou);
                        if (!rend.getItems().isEmpty()) {
                            creerfiche.setDisable(true);// Réactiver le bouton creerfiche si des rendez-vous existent
                            errorrend.setText("Veuillez choisir un rendez-vous");
                        } else {
                            creerfiche.setDisable(false);  // Désactiver si aucun rendez-vous n'est trouvé
                        }
                    }
                    rend.setDisable(false);
                } catch (NumberFormatException e) {
                    numeroerror.setText("Veuillez entrer un numéro de dossier valide.");
                    rend.setDisable(true);
                    rend.getItems().clear();
                }

            }
        });

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
            errorrend.setText("IL N'Y A PAS DE SEANCES DE SUIVI POUR CE PATIENT");
        } else {
            errorrend.setText("");
            numeroerror.setText((""));
            // Filtrer les rendez-vous qui ont des fiches de séance non nulles
            List<Rendez_vous> seancesAvecFiche = filteredRendezVous.stream()
                    .filter(rv -> rv instanceof Suivi && ((Suivi) rv).getFiche_seance() == null)
                    .collect(Collectors.toList());

            if (seancesAvecFiche.isEmpty()) {
                errorrend.setText("Toutes les séances de suivi ont des fiches de suivis.");
            } else {
                errorrend.setText("");
                numeroerror.setText((""));
                rend.getItems().setAll(seancesAvecFiche);
            }
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
            numeroerror.setText("Le champ nom ne doit pas être vide.");
        }else
        {
            try {numero= Integer.parseInt(num);
                Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
                if(dossier != null) {
                    existe = true;
                }else {
                    numeroerror.setText("Le numéro de dossier n'existe pas!.");
                }

            } catch (NumberFormatException e) {
                numeroerror.setText("Le numéro de dossier doit être un nombre valide.");
            }
        }
        return existe;
    }

    private void resetErrorMessages() {
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
             Rendez_vous Rend= (Suivi)dossier.findRendezVous(rend.getValue());
           if(Rend != null) {

            if  ((Rend instanceof Suivi && ((Suivi) Rend).getFiche_seance() != null)) {

                Fiche_suivi last_fiche = ((Suivi) Rend).getFiche_seance();
                if(last_fiche.isDone())
                {
                    afficherMessagesuivi("les objectifs de la fiche de suivi de cette séance de suivi de ce patient sont  atteint vous pouvez les consulter dans le profile du patient  et également creer une nouvelle fiche de suivi pour une nouvelle séance de suivi ");
                    System.out.println("User chose to continue");
                    creerfiche.setDisable(true);
                    num_dossier.setDisable(true);
                    System.out.println(listeobject);

                    /*try
                    { rend.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        creerfiche.setDisable(newValue == null);
                    });
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/com/example/tp_poo/ajoutobjec.fxml"));
                        HBox hBox = fxmlLoader.load();
                        ajouterobjectifController cic = fxmlLoader.getController();
                        cic.setData(listeobject);
                        listeobject.getChildren().add(hBox);

                    } catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }*/
                }
                else
                {
                    afficherMessagesuivi("les objectifs de la derniere fiche de suivi  ce patient ne sont pas atteint vous pouvez l'evaluer dans la page precedente ");
                }

            }else
            {
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


            }
             }
        }
    }
    @FXML
    public void enregistrer(ActionEvent event) {
        VBox vbox = listeobject;

        // Use ArrayList to temporarily store Objectif objects
        List<Objectif> objectifsList = new ArrayList<>();

        for (int i = 0; i < vbox.getChildren().size(); i++)
        {
            HBox hbox = (HBox) vbox.getChildren().get(i);
            AnchorPane nomObjectifFieldanch = (AnchorPane) hbox.getChildren().get(0);
            TextField nomObjectifField= (TextField) nomObjectifFieldanch.getChildren().get(0);
            ComboBox<String> typeObjectifComboBox =(ComboBox<String>)nomObjectifFieldanch.getChildren().get(1);
            //ComboBox<String> typeObjectifComboBox = (ComboBox<String>) hbox.getChildren().get(1);
            System.out.println("hehehehehehhehehhehehehehhehehehe");

            String nomObjectif = nomObjectifField.getText().trim();
            String typeObjectif = typeObjectifComboBox.getValue();
            System.out.println(nomObjectif);
            System.out.println(typeObjectif);

            if (!nomObjectif.isEmpty() && typeObjectif != null)
            {
                Type_objectif type = null;
                switch (typeObjectif) {
                    case "Court Terme":
                        type = Type_objectif.COURT_TERME;
                        break;
                    case "Moyen Terme":
                        type = Type_objectif.MOYEN_TERME;
                        break;
                    case "Long Terme":
                        type = Type_objectif.LONG_TERME;
                        break;
                    default:
                        // Handle unexpected type values (optional)
                        break;
                }

                if (type != null) {
                    Objectif objectif = new Objectif(nomObjectif, type);
                    objectifsList.add(objectif);
                    System.out.println("hehehehehehhehehhehehehehhehehehe");

                }
            }
        }
        Objectif[] objectifsArray = objectifsList.toArray(new Objectif[0]);
        Fiche_suivi ficheSuivi = new Fiche_suivi(objectifsArray);
        int numero= Integer.parseInt(num_dossier.getText());
        Dossier dossier = OrthophonisteSessionManager.getCurrentOrthophonisteName().rechercher_patient(numero);
        dossier.getFiches_suivi().add(ficheSuivi);
        Rendez_vous Rend= dossier.findRendezVous(rend.getValue());
        if (Rend != null){
            ((Suivi) dossier.findRendezVous(Rend)).setFiche(ficheSuivi);
        }

        afficherMessageSucces("Creation de la Fiche de suivi de patient "+dossier.getPatient().getNom() +" "+dossier.getPatient().getPrenom()+" qvec succés" );

        String PageRouter = "/com/example/tp_poo/CreerFichesuivi.fxml";
        try {
            Parent nextPage = FXMLLoader.load(getClass().getResource(PageRouter));
            Stage Scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(nextPage, 1000, 670);
            Scene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Convert List to Objectif array


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




        //ButtonType buttonTypeContinue = new ButtonType("Continue",ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel",ButtonBar.ButtonData.CANCEL_CLOSE);


        // Add buttons to the alert
        alert.getButtonTypes().setAll(buttonTypeCancel);

        // Show the alert and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();
        /*if (result.isPresent() && result.get() == buttonTypeContinue) {

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
        } else
         {

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

         }*/
        if (result.isPresent() && result.get() == buttonTypeCancel){
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
