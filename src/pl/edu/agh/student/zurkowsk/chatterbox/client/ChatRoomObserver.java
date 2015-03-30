package pl.edu.agh.student.zurkowsk.chatterbox.client;

public interface ChatRoomObserver {

    public void messageReceived(String channelName, ChatReceivedMessage message);

    public void stateChanged();
}
