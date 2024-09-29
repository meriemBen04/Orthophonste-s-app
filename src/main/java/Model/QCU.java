package Model;

import java.io.Serializable;
import java.util.List;

public class QCU extends Question  implements Serializable {
    private  List<String> choix;

  private String  reponse;


    public QCU(String enonce,int note, String reponse, List<String> choix) {

        super(enonce, note);
        this.choix = choix;
       // this.nbr_choix = choix.length;
        this.reponse =reponse;

    }

    public QCU() {
        super();
    }

    public List<String> getChoix() {
        return choix;
    }

    public void setChoix(List<String> choix) {
        this.choix = choix;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

}
