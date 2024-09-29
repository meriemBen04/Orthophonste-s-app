package Model;

public class BO_1 extends BO
{

    private Anamnese anamnese;
    private Boolean  prise_en_charge;

    public BO_1(Epreuve_clinique[] epreuves_cliniques, Diagnostique diagnostique, Projet_therapeu projet, Anamnese anamnese, Boolean prise_en_charge) {
        super(epreuves_cliniques, diagnostique, projet);
        this.anamnese = anamnese;
        this.prise_en_charge = prise_en_charge;
    }

    public BO_1() {

    }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

    public Boolean getPrise_en_charge() {
        return prise_en_charge;
    }

    public void setPrise_en_charge(Boolean prise_en_charge) {
        this.prise_en_charge = prise_en_charge;
    }
}
