package Controlleur;
import Model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField adresse;

    @FXML
    private TextField email;

    @FXML
    private TextField full_name;

    @FXML
    private TextField number;

    @FXML
    private Button returntologin;

    @FXML
    private TextField sign_upname;

    @FXML
    private Button signup;

    @FXML
    private AnchorPane signupform;

    @FXML
    private PasswordField signuppassword;

    @FXML
    private TextField signupusername;

    @FXML

    public void Loginform()
    {

        try {
            Parent next = FXMLLoader.load(getClass().getResource("/com/example/tp_poo/Login.fxml"));

            // Get the current scene
            Scene currentScene = signup.getScene();

            // Set the root of the current scene to the Step2 root
            currentScene.setRoot(next);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML


    public void Signin() {
        String nom = sign_upname.getText();
        String password = signuppassword.getText();
        String emailfield = email.getText();
        int telephone = Integer.parseInt(number.getText());
        String prenom= full_name.getText();
        String adress = adresse.getText();

        Compte compte=  new Compte(nom, prenom,telephone,  emailfield, password, adress) ;




        // SERIALIZED AUTOMATICALLY IN THE CLASS UTILISATEUR
        Orthophoniste orthophoniste=new Orthophoniste(compte);
        OrthophonisteSessionManager.setCurrentOrthophonisteName(orthophoniste);
        this.Loginform();












    }




}
