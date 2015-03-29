package pl.edu.agh.student.zurkowsk.chatterbox.handlers;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaveActionHandler extends ChatActionHandler implements ActionListener, Runnable {

    private String chatRoomName;

    public LeaveActionHandler(ChatGUI gui, ChatClient client) {
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
        client.leaveChatRoom(chatRoomName);
    }
}
