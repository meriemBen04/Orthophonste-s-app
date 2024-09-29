package Model;

import java.util.ArrayList;

public class Test_exercice  extends Test{
private ArrayList<Exercice> liste_excercice;
    private String titre;
    private String[][] Exercice;

    public Test_exercice() {
        super();
    }

    public String[][] getExercice()
    {
        return Exercice;
    }

    public void setExercice(String[][] exercice) {
        Exercice = exercice;
    }

    public String getTitre() {
        return titre;
    }




    public void setTitre(String titre) {
        this.titre = titre;
    }



    public Test_exercice(String nom, int capacite, ArrayList<Exercice> liste_excercice) {
        super(nom, capacite);
        this.liste_excercice = liste_excercice;
    }

    public ArrayList<Exercice> getListe_excercice()
    {
        return liste_excercice;
    }

    public void setListe_excercice(ArrayList<Exercice> liste_excercice) {
        this.liste_excercice = liste_excercice;
    }
}
