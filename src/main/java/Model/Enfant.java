package Model;

import java.time.LocalDate;

public class Enfant  extends  Patient
{
    private String class_etude;
    private int numeroparent [];

    public Enfant(String nom, String prenom, LocalDate date_naissance, String lieu_naissance, String adresse, String class_etude, int[] numeroparent) {
        super(nom, prenom, date_naissance, lieu_naissance, adresse);
        this.class_etude = class_etude;
        this.numeroparent = numeroparent;
    }
    public Enfant(String nom, String prenom,int num) {
        super(nom, prenom,num);

    }

    public Enfant() {

    }

    public String getClass_etude() {
        return class_etude;
    }

    public void setClass_etude(String class_etude) {
        this.class_etude = class_etude;
    }

    public int[] getNumeroparent() {
        return numeroparent;
    }

    public void setNumeroparent(int[] numeroparent) {
        this.numeroparent = numeroparent;
    }
}
