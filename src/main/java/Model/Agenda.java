package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class Agenda  implements Serializable {
    private TreeSet<Rendez_vous>rendez_vous ;
    private int nbr_rdv =0;

    public Agenda() {

        this.rendez_vous = new TreeSet<Rendez_vous>();

    }

    public TreeSet<Rendez_vous> getRendez_vous() {
        return rendez_vous;
    }

    public void setRendez_vous(TreeSet<Rendez_vous> rendez_vous) {
        this.rendez_vous = rendez_vous;
    }

    public void add_rendez_vous(Rendez_vous rdv){

        this.rendez_vous.add(rdv);

    }
    public boolean existe(LocalDate date, LocalTime heure, LocalTime duree) {
        LocalTime heureFin = heure.plusHours(duree.getHour()).plusMinutes(duree.getMinute());

        for (Rendez_vous rv : rendez_vous) {
            if (rv.getDate().equals(date)) {
                LocalTime rvHeureDebut = rv.getHeure();
                LocalTime rvDuree = LocalTime.parse(rv.getDuree());
                LocalTime rvHeureFin = rvHeureDebut.plusHours(rvDuree.getHour())
                        .plusMinutes(rvDuree.getMinute());
                System.out.println("la date ");
                System.out.println("Heure de début du nouveau rendez-vous: " + heure);
                System.out.println("Heure de fin du nouveau rendez-vous: " + heureFin);
                System.out.println("Heure de début du rendez-vous existant: " + rvHeureDebut);
                System.out.println("Heure de fin du rendez-vous existant: " + rvHeureFin);

                if ((heure.isAfter(rvHeureDebut) && heure.isBefore(rvHeureFin)) ||  // Cas 1: Nouveau rendez-vous est entièrement contenu dans un rendez-vous existant
                        (heure.equals(rvHeureDebut))) {
                    return true;
                }
            }
        }
        return false;
    }

}

