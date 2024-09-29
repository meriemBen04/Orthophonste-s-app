package Model;

public class OrthophonisteSessionManager
{
    private  static Orthophoniste currentOrthophoniste;


    private OrthophonisteSessionManager() {}



    public  static Orthophoniste getCurrentOrthophonisteName()
    {
        return currentOrthophoniste;
    }

    public  static  void setCurrentOrthophonisteName(Orthophoniste orthophonist) {
        currentOrthophoniste = orthophonist;
    }
}
