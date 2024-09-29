package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Dossier implements Serializable {

    private static int counter ;

    private int numero;
    private Patient patient ;
    private TreeSet<Rendez_vous>  rendez_vous;
    private List<BO> Bilans_orth;
    private ArrayList<Fiche_suivi> fiches_suivi;

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public ArrayList<Fiche_suivi> getFiches_suivi() {
        return fiches_suivi;
    }

    public void setFiches_suivi(ArrayList<Fiche_suivi> fiches_suivi) {
        this.fiches_suivi = fiches_suivi;
    }

    public Dossier(Patient patient)
    {
        this.patient= patient;
        numero = counter+1;
        counter++;
        this.rendez_vous = new TreeSet<Rendez_vous>();
        this.Bilans_orth= new ArrayList<BO>();
        this.fiches_suivi =new ArrayList<Fiche_suivi>();
    }
    public Dossier()
    {
        numero = counter+1;
        counter++;
        this.rendez_vous = new TreeSet<Rendez_vous>();
        this.Bilans_orth= new ArrayList<BO>();
        this.fiches_suivi =new ArrayList<Fiche_suivi>();

    }

    public List<BO> getBilans_orth() {
        return Bilans_orth;
    }

    public void setBilans_orth(List<BO> bilans_orth) {
        Bilans_orth = bilans_orth;
    }

    public Dossier(TreeSet<Rendez_vous> rendezVous, List<BO> bilans_orth, ArrayList< Fiche_suivi>  fiches_suivi , Patient patient    ) {
        this.numero = counter+1;
        counter++;
        this.rendez_vous = new TreeSet<Rendez_vous>(rendezVous);
        Bilans_orth = bilans_orth;
        this.fiches_suivi = fiches_suivi;
        this.patient = patient;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TreeSet<Rendez_vous> getRendez_vous() {
        return rendez_vous;
    }

    public void setRendez_vous(TreeSet<Rendez_vous> rendez_vous) {
        this.rendez_vous = rendez_vous;
    }



    public static String rendezVousToString(Rendez_vous rv) {
        return String.format("%s %s %s", rv.getDate(), rv.getHeure(), rv.getType());
    }

    public void add_rendez_vous(Rendez_vous rd){

        rendez_vous.add(rd);

    }
    public static void setCounter(int n){
        counter = n;
    }

    public static TreeSet<Rendez_vous> filterRendezVous(Set<Rendez_vous> rendezVousSet) {

        LocalDate today = LocalDate.now();

        Comparator<Rendez_vous> comparator = (rendezVousSet instanceof TreeSet) ?
                (Comparator<Rendez_vous>) ((TreeSet<Rendez_vous>) rendezVousSet).comparator() : Comparator.naturalOrder();

        return rendezVousSet.stream()
                .filter(rv -> rv instanceof Suivi )
                .filter(rv -> !rv.getDate().isBefore(today))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }
    public static TreeSet<Rendez_vous> Suivi_sans_fiche(Set<Rendez_vous> rendezVousSet) {

        LocalDate today = LocalDate.now();

        Comparator<Rendez_vous> comparator = (rendezVousSet instanceof TreeSet) ?
                (Comparator<Rendez_vous>) ((TreeSet<Rendez_vous>) rendezVousSet).comparator() : Comparator.naturalOrder();

        return rendezVousSet.stream()
                .filter(rv -> rv instanceof Suivi )
                .filter(rv-> ((Suivi) rv).getFiche_seance()!=null)
                .filter(rv -> !rv.getDate().isBefore(today))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public boolean containsRendezVous(Rendez_vous rendezVous) {
        return rendez_vous.contains(rendezVous);
    }
    public Rendez_vous findRendezVous(Rendez_vous rendezVous) {
        for (Rendez_vous rv : rendez_vous) {
            if (rv.equals(rendezVous)) {
                return rv;
            }
        }
        return null;
    }


}
