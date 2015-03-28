package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import javax.swing.*;

public class SenderPanel extends JPanel {

    private JButton sendButton;

    private JTextField messageTextField;

    public SenderPanel()
    {
        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        messageTextField = new JTextField();

        sendButton = new JButton("Send");

        add(messageTextField);
        add(sendButton);
    }

}
