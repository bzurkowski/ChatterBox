package pl.edu.agh.student.zurkowsk.chatterbox.client;

import org.jgroups.JChannel;
import org.jgroups.ReceiverAdapter;
import org.jgroups.stack.ProtocolStack;

public class ChannelFactory {

    public static JChannel buildChannel(String channelName, String hostName, ReceiverAdapter receiver) throws Exception {
        JChannel channel = new JChannel(false);

        channel.setName(channelName);
        channel.setReceiver(receiver);

        ProtocolStack protocolStack = ProtocolStackFactory.buildStack(hostName);

        channel.setProtocolStack(protocolStack);
        protocolStack.init();

        return channel;
    }

}
