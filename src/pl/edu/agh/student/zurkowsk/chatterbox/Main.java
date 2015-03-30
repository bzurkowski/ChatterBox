package pl.edu.agh.student.zurkowsk.chatterbox;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.controllers.ChatterBox;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to ChatterBox!");

        ChatGUI gui = new ChatGUI();
        ChatClient client = new ChatClient("Testowy2");

        ChatterBox chatterBox = new ChatterBox(gui, client);

        gui.setVisible(true);
    }
}
