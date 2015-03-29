package pl.edu.agh.student.zurkowsk.chatterbox;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.controllers.ChatterBox;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to ChatterBox!");

        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("java.net.preferIPv6Stack", "false");

        ChatGUI gui = new ChatGUI();
        ChatClient client = new ChatClient("Testowy");

        ChatterBox chatterBox = new ChatterBox(gui, client);

        gui.setVisible(true);
    }
}
