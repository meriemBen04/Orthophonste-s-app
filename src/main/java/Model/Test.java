package Model;

import java.io.Serializable;

public  abstract class Test implements Serializable {
    private String nom;
    private int capacite;
    private String compte_rendu;

    public Test() {
    }

    public Test(String nom, int capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    public String getNom() {
        return nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getCompte_rendu() {
        return compte_rendu;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setCompte_rendu(String compte_rendu) {
        this.compte_rendu = compte_rendu;
    }

}
