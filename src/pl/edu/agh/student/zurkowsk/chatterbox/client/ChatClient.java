package pl.edu.agh.student.zurkowsk.chatterbox.client;

import org.jgroups.JChannel;

import java.util.HashMap;
import java.util.Map;

public class ChatClient {

    private Map<String, ChatRoom> chatRooms = null;

    private ChatRoom currentChatRoom = null;

    private ChatManager chatManager = null;

    private String nickname;


    public ChatClient(String nickname) throws Exception {
        this.nickname = nickname;

        chatRooms = new HashMap<String, ChatRoom>();

        chatManager = new ChatManager(this);
    }

    public void joinChatRoom(String chatRoomName) throws Exception {
        ChatRoom chatRoom;

        if (chatRooms.containsKey(chatRoomName)) {
            chatRoom = chatRooms.get(chatRoomName);
        } else {
            chatRoom = new ChatRoom(chatRoomName);
            chatRooms.put(chatRoomName, chatRoom);
        }

        if (chatRoom != null) {
            if (chatRoom.join(nickname)) {
                currentChatRoom = chatRoom;
                chatManager.notifyJoin(nickname, chatRoomName);
            }

        }
    }

    public void leaveChatRoom(String chatRoomName)
    {
        if (chatRooms.containsKey(chatRoomName)) {
            if (chatRooms.get(chatRoomName).leave(nickname)) {
                currentChatRoom = null;
                chatManager.notifyLeave(nickname, chatRoomName);
            }

        }
    }

    public void sendMessage(String chatRoomName, String messageContent)
    {
        if (chatRooms.containsKey(chatRoomName)) {
            ChatRoom chatRoom = chatRooms.get(chatRoomName);

            if (chatRoom != null) {
                chatRoom.sendMessage(nickname, messageContent);
            }
        }
    }

    public void sendMessage(String messageContent)
    {
        if (currentChatRoom != null) {
            currentChatRoom.sendMessage(nickname, messageContent);
        }
    }

    public ChatRoom getChatRoom(String chatRoomName) {
        if (chatRooms.containsKey(chatRoomName)) {
            return chatRooms.get(chatRoomName);
        }
        return null;
    }

    public ChatRoom getCurrentChatRoom() {
        return currentChatRoom;
    }

    public void setCurrentChatRoom(ChatRoom currentChatRoom) {
        this.currentChatRoom = currentChatRoom;
    }

    public void setCurrentChatRoom(String chatRoomName) {
        if (chatRooms.containsKey(chatRoomName)) {
            currentChatRoom = chatRooms.get(chatRoomName);
        }
    }

    public String getNickname() {
        return nickname;
    }
}
