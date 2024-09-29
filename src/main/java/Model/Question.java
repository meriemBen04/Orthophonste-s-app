package Model;

import java.io.Serializable;

public  abstract class Question implements   Serializable {
    private String enonce;
    private  int note;

    public Question(String enonce, int note) {

        this.enonce = enonce;
        this.note = note;

    }

    public Question() {

    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
