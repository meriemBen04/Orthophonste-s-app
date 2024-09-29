package Model;

import javafx.scene.control.TextField;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation extends Rendez_vous implements Serializable {
    private String nom;
    private String prenom;
    private int Age;
    private String duree;

    public Consultation(LocalDate date, LocalTime heure, Type_rendez_vous type, String nom, String prenom, int age, String duree) {
        super(date, heure, type);
        this.nom = nom;
        this.prenom = prenom;
        Age = age;
        this.duree = duree;
    }

    public Consultation(LocalDate now, LocalTime heure, Type_rendez_vous typeRendezVous, String observation, String duree)
    {
        super(now, heure, typeRendezVous, observation);
        this.duree=duree;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return Age;
    }

    public String getDuree() {
        return duree;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getPatientName() {
        return this.nom +" "+ this.prenom;
    }

}