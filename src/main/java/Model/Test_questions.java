package Model;

public class Test_questions extends Test {
    private Question[] questions;
    private int nbr_questions; // a revoir

    public Test_questions(String nom, int capacité, Question[] questions) {
        super(nom, capacité);
        this.questions = questions;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
