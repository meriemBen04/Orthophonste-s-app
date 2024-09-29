package Model;

import java.io.Serializable;

public class Diagnostique implements Serializable
{

    private Trouble[] trouble;

    public Diagnostique(Trouble[] trouble)
    {
        this.trouble = trouble;
    }

    public Trouble[] getTrouble() {
        return trouble;
    }

    public void setTrouble(Trouble[] trouble) {
        this.trouble = trouble;
    }
}
