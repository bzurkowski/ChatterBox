package pl.edu.agh.student.zurkowsk.chatterbox.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatReceivedMessage {

    private String username;

    private String content;

    private Date date;

    public ChatReceivedMessage(String username, String content)
    {
        this.username = username;
        this.content  = content;
        this.date     = new Date();
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return "[" + dateFormat.format(date) + "] " + username + ": " + content + "\n";
    }
}
