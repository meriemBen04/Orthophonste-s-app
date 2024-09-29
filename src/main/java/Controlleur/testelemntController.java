package Controlleur;

import Model.Exercice;
import Model.Question;
import Model.Test_questions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class testelemntController
{

    @FXML
    public Label consigne;

    @FXML
    public Label m;

    @FXML
    public Label mat;

    @FXML
    public Label n;

    @FXML
   public Label nb;

    @FXML
   public Label nbv;

    @FXML
   public Label note;

    @FXML
   public Label reponse;
    @FXML
   public Label rpview;

    public void setData(Exercice ex)
    {

        reponse.setVisible(false);
        rpview.setVisible(false);
        consigne.setText(ex.getConsign());
        mat.setText(ex.getMateriel());
        nb.setText(String.valueOf(ex.getNbr_repeter()));
        note.setText(String.valueOf(ex.getNote()));



    }
    public void setData1(Question q)
    {
        consigne.setText(q.getEnonce());
     mat.setVisible(false);
     nb.setVisible(false);
     note.setVisible(false);
     m.setVisible(false);
     nbv.setVisible(false);
     n.setVisible(false);
     //reponse.setText(q.getReponse());




    }







}
