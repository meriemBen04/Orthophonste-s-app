package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Anamnese implements Serializable {

    private List<Question_anamnese> question;

    public Anamnese(List<Question_anamnese> question) {
        this.question = question;
    }

    public Anamnese() {

    }

    public List<Question_anamnese>getQuestion() {
        return question;
    }

    public void setQuestion(ArrayList<Question_anamnese>question)
    {
        this.question = question;
    }



    
}
