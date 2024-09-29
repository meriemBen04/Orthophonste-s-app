package Model;

public class Question_enfant  extends  Question_anamnese {


    private   Categorie_Question_Enfant type_enonce;

    public Question_enfant(String enonce, Categorie_Question_Enfant type_enonce) {
        super(enonce);
        this.type_enonce = type_enonce;
    }

    public Categorie_Question_Enfant getType_enonce() {
        return type_enonce;
    }

    public void setType_enonce(Categorie_Question_Enfant type_enonce) {
        this.type_enonce = type_enonce;
    }


}
