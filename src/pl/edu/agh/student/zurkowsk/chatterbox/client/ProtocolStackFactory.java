package pl.edu.agh.student.zurkowsk.chatterbox.client;

import org.jgroups.protocols.*;
import org.jgroups.protocols.pbcast.*;
import org.jgroups.stack.Protocol;
import org.jgroups.stack.ProtocolStack;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ProtocolStackFactory {

    public static ProtocolStack buildStack(String hostName) throws UnknownHostException {
        ProtocolStack stack = new ProtocolStack();

        Protocol udpProtocol = new UDP();

        udpProtocol.setValue("mcast_group_addr", InetAddress.getByName(hostName));

        stack.addProtocol(udpProtocol)
                .addProtocol(new PING())
                .addProtocol(new MERGE2())
                .addProtocol(new FD_SOCK())
                .addProtocol(new FD_ALL()
                        .setValue("timeout", 12000)
                        .setValue("interval", 3000))
                .addProtocol(new VERIFY_SUSPECT())
                .addProtocol(new BARRIER())
                .addProtocol(new NAKACK())
                .addProtocol(new UNICAST2())
                .addProtocol(new STABLE())
                .addProtocol(new GMS())
                .addProtocol(new UFC())
                .addProtocol(new MFC())
                .addProtocol(new FRAG2())
                .addProtocol(new STATE_TRANSFER())
                .addProtocol(new FLUSH());
        return stack;
    }

}
