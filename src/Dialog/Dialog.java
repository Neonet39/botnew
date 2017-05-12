package Dialog;

import Convert.Convert;
import Inquiries.Inquiries;
import List.List;
import Structur.StructurDialogs;

import java.util.ArrayList;
import Error.Error;
/**
 * Created by Admin on 08.04.2017.
 */
public class Dialog extends Thread {
    private Inquiries inquiries;
    private Convert convert = new Convert();
    private Inquiries reader;

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
            inquiries = new Inquiries();
            inquiries.GetDialogs();
            inquiries.start();
            while (inquiries.isAlive()) {
            }
            if (!new Error().exep(inquiries.getResult()) && !inquiries.getResult().equals("") && !inquiries.getResult().equals("{\"response\":{\"count\":0,\"items\":[]}}")) {



                    ArrayList<StructurDialogs> arrayList = (convert.DialogConvert(inquiries.getResult()));

                    List.AddDialog(arrayList);
                    for (StructurDialogs remark : arrayList) {
                        reader = new Inquiries();
                        reader.Remarka(remark.getUser_id());
                        // System.out.println(remark.getMesseg_id());
                        reader.start();
                        try {
                            reader.join();
                        } catch (InterruptedException e) {

                        }
                    }


                    inquiries = null;
                    reader = null;
                }
            }catch(Exception e){}
        }
    }
}
