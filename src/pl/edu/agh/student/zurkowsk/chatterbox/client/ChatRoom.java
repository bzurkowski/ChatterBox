package pl.edu.agh.student.zurkowsk.chatterbox.client;

import com.google.protobuf.InvalidProtocolBufferException;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatAction;
import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatMessage;

public class ChatRoom extends ReceiverAdapter {

    private ChatClient client = null;

    private JChannel channel = null;

    private String channelName;

    private List<ChatAction> state = null;

    private List<ChatReceivedMessage> messages = null;

    private List<String> users = null;


    public ChatRoom(ChatClient client, String channelName) throws Exception
    {
        this.client = client;

        this.channelName = channelName;

        state = new LinkedList<ChatAction>();
        users = new ArrayList<String>();

        messages = new LinkedList<ChatReceivedMessage>();

        channel = ChannelFactory.buildChannel(channelName, channelName, this);
    }

    public boolean join(String username) throws Exception
    {
        if (channel != null && !channel.isConnected()) {
            channel.connect(channelName);
            users.add(username);
            return true;
        }
        return false;
    }

    public boolean leave(String username)
    {
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
            users.remove(username);
            return true;
        }
        return false;
    }

    public void sendMessage(String username, String messageContent)
    {
        ChatMessage message = ChatMessage.newBuilder()
                .setMessage(messageContent).build();

        byte[] messageBytes = message.toByteArray();

        try {
            channel.send(new Message(null, null, messageBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receive(Message msg) {
        ChatMessage chatMessage = null;
        try {
            chatMessage = ChatMessage.parseFrom(msg.getRawBuffer());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        if (chatMessage == null) return;

        String messageContent = chatMessage.getMessage();
        String username = "Unknown";

        ChatReceivedMessage message = new ChatReceivedMessage(username, messageContent);
        messages.add(message);

        client.notifyMessageReceived(channelName, message);
    }

    public void addUser(String username)
    {
        if (!users.contains(username)) {
            users.add(username);
            client.notifyStateChanged();
        }
    }

    public void removeUser(String username)
    {
        users.remove(username);
        client.notifyStateChanged();
    }

    public List<String> getUsers() {
        return users;
    }

    public List<ChatAction> getState() {
        return state;
    }

    public List<ChatReceivedMessage> getMessages() {
        return messages;
    }

    public String getChannelName() {
        return channelName;
    }
}
