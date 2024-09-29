package Model;

import java.io.Serializable;
import java.util.List;

public class QCM extends Question implements Serializable {
    private List<String>choix;
    //private  int nbr_choix;
    private String [] reponse;
    int nbr_reponce;



    public QCM (String enonce, int note, String [] reponse, List<String> choix) {
        super(enonce, note);
        this.choix = choix;

        this.reponse =reponse;
        this.nbr_reponce= reponse.length;

    }

    public QCM() {

    }

    public List<String> getChoix() {
        return choix;
    }

    public void setChoix(List<String> choix) {
        this.choix = choix;
    }

    public String[] getReponse() {
        return reponse;
    }

    public void setReponse(String[] reponse) {
        this.reponse = reponse;
    }
}
