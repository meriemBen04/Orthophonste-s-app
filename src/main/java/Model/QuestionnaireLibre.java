package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class QuestionnaireLibre implements Serializable
{
    private String titre;
    private question_libre Question[];
    private int capacite;

    public QuestionnaireLibre(String titre, question_libre[] question, int capacite) {

        this.titre = titre;
        Question = question;
        this.capacite = capacite;

    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public question_libre[] getQuestion() {
        return Question;
    }

    public void setQuestion(question_libre[] question) {
        Question = question;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
