package Model;

import java.io.Serializable;

public class BO  implements Serializable
{
    private Epreuve_clinique epreuves_cliniques[];
    private Diagnostique diagnostique;
    private Projet_therapeu projet;

    public BO()
    {


        this.epreuves_cliniques =new Epreuve_clinique[4];
    }

    public BO(Epreuve_clinique[] epreuves_cliniques, Diagnostique diagnostique, Projet_therapeu projet) {
        this.epreuves_cliniques = epreuves_cliniques;
        this.diagnostique = diagnostique;
        this.projet = projet;
    }

    public BO(Projet_therapeu po) {

        this.projet = po;



    }

    public BO(Projet_therapeu po, Epreuve_clinique[] s) {

        this.epreuves_cliniques = s;
        this.projet = po;

    }

    public Epreuve_clinique[] getEpreuves_cliniques() {
        return epreuves_cliniques;
    }

    public void setEpreuves_cliniques(Epreuve_clinique[] epreuves_cliniques) {
        this.epreuves_cliniques = epreuves_cliniques;
    }

    public Diagnostique getDiagnostique() {
        return diagnostique;
    }

    public void setDiagnostique(Diagnostique diagnostique) {
        this.diagnostique = diagnostique;
    }

    public Projet_therapeu getProjet() {
        return projet;
    }

    public void setProjet(Projet_therapeu projet) {
        this.projet = projet;
    }
}
