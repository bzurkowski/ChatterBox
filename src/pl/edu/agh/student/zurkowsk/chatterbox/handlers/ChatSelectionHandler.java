package pl.edu.agh.student.zurkowsk.chatterbox.handlers;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatRoom;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChatSelectionHandler extends ChatActionHandler implements ListSelectionListener, Runnable {

    public ChatSelectionHandler(ChatGUI gui, ChatClient client) {
        super(gui, client);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String chatRoomName = gui.getSelectedChatRoomName();

        ChatRoom chatRoom = client.getChatRoom(chatRoomName);

        if (chatRoom != null) {
            gui.updateUserList(chatRoom.getUsers());
            client.setCurrentChatRoom(chatRoom);
        }
    }

    @Override
    public void run() {

    }
}
