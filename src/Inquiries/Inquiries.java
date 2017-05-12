package Inquiries;

import Convert.Convert;

import javax.swing.*;
import java.beans.Encoder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Created by Admin on 08.04.2017.
 */
public class Inquiries extends Thread {
    private String result = "";
    private String inquireis = "";
    private static String key = "";
    private static String groop ="";


    public static void setGroop(String groop) {
        Inquiries.groop = groop;
    }

    public static void setKey(String key){
        Inquiries.key = key;

    }

    public void Check(String id){
        this.inquireis = "https://api.vk.com/method/groups.isMember?v=5.63&access_token="+this.key+"&group_id="+this.groop+"&user_id="+id+"&extended=0";
    }

    public void Remarka(String peer_id){
        this.inquireis = "https://api.vk.com/method/messages.markAsRead?v=5.63&access_token="+this.key+"&peer_id="+peer_id+"&message_ids=0";//;+peer_id;
    }

    public void GetDialogs(){
        this.inquireis  = "https://api.vk.com/method/messages.getDialogs?v=5.41&access_token="+this.key+"&count=200&offset=0&unread=1&start_message_id=0";
    }


    public void GetMessage(String id,String count,String idmes){
        this.inquireis = "https://api.vk.com/method/messages.getHistory?&peer_id="+id+"&count="+count+"&start_message_id="+Integer.parseInt(idmes+1)+"&access_token="+this.key+"&v=5.63";

        //this.inquireis = "https://api.vk.com/method/messages.getHistory?v=5.41&access_token="+this.key+";
    }

    public void SystemSendMessage(String id,String messeg){

        this.inquireis = "https://api.vk.com/method/messages.send?v=5.63&access_token="+this.key+"&user_id="+id+"&message="+messeg+"&peer_id=-"+this.groop;

    }
    public void SendMesseg(String id,String messeg,String attach) {


        try {
            messeg =  URLEncoder.encode(messeg, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
       //
        if(attach.equals(""))
        this.inquireis = "https://api.vk.com/method/messages.send?user_id="+id+"&message="+messeg+"&peer_id=-"+this.groop+"&access_token="+this.key+"&v=5.63";

        else
            this.inquireis = "https://api.vk.com/method/messages.send?user_id="+id+"&message="+messeg+"&peer_id=-"+this.groop+"&attachment="+attach+"&access_token="+this.key+"&v=5.63";
        //    this.inquireis = "https://api.vk.com/method/messages.send?v=5.63&access_token="+this.key+"&user_id="+id+"&message="+messeg+"&peer_id=-"+this.groop+;
    }

    public String getResult()
    {
        return this.result;
    }

    public void run() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;

        try {
            url = new URL(this.inquireis);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {

        }

    }
}



