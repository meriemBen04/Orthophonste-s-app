package Controlleur;

import Model.Test;
import Model.Test_exercice;
import Model.Test_questions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class testbuttomController {

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



    public void setficheData(Test test)
    {

        capacitetest.setText(String.valueOf(test.getCapacite()));
        nomtestes.setText(String.valueOf(test.getNom()));
        compterendu.setText(test.getCompte_rendu());
        if (test instanceof Test_exercice)
        {
            listeq.setVisible(false);
            for (int i = 0; i < ((Test_exercice) test).getListe_excercice().size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/tp_poo/testelemnt.fxml"));
                try {
                    HBox hBox = fxmlLoader.load();
                    testelemntController cic = fxmlLoader.getController();
                    cic.setData(((Test_exercice) test).getListe_excercice().get(i));
                    testelay.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        if (test instanceof Test_questions)
        {
            listex.setVisible(false);
            for (int i = 0; i < ((Test_questions) test).getQuestions().length; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/tp_poo/testelemnt.fxml"));
                try {
                    HBox hBox = fxmlLoader.load();
                    testelemntController cic = fxmlLoader.getController();
                    cic.setData1(((Test_questions) test).getQuestions()[i]);
                    testelay.getChildren().add(hBox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }


    }
}
