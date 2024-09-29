package Controlleur;

import Model.Fiche_suivi;
import Model.Objectif;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class evaluerobjectifController {

    @FXML
    private Label nomobjc;

    @FXML
    private Label nomobjerreur;

    @FXML
    private Label nomobjerreur1;

    @FXML
    private Label nomobjerreur11;

    @FXML
    private ComboBox<String> noteobj;

    @FXML
    private CheckBox objc_atteint;

    @FXML
    private Label typeobjc;


    public void setData(Objectif obj)
    {

        ObservableList<String> options = FXCollections.observableArrayList(
                "1", "2", "3","4","5"
        );
        noteobj.setItems(options);
        nomobjc.setText(obj.getNom());
        typeobjc.setText(String.valueOf(obj.getType()));


    }



}
