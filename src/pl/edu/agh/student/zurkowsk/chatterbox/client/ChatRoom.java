package pl.edu.agh.student.zurkowsk.chatterbox.client;

import org.jgroups.JChannel;
import org.jgroups.Message;
import pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatAction;
import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatMessage;

public class ChatRoom {

    private JChannel channel;

    private String channelName;

    private List<ChatAction> state;

    private List<String> users = null;

    public ChatRoom(String channelName) throws Exception
    {
        this.channelName = channelName;

        state = new LinkedList<ChatAction>();
        users = new ArrayList<String>();

        channel = ChannelFactory.buildChannel(channelName, channelName, null);
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

    public void sendMessage(String messageContent)
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

    public void addUser(String username)
    {
        if (!users.contains(username)) {
            users.add(username);
        }
    }

    public void removeUser(String username)
    {
        users.remove(username);
    }

    public List<String> getUsers() {
        return users;
    }

    public List<ChatAction> getState() {
        return state;
    }
}
