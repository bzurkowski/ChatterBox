package pl.edu.agh.student.zurkowsk.chatterbox;

import pl.edu.agh.student.zurkowsk.chatterbox.client.ChatClient;
import pl.edu.agh.student.zurkowsk.chatterbox.controllers.ChatterBox;
import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to ChatterBox!");

        String nickname = JOptionPane.showInputDialog(null, "Enter your nickname:");

        ChatGUI gui = new ChatGUI();
        ChatClient client = new ChatClient(nickname);

        ChatterBox chatterBox = new ChatterBox(gui, client);

        gui.setVisible(true);
    }
}
