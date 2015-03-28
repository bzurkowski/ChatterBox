package pl.edu.agh.student.zurkowsk.chatterbox;

import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatterBoxGUI;

public class Main {

    public static void main(String[] args)
    {
        System.out.println("Welcome to ChatterBox!");

        ChatterBoxGUI chatterBoxGUI = new ChatterBoxGUI();
        chatterBoxGUI.setVisible(true);
    }

}
