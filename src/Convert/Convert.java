package Convert;

import Structur.Structur;
import Structur.StructurDialogs;
import SystemSendMesseg.SystemSendMesseg;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 08.04.2017.
 */
public class Convert {
    public static String edit(String text){
        //text = text.replaceAll("\\\","//");
       text = text.replaceAll("\\\\/","/");
        text = text.replaceAll("\\\\n","\n");

        return text;
    }
    public ArrayList<StructurDialogs> DialogConvert(String text) {
        ArrayList<StructurDialogs> listmas = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        Pattern parent = Pattern.compile("\"user_id\":[0-9]{1,},\"read_state\":");
        Pattern patt = Pattern.compile("\"unread\":[0-9]{1,}");
        Pattern id = Pattern.compile("(?<=\"message\":\\{\"id\":)[\\w\\W]*?(?=,)");
        Matcher m1 = patt.matcher(text);
        Matcher m = parent.matcher(text);
        Matcher mid = id.matcher(text);
        while (mid.find()) {
            list2.add(text.substring(mid.start(), mid.end()));

        }
        while (m.find()) {
            list.add(text.substring(m.start() + 10, m.end() - 14));
        }

        while (m1.find()) {
            list1.add(text.substring(m1.start() + 9, m1.end()));
        }

        for (int i = 0; i < list.size(); i++) {
            listmas.add(new StructurDialogs(list.get(i), list1.get(i), list2.get(i)));

        }
        return listmas;
    }

    public ArrayList<Structur> MessegConvert(String text) {
        ArrayList<Structur> structurs = new ArrayList<Structur>();

        String[] messeg = text.split("body");
        for (String mess : messeg) {
            // System.out.println(mess);
            if (!UserID(mess).equals("")) {
                // System.out.println(UserID(mess));
                structurs.add(new Structur(UserID(mess), Date(mess), BodyText(mess), Attach(mess,UserID(mess))));
            }
        }
        return structurs;
    }

    public boolean CheckGroop(String text) {
        Pattern pattern = Pattern.compile("(?<=\\{\"response\":)[\\w\\W]*?(?=})");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            text = text.substring(matcher.start(), matcher.end());
            break;
        }
        if (text.equals("0")) return false;
        else return true;
    }

    private String BodyText(String text) {
        String result = "";
        Pattern bodytext = Pattern.compile("(?<=\":\")[\\w\\W]*?(?=\",)");
        Matcher matcher = bodytext.matcher(text);
        while (matcher.find()) {
            result = text.substring(matcher.start(), matcher.end());
            break;
        }
        return result;
    }

    private String UserID(String text) {
        String Result = "";
        Pattern pattern = Pattern.compile("(?<=\"user_id\":)[\\w\\W]*?(?=,)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            Result = text.substring(matcher.start(), matcher.end());
            break;
        }
        return Result;
    }

    private String Date(String text) {
        String result = "";
        Pattern pattern = Pattern.compile("(?<=\"date\":)[\\w\\W]*?(?=,)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            result = text.substring(matcher.start(), matcher.end());
            break;
        }
        return result;
    }

    private String Attach(String text,String id) {
        try {
        String evrika = "";
        String attacments = "";
        String regex_attachments = "(?<=\"attachments\":)[\\w\\W]*?(?=])";
        Map<String, String> regex = new HashMap<String, String>() {{
            put("regex_Type", "(?<=\"type\":\")[\\w\\W]*?(?=\")");
            put("regex_Owner_id", "(?<=\"owner_id\":)[\\w\\W]*?(?=,)");
            put("regex_id", "(?<=\"id\":)[\\w\\W]*?(?=,)");
            put("regex_asses_key", "(?<=\"access_key\":\")[\\w\\W]*?(?=\")");
        }};
        Map<String, String> mapAtt = new HashMap<String, String>();
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(regex_attachments);
        matcher = pattern.matcher(text);

        while (matcher.find()) {
            attacments = (text.substring(matcher.start() - 11, matcher.end()));
            break;
        }
//        System.out.println("sdf"+text);
        if (!attacments.equals("")) {

            //  System.out.println(attacments);
            String[] CollectionsAttch = attacments.split("}}");
            for (String collectionattch : CollectionsAttch) {
                mapAtt.clear();
                for (Map.Entry<String, String> map : regex.entrySet()) {
                    pattern = Pattern.compile(map.getValue());
                    matcher = pattern.matcher(collectionattch);
                    while (matcher.find()) {
                        mapAtt.put(map.getKey(), collectionattch.substring(matcher.start(), matcher.end()));
                     //   System.out.println(map.getKey() + " " + collectionattch.substring(matcher.start(), matcher.end()));
                        break;
                    }
                }
                if (mapAtt.get("regex_Type").equals("photo") || mapAtt.get("regex_Type").equals("audio") || mapAtt.get("regex_Type").equals("video")) {
                    evrika += mapAtt.get("regex_Type") + mapAtt.get("regex_Owner_id") + "_" + mapAtt.get("regex_id") + "_" + mapAtt.get("regex_asses_key") + ",";
                } else continue;

            }

        } else return "";

            evrika = evrika.replaceAll("_null", "");
            evrika = evrika.substring(0, evrika.length() - 1);
            System.out.println(evrika);
            return evrika;
        } catch (Exception e) {
           SystemSendMesseg.ErrorOtach(id);
        }

        return "";
    }
}