package pl.edu.agh.student.zurkowsk.chatterbox.handlers;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

public abstract class ChatActionHandler {

    protected ChatGUI gui;

    protected ChatClient client;

    public ChatActionHandler(ChatGUI gui, ChatClient client)
    {
        this.gui = gui;
        this.client = client;
    }
}
