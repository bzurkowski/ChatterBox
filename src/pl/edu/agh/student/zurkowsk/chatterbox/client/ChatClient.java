package pl.edu.agh.student.zurkowsk.chatterbox.client;

import java.util.*;

import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatAction;
import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatAction.ActionType;

public class ChatClient {

    private Map<String, ChatRoom> chatRooms = null;

    private ChatRoom currentChatRoom = null;

    private List<ChatRoomObserver> chatRoomObservers = null;

    private ChatManager chatManager = null;

    private String nickname;


    public ChatClient(String nickname) throws Exception {
        this.nickname = nickname;

        chatRooms         = new HashMap<String, ChatRoom>();
        chatRoomObservers = new LinkedList<ChatRoomObserver>();

        chatManager = new ChatManager(this);
    }

    public void start() throws Exception {
        chatManager.start();
    }

    public ChatRoom createRoom(String chatRoomName) throws Exception {
        if (!chatRooms.containsKey(chatRoomName)) {
            ChatRoom chatRoom = new ChatRoom(this, chatRoomName);
            chatRooms.put(chatRoomName, chatRoom);

            notifyStateChanged();
            return chatRoom;
        }
        return null;
    }

    public void joinChatRoom(String chatRoomName) throws Exception {
        ChatRoom chatRoom;

        if (chatRooms.containsKey(chatRoomName)) {
            chatRoom = chatRooms.get(chatRoomName);
        } else {
            chatRoom = createRoom(chatRoomName);
        }

        if (chatRoom != null) {
            if (chatRoom.join(nickname)) {
                currentChatRoom = chatRoom;
                chatManager.notifyJoin(nickname, chatRoomName);
                notifyStateChanged();
            }

        }
    }

    public void leaveChatRoom(String chatRoomName)
    {
        if (chatRooms.containsKey(chatRoomName)) {
            if (chatRooms.get(chatRoomName).leave(nickname)) {
                chatManager.notifyLeave(nickname, chatRoomName);
                notifyStateChanged();
            }

        }
    }

    public void sendMessage(String messageContent)
    {
        if (currentChatRoom != null) {
            currentChatRoom.sendMessage(nickname, messageContent);
        }
    }


    public synchronized void loadState(List<ChatAction> chatState) throws Exception {
        clearState();

        for (ChatAction chatAction : chatState) {
            ActionType action  = chatAction.getAction();
            String channelName = chatAction.getChannel();

            ChatRoom chatRoom = chatRooms.get(channelName);

            if (chatRoom == null) {
                chatRoom = new ChatRoom(this, channelName);
                chatRooms.put(channelName, chatRoom);
            }

            String username = chatAction.getNickname();

            switch (action) {
                case JOIN:
                    chatRoom.addUser(username);
                    break;
                case LEAVE:
                    chatRoom.removeUser(username);
                    break;
            }
        }

        notifyStateChanged();
    }

    public void notifyStateChanged()
    {
        for (ChatRoomObserver observer : chatRoomObservers) {
            observer.stateChanged();
        }
    }

    public void notifyMessageReceived(String channelName, ChatReceivedMessage message)
    {
        for (ChatRoomObserver observer : chatRoomObservers) {
            observer.messageReceived(channelName, message);
        }
    }

    private void clearState() {
        for (ChatRoom chatRoom : chatRooms.values()) {
            chatRoom.getState().clear();
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

    public void addChatRoomObserver(ChatRoomObserver observer) {
        chatRoomObservers.add(observer);
    }

    public Map<String, ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public List<String> getChatRoomNames()
    {
        List<String> chatRoomNames = new ArrayList<String>();
        chatRoomNames.addAll(chatRooms.keySet());
        return chatRoomNames;
    }

    public String getNickname() {
        return nickname;
    }
}
