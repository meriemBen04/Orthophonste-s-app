package Model;

import java.io.Serializable;

public class Compte implements Serializable {

    private String nom;
    private String prenom;
    private int num_tlf;
    private String email;
    private String Mot_pass;
    private String adresse;


    public Compte(String nom, String prenom, int num_tlf, String email, String mot_pass, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tlf = num_tlf;
        this.email = email;
        Mot_pass = mot_pass;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNum_tlf() {
        return num_tlf;
    }

    public void setNum_tlf(int num_tlf) {
        this.num_tlf = num_tlf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_pass() {
        return Mot_pass;
    }

    public void setMot_pass(String mot_pass) {
        Mot_pass = mot_pass;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
