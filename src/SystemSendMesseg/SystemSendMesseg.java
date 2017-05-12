package SystemSendMesseg;

import java.net.URLEncoder;

        import Inquiries.Inquiries;
import List.List;
import Structur.Structur;
import Inquiries.NewDermoIdea;

import java.util.ArrayList;

/**
 * Created by Admin on 12.04.2017.
 */
public class SystemSendMesseg  {

    private  String mess;
    private static ArrayList<String> arrayList = new ArrayList<>();



    public static synchronized void StandarMesseg(String id){
        String text = ("Системное оповещение:\nЧтобы начать общаться, ты должен на меня подписаться!\nhttps://vk.com/bot_kitoss");
        SendSystemMesseg(id,text);

    }
    public static synchronized  void ErrorOtach(String id){
        String text ="Системное оповещение :\nЯ не умею отправлять, стикеры, документы, пустые сообщения,пересланные сообщения";
        SendSystemMesseg(id,text);
    }

    public static synchronized  void OkStart(String id){
        String text = "Системное оповещение:\nСобеседник найден!\nМожете приступать к диалогу";
        SendSystemMesseg(id,text);
    }
    public static synchronized  void ErroSystemStart(String id){
        String text = "Системное оповещение:\nВ данный момент вам доступна команда \"!стоп\"";
        SendSystemMesseg(id,text);
    }

    public static synchronized  void ErroSystemPre_Base(String id){
        String text = "Системное оповещение:\nВ данный момент происходит поиск собеседника\nВы можете прекратить поиск, прислав команду \"!стоп\"";
        SendSystemMesseg(id,text);
    }

    public static synchronized  void Interrupted(String id){
        String text = "Системное оповещение:\nВаш собеседник прервал общение.\nВведите команду \"!старт\",что бы найти нового собеседника.";
        SendSystemMesseg(id,text);
    }

    public  static synchronized  void Interrupting(String id){
        String text = "Системное оповещение:\nВы прервали беседу, что бы найти нового собеседника введите команду \"!старт\"";
        SendSystemMesseg(id,text);
    }

    public static synchronized  void Sereach_Pre_Base(String id){
        String text = "Системное оповещение:\nВы прервали поиск собеседника,что бы заново запустить поиск введите команду \"!старт\"";
        SendSystemMesseg(id,text);
    }
    public static synchronized   void Errror(String id){
        String text = "Системное оповещение:\nДля того что бы найти собеседника, введите команду \"!старт\"";
        SendSystemMesseg(id,text);
    }

    private static synchronized void  SendSystemMesseg(String id,String messeg){

       // String mess = URLEncoder.encode(messeg);
        List.AddSendMesseg(new Structur(id,"",messeg,""));






    }

}
