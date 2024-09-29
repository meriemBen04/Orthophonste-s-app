package Model;

import java.io.Serializable;

public class Trouble implements Serializable {

    private String nom_trouble;
    private Categorie_trouble categorie;

    public Trouble(String nom_trouble, Categorie_trouble categorie)
    {

        this.nom_trouble = nom_trouble;
        this.categorie = categorie;

    }

    public String getNom_trouble() {
        return nom_trouble;
    }

    public void setNom_trouble(String nom_trouble) {
        this.nom_trouble = nom_trouble;
    }

    public Categorie_trouble getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie_trouble categorie) {
        this.categorie = categorie;
    }
}
