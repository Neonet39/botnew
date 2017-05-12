package Baza;

import Structur.Structur;
import SystemSendMesseg.SystemSendMesseg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 12.04.2017.
 */
public class Base {
    private static volatile Map<String,String> Base = new HashMap<>();
    private static volatile List<String> Pre_Base = new ArrayList<>();



    public static synchronized void setBase(Map<String, String> base) {
        Base.putAll(base);
    }

    public static synchronized void setPre_Base(String pre_Base) {
        Pre_Base.add(pre_Base);
    }
    public static synchronized void DeletePre_Base(int i){
        Pre_Base.remove(i);
    }
    public static synchronized void Comunication(){
        int size = Pre_Base.size();
        if(size>1){
            if(size%2!=0) size--;
            for (int i=0;i<size;i+=2){
                String one = Pre_Base.get(0);
                String two = Pre_Base.get(1);
                Base.put(one,two);
                DeletePre_Base(0);
                DeletePre_Base(0);
                SystemSendMesseg systemSendMesseg = new SystemSendMesseg();
                SystemSendMesseg.OkStart(one);
                SystemSendMesseg systemSendMesseg1 = new SystemSendMesseg();
                SystemSendMesseg.OkStart(two);
            }
        }

    }



    public static synchronized void AddBase(String one,String two){
        Base.put(one,two);
    }

    public static synchronized boolean SereachBase(String id){
        for(Map.Entry<String,String> map:Base.entrySet()){
            if(map.getKey().equals(id)||map.getValue().equals(id)){
                return true;

            }
        }
        return false;

    }

    public static synchronized boolean SereachPre_Base(String id){
        for(String list:Pre_Base){

            if(list.equals(id))
                return true;
        }
        return false;
    }
    public static synchronized String id(Structur structur){
        for(Map.Entry<String,String> map:Base.entrySet()){
            if(map.getKey().equals(structur.getOwner_id())){
                return map.getValue();
            }
            else if(map.getValue().equals(structur.getOwner_id())){
                return map.getKey();
            }
        }
        return "";
    }

    public static synchronized void RemoveBase(String id){
        SystemSendMesseg systemSendMesseg = new SystemSendMesseg();
        String Interrupting = id;
        String Interrupted = "";
        String key = "";
        for(Map.Entry<String,String> map:Base.entrySet()){
            if(map.getKey().equals(id)||map.getValue().equals(id)){
                if(map.getKey().equals(id)){
                    Interrupted = map.getValue();
                }else{
                    Interrupted = map.getKey();
                }
             key = map.getKey();
             break;
            }
        }
        Base.remove(key);
        systemSendMesseg.Interrupted(Interrupted);
        systemSendMesseg.Interrupting(Interrupting);
    }

    public static synchronized void RemovePre_Base(String id){
        SystemSendMesseg systemSendMesseg = new SystemSendMesseg();
        int i=-1;
        for(String list:Pre_Base){
            i++;
            if(list.equals(id)){
                Pre_Base.remove(i);
                SystemSendMesseg.Sereach_Pre_Base(id);
                break;
            }
        }

    }



}
