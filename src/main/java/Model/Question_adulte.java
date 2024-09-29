package Model;

import java.io.Serializable;

public class Question_adulte  extends Question_anamnese implements Serializable {

    private   Categorie_question_adulte type_enonce;

    public Question_adulte(String enonce, Categorie_question_adulte type_enonce) {
        super(enonce);
        this.type_enonce = type_enonce;
    }

    public Categorie_question_adulte getType_enonce() {
        return type_enonce;
    }

    public void setType_enonce(Categorie_question_adulte type_enonce) {
        this.type_enonce = type_enonce;
    }
}
