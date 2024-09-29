package Model;

import javafx.scene.control.ComboBox;

import java.io.Serializable;

public class Objectif  implements Serializable {
    private String nom;
    private Type_objectif type;
    private int evaluation ;
    private boolean  objectifatteint;

    public Objectif(String nomObjectif, Type_objectif type, int note, boolean atteindre) {


        this.nom = nomObjectif;
        this.type = type;
        objectifatteint=atteindre;
        evaluation=note;



    }


    public boolean isObjectifatteint() {
        return objectifatteint;
    }

    public void setObjectifatteint(boolean objectifatteint) {
        this.objectifatteint = objectifatteint;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public Objectif(String nom, Type_objectif type) {
        this.nom = nom;
        this.type = type;

        objectifatteint=false;
        evaluation=0;
    }

    public String getNom() {
        return nom;
    }

    public Type_objectif getType() {
        return type;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(Type_objectif type) {
        this.type = type;
    }
}
