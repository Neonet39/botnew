package List;

import Structur.Structur;
import Structur.StructurDialogs;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by Admin on 08.04.2017.
 */
public class List {

    private static volatile ArrayList<StructurDialogs> Dialogs = new ArrayList<>();
    private static volatile ArrayList<Structur> GetMesseg = new ArrayList<>();
    public static volatile ArrayList<Structur> SendMesseg = new ArrayList<>();

    public static synchronized ArrayList<Structur> GetSendMesseg(){
        return SendMesseg;
    }
    public static synchronized void AddSendMesseg(ArrayList<Structur> o){
        SendMesseg.addAll(o);
    }
    public static synchronized void DeleteSendMesseg(){
        SendMesseg.remove(0);
    }

    public static synchronized void AddGetMesseg(ArrayList<Structur> getMesseg){
        GetMesseg.addAll(getMesseg);
    }

    public static synchronized ArrayList<Structur> GetgetMesseg(){
        return GetMesseg;
    }

    public static synchronized void AddDialog(ArrayList<StructurDialogs> dialogs)
    {
        Dialogs.addAll(dialogs);
    }

    public static synchronized void DeleteDialogs(int i){
        for(int j=0;j<i;j++)
        Dialogs.remove(0);
    }

    public static synchronized void DeleteGetMesseg(){

            GetMesseg.remove(0);
    }

    public static  synchronized ArrayList<StructurDialogs> GetDialogs(){
        return Dialogs;
    }


    public static void AddSendMesseg(Structur structur) {
        SendMesseg.add(structur);
    }
}
