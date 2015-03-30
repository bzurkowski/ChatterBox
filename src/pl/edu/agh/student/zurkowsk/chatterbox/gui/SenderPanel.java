package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SenderPanel extends JPanel {

    JButton sendButton;

    JTextField messageTextField;

    public SenderPanel()
    {
        initPanel();
    }

    private void initPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        messageTextField = new JTextField();

        sendButton = new JButton("Send");

        messageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    sendButton.doClick();
                    messageTextField.setText(null);
                }
            }
        });

        add(messageTextField);
        add(sendButton);
    }

}
