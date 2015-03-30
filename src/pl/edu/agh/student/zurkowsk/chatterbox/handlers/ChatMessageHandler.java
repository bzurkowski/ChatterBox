package pl.edu.agh.student.zurkowsk.chatterbox.handlers;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatReceivedMessage;
import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatRoom;
import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatRoomObserver;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

public class ChatMessageHandler extends ChatActionHandler implements ChatRoomObserver {

    public ChatMessageHandler(ChatGUI gui, ChatClient client) {
        super(gui, client);
    }

    @Override
    public void messageReceived(String channelName, ChatReceivedMessage message) {
        ChatRoom currentChatRoom = client.getCurrentChatRoom();

        if (currentChatRoom == null) return;

        if (currentChatRoom.getChannelName().equals(channelName)) {
            gui.addMessage(message);
        }
    }
}
