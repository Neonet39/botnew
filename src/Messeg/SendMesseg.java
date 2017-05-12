package Messeg;
import Convert.Convert;
import Inquiries.Inquiries;
import List.List;
import Error.Error;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by Admin on 13.04.2017.
 */
public class SendMesseg extends Thread {
    public void run(){
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(List.GetSendMesseg().size()>0){
                    Inquiries inquiries = new Inquiries();

                    inquiries.SendMesseg(List.GetSendMesseg().get(0).getOwner_id(),Convert.edit(List.GetSendMesseg().get(0).getBodyText()),List.GetSendMesseg().get(0).getAttech());
                    inquiries.start();
                try {
                    inquiries.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    System.out.println(inquiries.getResult());
                    if (!new Error().exep(inquiries.getResult()))
                    List.DeleteSendMesseg();
                    else {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                inquiries = null;
                }
            }
        }
    }

