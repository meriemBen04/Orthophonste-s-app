package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Suivi extends Rendez_vous
{
    private int numero_dossier;
    private Deroulement_seance type;
    private Fiche_suivi fiche;
    private final String duree = "1:00";


    public Suivi(LocalDate date, LocalTime heure, Type_rendez_vous type, int numero_dossier, Deroulement_seance type1, String duree)
    {
        super(date, heure, type);
        this.numero_dossier = numero_dossier;
        this.type = type1;
    }
    public Suivi(LocalDate date)
    {
        super(date) ;
    }
    public Suivi() {

    }

    public Suivi(LocalDate now1, LocalTime heure, Type_rendez_vous typeRendezVous, String observation, int i, Deroulement_seance deroulementSeance, Objectif[] objectifs, String s) {
    }

    public void setNumero_dossier(int numero_dossier) {
        this.numero_dossier = numero_dossier;
    }

    public void setType(Deroulement_seance type) {
        this.type = type;
    }

    public void setFiche(Fiche_suivi fiche) {
        this.fiche = fiche;
    }

    public int getNumero_dossier() {
        return numero_dossier;
    }

    public Deroulement_seance getType_suivi() {
        return type;
    }

    public Fiche_suivi getFiche_seance() {
        return fiche;
    }


    public String getDuree() {
        return duree;
    }

    public String getPatientName() {
        // Récupérer l'orthophoniste actuel
        Orthophoniste orthophoniste = OrthophonisteSessionManager.getCurrentOrthophonisteName();

        // Vérifier si le patient avec le numéro de dossier existe
        if (orthophoniste.getMes_patients().containsKey(this.numero_dossier)) {
            // Récupérer le patient
            Patient patient = orthophoniste.getMes_patients().get(this.numero_dossier).getPatient();
            System.out.println(patient.getNom());
            // Retourner le nom complet du patient
            return patient.getNom() + " " + patient.getPrenom();
        }

        // Retourner "nom" si le patient n'existe pas
        return "nom";
    }
  public Patient getPatient(){
      Orthophoniste orthophoniste = OrthophonisteSessionManager.getCurrentOrthophonisteName();
      // Vérifier si le patient avec le numéro de dossier existe
      if (orthophoniste.getMes_patients().containsKey(this.numero_dossier)) {
          // Récupérer le patient
          Patient patient = orthophoniste.getMes_patients().get(this.numero_dossier).getPatient();
          return patient;
      }

      // Retourner "nom" si le patient n'existe pas
      return null;

  }

}
