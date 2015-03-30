package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatReceivedMessage;

import javax.swing.*;
import java.util.List;

public class MessagesArea extends JTextArea {

    public MessagesArea()
    {
        initArea();
    }

    public void addMessage(ChatReceivedMessage message)
    {
        append(message.toString());
    }

    public void updateMessages(List<ChatReceivedMessage> messages) {
        setText(null);

        for (ChatReceivedMessage message : messages) {
            addMessage(message);
        }
    }

    private void initArea()
    {
        setEnabled(false);
    }
}
