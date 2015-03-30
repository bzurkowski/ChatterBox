package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatReceivedMessage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MessagesArea extends JPanel {

    private JTextArea messagesArea;

    public MessagesArea()
    {
        initPanel();
    }

    public void addMessage(ChatReceivedMessage message)
    {
        messagesArea.append(message.toString());
    }

    public void updateMessages(List<ChatReceivedMessage> messages) {
        messagesArea.setText(null);

        for (ChatReceivedMessage message : messages) {
            addMessage(message);
        }
    }

    private void initPanel()
    {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        messagesArea = new JTextArea();
        messagesArea.setEditable(false);

        JScrollPane messagesPane = new JScrollPane(
                messagesArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        add(messagesPane, BorderLayout.CENTER);
    }
}
