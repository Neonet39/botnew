import Checking.Checking;
import Dialog.Dialog;
import Inquiries.Inquiries;
import Messeg.GetMesseg;
import Messeg.SendMesseg;
import SystemSendMesseg.SystemSendMesseg;
import Error.Error;

import java.io.IOException;

/**
 * Created by Admin on 08.04.2017.
 */
public class Main {
    public static void main(String[] ags) throws IOException, InterruptedException {



        SystemSendMesseg systemSendMesseg = new SystemSendMesseg();

        Inquiries inquiries = new Inquiries();
        Dialog dialog = new Dialog();
        GetMesseg getMesseg = new GetMesseg();
        Checking checking = new Checking();
        SendMesseg sendMesseg = new SendMesseg();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       // String key = reader.readLine();
     //   String idgroop = reader.readLine();
     //   String pass = reader.readLine();
        inquiries.setKey("0f021b4f88454597ab08e7edd685e15926b432d8ad7a7d1a2fdb67a933e5849b3a1a0623841f2ff2d9b3");
        inquiries.setGroop("124403657");

        dialog.start();
        getMesseg.start();
        checking.start();
        sendMesseg.start();
        System.out.println("Ok!");
       }







    }

