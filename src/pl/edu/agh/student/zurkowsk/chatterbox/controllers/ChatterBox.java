package pl.edu.agh.student.zurkowsk.chatterbox.controllers;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;
import pl.edu.agh.student.zurkowsk.chatterbox.handlers.*;

public class ChatterBox {

    private ChatGUI gui;

    private ChatClient client;

    public ChatterBox(ChatGUI gui, ChatClient client) throws Exception {
        this.client = client;
        this.gui    = gui;

        gui.setUser(client.getNickname());

        initEventListeners();

        client.start();
    }

    private void initEventListeners()
    {
        client.addChatRoomObserver(new ChatRoomHandler(gui, client));

        gui.addCreateButtonListener(new CreateActionHandler(gui, client));
        gui.addJoinButtonListener  (new JoinActionHandler(gui, client));
        gui.addLeaveButtonListener (new LeaveActionHandler(gui, client));

        gui.addChatRoomListSelectionListener(new ChatSelectionHandler(gui, client));

        gui.addSendButtonListener(new SendActionHandler(gui, client));
    }
}
