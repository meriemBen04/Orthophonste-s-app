package Model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public    class  Patient implements Serializable {


    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    private String lieu_naissance;
    private String Adresse;
    private int num_dossier;//numero dossier

    public Patient(String nom, String prenom , int num) {

        this.nom = nom;
        this.prenom =prenom;
        this.num_dossier = num;
    }

    public int getNum_dossier() {
        return num_dossier;
    }

    public void setNum_dossier(int num_dossier) {
        this.num_dossier = num_dossier;
    }

    public Patient(String nom, String prenom, LocalDate date_naissance, String lieu_naissance, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.lieu_naissance = lieu_naissance;
        this.Adresse = adresse;

    }


    public int calcul_age() {
        LocalDate birthDate =this.date_naissance;
        if (birthDate == null) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être nulle");
        }

        LocalDate currentDate = LocalDate.now();

        if (birthDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être dans le futur");
        }

        return Period.between(birthDate, currentDate).getYears();
    }

    public Patient()
    {

    }

   // public Dossier getDossier() {
    //    return dossier;
    //}

//    public void setDossier(Dossier dossier) {
//        this.dossier = dossier;
//    }

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

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getLieu_naissance() {
        return lieu_naissance;
    }

    public void setLieu_naissance(String lieu_naissance) {
        this.lieu_naissance = lieu_naissance;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }
}
