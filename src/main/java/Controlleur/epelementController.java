package Controlleur;

import Model.Epreuve_clinique;
import Model.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class epelementController
{
    @FXML
    private Label capacitetest;

    @FXML
    private Label compterendu;

    @FXML
    private Label listeq;

    @FXML
    private Label listex;

    @FXML
    private Label nomtestes;

    @FXML
    private VBox testelay;

    @FXML
    private Label objc;

   public void   setData(Epreuve_clinique ep)
    {
         if (ep != null) {
             String[] obs= ep.getObservations_cliniques();

             objc.setText(obs[0]);

             for (Test test : ep.getTests()) {
                 FXMLLoader fxmlLoader = new FXMLLoader();
                 fxmlLoader.setLocation(getClass().getResource("/com/example/tp_poo/tbutton.fxml"));
                 try {
                     HBox hBox = fxmlLoader.load();
                     tController cic = fxmlLoader.getController();
                     cic.setData(test);
                     testelay.getChildren().add(hBox);
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }
         }

    }
}
