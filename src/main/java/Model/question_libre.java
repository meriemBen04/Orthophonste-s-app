package Model;

import java.io.Serializable;

public class question_libre extends Question  implements Serializable {
    private String reponse;



    public question_libre(String enonce, int note, String reponse) {
        super(enonce, note);
        this.reponse = reponse;
    }

    public question_libre() {

    }


    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }


}
