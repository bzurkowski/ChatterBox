package pl.edu.agh.student.zurkowsk.chatterbox;

import pl.edu.agh.student.zurkowsk.chatterbox.gui.ChatterBoxGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {

    public Test()
    {

    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to ChatterBox!");

        Test test = new Test();

        ChatterBoxGUI chatterBoxGUI = new ChatterBoxGUI();
        chatterBoxGUI.setVisible(true);

        chatterBoxGUI.addCreateButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create button clicked!");
            }
        });
    }

}
