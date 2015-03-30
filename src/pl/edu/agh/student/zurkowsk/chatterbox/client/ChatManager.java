package pl.edu.agh.student.zurkowsk.chatterbox.client;

import com.google.protobuf.InvalidProtocolBufferException;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatAction;
import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatAction.ActionType;
import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatAction.parseFrom;
import static pl.edu.agh.student.zurkowsk.chatterbox.protos.ChatOperationProtos.ChatState;

public class ChatManager extends ReceiverAdapter {

    private static final String MANAGEMENT_CHANNEL_NAME = "ChatManagement768624";

    private static final int STATE_SYNC_TIMEOUT = 10000;

    private JChannel managementChannel;

    private ChatClient client;

    public ChatManager(ChatClient client) throws Exception
    {
        this.client = client;
    }

    public void start() throws Exception
    {
        managementChannel = ChannelFactory.buildChannel(MANAGEMENT_CHANNEL_NAME, null, this);
        managementChannel.connect(MANAGEMENT_CHANNEL_NAME);
        managementChannel.getState(null, STATE_SYNC_TIMEOUT);
    }

    public void notifyJoin(String username, String channelName)
    {
        sendChatAction(username, channelName, ActionType.JOIN);
    }

    public void notifyLeave(String username, String channelName)
    {
        sendChatAction(username, channelName, ActionType.LEAVE);
    }


    private void sendChatAction(String username, String channelName, ActionType actionType)
    {
        ChatAction chatAction = ChatAction.newBuilder()
                .setNickname(username)
                .setChannel(channelName)
                .setAction(actionType).build();

        byte[] chatActionBytes = chatAction.toByteArray();

        Message message = new Message(null, null, chatActionBytes);

        try {
            managementChannel.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        ChatState.Builder chatState = ChatState.newBuilder();

        Map<String, ChatRoom> chatRooms = client.getChatRooms();

        synchronized (chatRooms) {
            for (ChatRoom chatRoom : chatRooms.values()) {
                chatState.addAllState(chatRoom.getState());
            }
            output.write(chatState.build().toByteArray());
        }
    }

    @Override
    public void setState(InputStream input) throws Exception {
        List<ChatAction> chatState = ChatState.parseFrom(input).getStateList();

        client.loadState(chatState);
    }

    @Override
    public void viewAccepted(View view) {
        super.viewAccepted(view);
    }

    @Override
    public void receive(Message msg) {
        try {
            ChatAction chatAction = parseFrom(msg.getRawBuffer());

            String channelName = chatAction.getChannel();
            String nickname = chatAction.getNickname();

            ChatRoom chatRoom = client.getChatRoom(channelName);

            if (chatRoom == null) {
                chatRoom = client.createRoom(channelName);
            }

            chatRoom.getState().add(chatAction);

            switch(chatAction.getAction()) {
                case JOIN:
                    chatRoom.addUser(nickname);
                    break;
                case LEAVE:
                    chatRoom.removeUser(nickname);
                    break;
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
