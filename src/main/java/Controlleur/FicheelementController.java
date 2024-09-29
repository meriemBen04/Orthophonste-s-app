package Controlleur;

import Model.Fiche_suivi;
import Model.Objectif;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FicheelementController {

    @FXML
    private Label numfiche;

    @FXML
    private Label objectif;

    @FXML
    private Label typeobj;
    @FXML
    private Label att;

    @FXML
    private Label note;
    public void setData(Fiche_suivi fiche,Objectif obj ,int numero) {
        numfiche.setText(String.valueOf(fiche.getNum()));
        objectif.setText(obj.getNom());
        typeobj.setText(String.valueOf(obj.getType()));
        if (obj.isObjectifatteint()) {
            att.setText("Oui");
        } else
        {       att.setText("Non");}
        note.setText(String.valueOf(obj.getEvaluation()));
        numfiche.setText(String.valueOf(numero));

    }







}
