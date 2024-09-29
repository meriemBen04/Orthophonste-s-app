package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public abstract class Rendez_vous implements Comparable<Rendez_vous>, Serializable {
    private LocalDate date;
    private LocalTime heure;
    private Type_rendez_vous type;
    private String Observation;

    public Rendez_vous(LocalDate date, LocalTime heure, Type_rendez_vous type) {
        this.date = date;
        this.heure = heure;
        this.type = type;
    }

    public Rendez_vous(LocalDate date) {
        this.date = date;
    }

    protected Rendez_vous() {

    }

    public Rendez_vous(LocalDate date, LocalTime heure, Type_rendez_vous type, String observation) {
        this.date = date;
        this.heure = heure;
        this.type = type;
        this.Observation = observation;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public Enum<Type_rendez_vous> getType() {
        return type;
    }

    public String getObservation() {
        return Observation;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public void setType(Type_rendez_vous type) {
        this.type = type;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }
    @Override
    public int compareTo(Rendez_vous other) {
        // Compare les dates en premier
        int dateComparison = this.date.compareTo(other.getDate());

        if (dateComparison != 0) {
            // Si les dates sont différentes, retourne le résultat de la comparaison des dates
            return dateComparison;
        } else {
            // Si les dates sont les mêmes, compare les heures
            return this.heure.compareTo(other.getHeure());
        }
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rendez_vous that = (Rendez_vous) o;
        return Objects.equals(date, that.date)&&
                Objects.equals(heure, that.heure);
    }
    public abstract String getDuree();

    public abstract String getPatientName();







}
