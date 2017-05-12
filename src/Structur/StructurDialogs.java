package Structur;

/**
 * Created by Admin on 11.04.2017.
 */
public class StructurDialogs {
    private String user_id;
    private String count;
    private String messeg_id;

    public StructurDialogs(String user_id, String count, String messeg_id) {
        this.user_id = user_id;
        this.count = count;
        this.messeg_id = messeg_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCount() {
        return count;
    }

    public String getMesseg_id() {
        return messeg_id;
    }
}
