package Model;

import java.io.Serializable;

public class Exercice  implements Serializable {
    private String consign;
    private int nbr_repeter;
    private String materiel;
    private int note=0;

    public Exercice(String consign, String materiel,int nb) {
        this.consign = consign;
        this.materiel = materiel;
        this.nbr_repeter=nb;
    }

    public Exercice(String consign, String matteriel) {


    }

    public String getConsign() {
        return consign;
    }

    public void setConsign(String consign) {
        this.consign = consign;
    }

    public int getNbr_repeter() {
        return nbr_repeter;
    }

    public void setNbr_repeter(int nbr_repeter) {
        this.nbr_repeter = nbr_repeter;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

}
