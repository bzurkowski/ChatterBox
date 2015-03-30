package pl.edu.agh.student.zurkowsk.chatterbox.handlers;


import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendActionHandler extends ChatActionHandler implements ActionListener, Runnable {

    public SendActionHandler(ChatGUI gui, ChatClient client) {
        super(gui, client);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(this).start();
    }

    @Override
    public void run() {
        String messageContent = gui.getMessageContent();
        client.sendMessage(messageContent);
    }
}
