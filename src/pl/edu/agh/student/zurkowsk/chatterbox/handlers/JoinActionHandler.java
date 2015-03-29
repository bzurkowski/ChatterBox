package pl.edu.agh.student.zurkowsk.chatterbox.handlers;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinActionHandler extends ChatActionHandler implements ActionListener, Runnable {

    private String chatRoomName;

    public JoinActionHandler(ChatGUI gui, ChatClient client) {
        super(gui, client);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chatRoomName = gui.getSelectedChatRoomName();

        if (chatRoomName != null && !chatRoomName.isEmpty()) {
            new Thread(this).start();
        }

    }

    @Override
    public void run() {
        try {
            client.joinChatRoom(chatRoomName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
