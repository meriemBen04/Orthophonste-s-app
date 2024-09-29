package Controlleur;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class anamnelementController {

    @FXML
    private Label categ;

    @FXML
    private Label consigne;

    @FXML
    private Label question;

    public  void setData(Question_anamnese question, int nbr)
    {
        this.consigne.setText(question.getEnonce());
        if (question instanceof Question_enfant){
            categ.setText(((Question_enfant) question).getType_enonce().toString());
            System.out.println(((Question_enfant) question).getType_enonce().toString());
        }else if(question instanceof Question_adulte) {
            categ.setText(((Question_adulte) question).getType_enonce().toString());
            System.out.println(((Question_adulte) question).getType_enonce().toString());
        }
        this.question.setText("Question "+ String.valueOf(nbr) +": ");
    }

}
