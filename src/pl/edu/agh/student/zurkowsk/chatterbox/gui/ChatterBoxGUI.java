package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChatterBoxGUI extends JFrame {

    private ChatActionBar actionBar;

    private ChatRoomsListPanel chatRoomsListPanel;

    private SenderPanel senderPanel;

    private JTextArea messagesTextArea;

    public ChatterBoxGUI()
    {
        initGUI();
    }

    private void initGUI()
    {
        actionBar          = new ChatActionBar();
        chatRoomsListPanel = new ChatRoomsListPanel();
        senderPanel        = new SenderPanel();

        messagesTextArea = new JTextArea();
        messagesTextArea.setEnabled(false);

        add(actionBar,          BorderLayout.NORTH);
        add(chatRoomsListPanel, BorderLayout.WEST);
        add(senderPanel,        BorderLayout.SOUTH);
        add(messagesTextArea,   BorderLayout.CENTER);

        pack();

        setSize(1000, 700);
        setTitle("ChatterBox");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void addCreateButtonListener(ActionListener listener)
    {
        actionBar.createButton.addActionListener(listener);
    }

    public void addJoinButtonListener(ActionListener listener)
    {
        actionBar.joinButton.addActionListener(listener);
    }

    public void addLeaveButtonListener(ActionListener listener)
    {
        actionBar.leaveButton.addActionListener(listener);
    }
}
