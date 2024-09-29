package Controlleur;

import Model.Categorie_trouble;
import Model.Trouble;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TroublelementController {

    @FXML
    private Label categorie_trouble;

    @FXML
    private Label nom_trouble;


    public void setData(Trouble trouble)
    {

        nom_trouble.setText(trouble.getNom_trouble()+" :");



       if (trouble.getCategorie()== Categorie_trouble.Deglutition) {
           categorie_trouble.setText(String.valueOf(" Deglutition "));

       }
        if (trouble.getCategorie()== Categorie_trouble.Neuro_developpementaux) {

            categorie_trouble.setText(String.valueOf("Neuro developpementaux "));
        }
        if ( trouble.getCategorie()== Categorie_trouble.Cognitifs) {

            categorie_trouble.setText(String.valueOf("Cognitifs "));
        }



    }





}
