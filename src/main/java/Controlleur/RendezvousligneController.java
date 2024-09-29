package Controlleur;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class RendezvousligneController
{

    @FXML
    private Label date;

    @FXML
    private Label deroulement;

    @FXML
    private Label duree;

    @FXML
    private Label heure;

    @FXML
    private ImageView imageplus;

    @FXML
    private Label info1;

    @FXML
    private Label info2;

    @FXML
    private Label objecetifseance;

    @FXML
    private Label observation;

    @FXML
    private Label thematiaue;

    @FXML
    private Label typeobj;

    @FXML
    private Label typerend;

    @FXML
    private Button ajouter_obs;

    private  Rendez_vous rend;

    @FXML
    void ajouter_obs(ActionEvent event) {
        String obs = observation_text.getText();

        if(!obs.isEmpty()){
            this.rend.setObservation(obs);
            ajouter_obs.setVisible(false);
            imageplus.setVisible(false);
            observation_text.setVisible(false);
            observation.setText(rend.getObservation());
        }


    }
    @FXML
    private TextArea observation_text;

    public void setData(Rendez_vous rend)
    {            this.rend =rend;

        if(rend instanceof Consultation)
        {
            thematiaue.setVisible(false);
            objecetifseance.setVisible(false);
            deroulement.setVisible(false);
            info1.setVisible(false);
            info2.setVisible(false);
            if(rend.getObservation() == null){
                ajouter_obs.setVisible(true);
                observation_text.setVisible(true);
                imageplus.setVisible(true);


            }else {
                ajouter_obs.setVisible(false);

                observation_text.setVisible(false);

                imageplus.setVisible(false);
            }

            date.setText(String.valueOf(rend.getDate()));
            //date.setText("22/12/2024");
            heure.setText(String.valueOf(rend.getHeure()));

            duree.setText(((Consultation)rend).getDuree());
            typerend.setText(String.valueOf(rend.getType()));
            observation.setText(rend.getObservation());


        }
        if(rend instanceof Suivi)
        {
            thematiaue.setVisible(false);
            objecetifseance.setVisible(true);
            deroulement.setVisible(true);
            info1.setVisible(true);
            info2.setVisible(true);
            date.setText(String.valueOf(rend.getDate()));
            //date.setText("22/12/2024");
            if(rend.getObservation() == null){
                ajouter_obs.setVisible(true);
                observation_text.setVisible(true);
                imageplus.setVisible(true);


            }else {
                ajouter_obs.setVisible(false);

                observation_text.setVisible(false);

                imageplus.setVisible(false);
            }
            heure.setText(String.valueOf(rend.getHeure()));
            duree.setText(((Suivi)rend).getDuree());
            typerend.setText(String.valueOf(rend.getType()));
            observation.setText(rend.getObservation());
//            info1.setText(String.valueOf(((Suivi)rend).getType_suivi()));
//            Objectif[] obj =((Suivi)rend).getObjectif_seance();


        }
        if(rend instanceof Atelier)
        {

            thematiaue.setVisible(true);
            objecetifseance.setVisible(false);
            deroulement.setVisible(false);
            info1.setVisible(true);
            info2.setVisible(false);
            date.setText(String.valueOf(rend.getDate()));
            if(rend.getObservation() == null){
                ajouter_obs.setVisible(true);
                observation_text.setVisible(true);
                imageplus.setVisible(true);


            }else {
                ajouter_obs.setVisible(false);

                observation_text.setVisible(false);

                imageplus.setVisible(false);
            }
            //date.setText("22/12/2024");
            heure.setText(String.valueOf(rend.getHeure()));
            duree.setText(((Atelier)rend).getDuree());
            typerend.setText(String.valueOf(rend.getType()));
            observation.setText(rend.getObservation());
            info1.setText(String.valueOf(((Atelier)rend).getThematique()));

        }

    }
}
