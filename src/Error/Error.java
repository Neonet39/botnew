package Error;

import SystemSendMesseg.SystemSendMesseg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 20.04.2017.
 */
public  class Error {
    public boolean exep(String text){
        Pattern pattern = Pattern.compile("(?<=\"error_code\":)[\\w\\W]*?(?=,)");
        Matcher matcher = pattern.matcher(text);
        String textres = text.substring(1,8);

        if(textres.equals("\"error\"")) {
            System.out.println("oshib");
            String result = "";
            while (matcher.find()){
                result = text.substring(matcher.start(),matcher.end());
                break;
            }
            if(result.equals("6")) return true;else return false;
        }else return false;
    }
}
