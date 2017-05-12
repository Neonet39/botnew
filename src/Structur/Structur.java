package Structur;

/**
 * Created by Admin on 11.04.2017.
 */
public class Structur {
    private String owner_id;
    private String Date;
    private String BodyText;
    private String Attech;

    public Structur(String owner_id, String date, String bodyText, String attech) {
        this.owner_id = owner_id;
        Date = date;
        BodyText = bodyText;
        Attech = attech;
    }

    public void Setowner_id(String text){
        this.owner_id = text;
    }

    public String getOwner_id() {
        return owner_id;
    }



    public String getDate() {
        return Date;
    }

    public String getAttech() {
        return Attech;
    }


    public String getBodyText() {
        return BodyText;
    }
}
