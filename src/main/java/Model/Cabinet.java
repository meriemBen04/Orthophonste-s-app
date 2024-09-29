package Model;

import java.io.Serializable;

public class Cabinet implements Serializable {

    private  Orthophoniste [] orthophoniste;

    public Cabinet(Orthophoniste[] orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    public Orthophoniste[] getOrthophoniste() {
        return orthophoniste;
    }

    public void setOrthophoniste(Orthophoniste[] orthophoniste) {
        this.orthophoniste = orthophoniste;
    }
}
