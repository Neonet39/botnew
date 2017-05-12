package Messeg;

import Convert.Convert;
import List.List;
import Inquiries.Inquiries;
import Structur.StructurDialogs;
import com.sun.javafx.geom.transform.Identity;
import Error.Error;
import java.lang.reflect.Array;
import java.util.ArrayList;



/**
 * Created by Admin on 08.04.2017.
 */
public class GetMesseg extends Thread{
    Identity identity;
    Convert convert = new Convert();
    ArrayList<StructurDialogs> messeg;
    ArrayList<Inquiries> getmesseg = new ArrayList<>();

    public void run(){
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            messeg = List.GetDialogs();
            if(messeg.size()>0) {
             //   System.out.println(messeg.get(0).getUser_id());


                for(int i=0;i<messeg.size();i++){

                    Inquiries identitymesseg = new Inquiries();
                    identitymesseg.GetMessage(messeg.get(i).getUser_id(),messeg.get(i).getCount(),String.valueOf(Integer.parseInt(messeg.get(i).getMesseg_id())-(Integer.parseInt(messeg.get(i).getCount())-1)));
                    identitymesseg.start();
                    while (identitymesseg.isAlive()){}
                    System.out.println(identitymesseg.getResult());
                     if(!new Error().exep(identitymesseg.getResult())) {
                         List.AddGetMesseg(convert.MessegConvert(identitymesseg.getResult()));

                     } else {
                         try {
                             Thread.sleep(1000);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                         i--;
                    }
                    identitymesseg = null;
                }

                 List.DeleteDialogs(messeg.size());

            }

        }
    }
}
