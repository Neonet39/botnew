package Checking;

import Baza.Base;
import Convert.Convert;
import Inquiries.Inquiries;
import List.List;
import Structur.Structur;
import SystemSendMesseg.SystemSendMesseg;


import java.util.ArrayList;

/**
 * Created by Admin on 12.04.2017.
 */
public class Checking extends Thread {
    private ArrayList<Structur> list = new ArrayList<>();
    private ArrayList<Structur> SendMesseg = new ArrayList<>();

    private Inquiries inquiries;
    private Convert convert = new Convert();

    private ArrayList<Structur> Sort(ArrayList<Structur> o){
        Structur structur;
        for(int i=0;i<o.size();i++){
           for (int j=0;j<o.size();j++){

               if(Integer.parseInt(o.get(i).getDate())>Integer.parseInt(o.get(j).getDate())){
                   structur = o.get(j);
                   o.set(j,o.get(i));
                   o.set(i,structur);
               }
           }
        }
        return o;
    }
    private boolean CheckingBase(Structur structur) {

        if (Base.SereachBase(structur.getOwner_id())) {

            if (structur.getBodyText().equals("!старт")) {
               SystemSendMesseg.ErroSystemStart(structur.getOwner_id());
                return true;
            } else if (structur.getBodyText().equals("!стоп")) {
                Base.RemoveBase(structur.getOwner_id());
                return true;
            } else {
                structur.Setowner_id(Base.id(structur));
                SendMesseg.add(structur);
                return true;
            }
        } else if (Base.SereachPre_Base(structur.getOwner_id())) {
            if (structur.getBodyText().equals("!старт")) {
                SystemSendMesseg.ErroSystemPre_Base(structur.getOwner_id());
                return true;
            } else if (structur.getBodyText().equals("!стоп")) {

                Base.RemovePre_Base(structur.getOwner_id());
                return true;
            } else {
                SystemSendMesseg.ErroSystemPre_Base(structur.getOwner_id());
                return true;
            }


        } else return false;
    }

    private void Processing(Structur structur) {
        inquiries = new Inquiries();
        switch (structur.getBodyText()) {
            case "!старт": {
                inquiries.Check(structur.getOwner_id());
                inquiries.start();
                try {
                    inquiries.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (convert.CheckGroop(inquiries.getResult())) {
                    Base.setPre_Base(structur.getOwner_id());
                    SystemSendMesseg.ErroSystemPre_Base(structur.getOwner_id());
                    Base.Comunication();

                } else {
                    SystemSendMesseg.StandarMesseg(structur.getOwner_id());
                }
                inquiries = null;
                break;
            }
            default: {
                   SystemSendMesseg.Errror(structur.getOwner_id());
                break;
            }
        }

    }

    public void run() {
        while (true) {
            if (List.GetgetMesseg().size() > 0) {
                while (List.GetgetMesseg().size() > 0) {
                   // System.out.println(List.GetgetMesseg().get(0).getOwner_id()+" "+List.GetgetMesseg().get(0).getBodyText());
                    if (!CheckingBase(List.GetgetMesseg().get(0))) {
                        Processing(List.GetgetMesseg().get(0));
                    }
                    List.DeleteGetMesseg();
                }
                SendMesseg = Sort(SendMesseg);
                List.AddSendMesseg(SendMesseg);
                SendMesseg.clear();
            }
        }
    }
}