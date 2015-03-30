package pl.edu.agh.student.zurkowsk.chatterbox.controllers;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;
import pl.edu.agh.student.zurkowsk.chatterbox.handlers.*;

public class ChatterBox {

    private ChatGUI gui;

    private ChatClient client;

    public ChatterBox(ChatGUI gui, ChatClient client)
    {
        this.gui    = gui;
        this.client = client;

        initEventListeners();
    }

    private void initEventListeners()
    {
        gui.addCreateButtonListener(new CreateActionHandler(gui, client));
        gui.addJoinButtonListener  (new JoinActionHandler(gui, client));
        gui.addLeaveButtonListener (new LeaveActionHandler(gui, client));

        gui.addChatRoomListSelectionListener(new ChatSelectionHandler(gui, client));

        gui.addSendButtonListener(new SendActionHandler(gui, client));

        client.addChatRoomObserver(new ChatMessageHandler(gui, client));
    }
}
