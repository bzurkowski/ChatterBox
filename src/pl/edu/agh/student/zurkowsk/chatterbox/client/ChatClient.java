package pl.edu.agh.student.zurkowsk.chatterbox.client;

import org.jgroups.JChannel;
import org.jgroups.Message;

import java.util.HashMap;
import java.util.Map;

import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatMessage;

public class ChatClient {

    private Map<String, JChannel> chatRooms = null;

    private JChannel currentChannel = null;

    private String nickname;

    public ChatClient(String nickname)
    {
        this.nickname = nickname;

        chatRooms = new HashMap<String, JChannel>();
    }

    public void joinChatRoom(String chatRoomName) throws Exception {
        if (chatRooms.containsKey(chatRoomName)) {
            System.out.println("Already joined chat room: '" + chatRoomName + "'");
        } else {
            JChannel channel = ChannelFactory.buildChannel(nickname, chatRoomName, null);
            chatRooms.put(chatRoomName, channel);
            channel.connect(chatRoomName);
        }
    }

    public void leaveChatRoom(String chatRoomName)
    {
        if (chatRooms.containsKey(chatRoomName)) {
            JChannel channel = chatRooms.get(chatRoomName);
            channel.close();
            chatRooms.remove(chatRoomName);
        } else {
            System.out.println("Chat room '" + chatRoomName + "' is not connected yet");
        }
    }

    public void sendMessage(String chatRoomName, String messageContent)
    {
        if (chatRooms.containsKey(chatRoomName)) {
            JChannel channel = chatRooms.get(chatRoomName);

            ChatMessage message = ChatMessage.newBuilder()
                    .setMessage(messageContent).build();

            byte[] messageBytes = message.toByteArray();

            try {
                channel.send(new Message(null, null, messageBytes));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Chat room '" + chatRoomName + "' is not connected yet");
        }
    }

    public JChannel getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(String chatRoomName) {
        JChannel currentChannel = chatRooms.get(chatRoomName);

        if (currentChannel != null) {
            this.currentChannel = currentChannel;
        }
    }
}
