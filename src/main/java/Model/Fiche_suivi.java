package Model;

import java.io.Serializable;

public class Fiche_suivi implements Serializable {
    static int counter ;
    private int num;
    private Objectif[] objectifs;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Fiche_suivi.counter = counter;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Fiche_suivi()
    {
        counter++;
        num = counter;
    }

    public Fiche_suivi(Objectif[] objectifs) {
        this.objectifs = objectifs;
        counter++;
        num = counter;

    }

    public void setObjectifs(Objectif[] objectifs) {
        this.objectifs = objectifs;
    }

    public Objectif[] getObjectifs()
    {
        return objectifs;
    }
    public boolean isDone() {
        if (objectifs != null)
        {
            for (Objectif objectif : objectifs) {
                if (!objectif.isObjectifatteint()) {
                    return false;
                }
            }
        }
        return true;
    }


}
