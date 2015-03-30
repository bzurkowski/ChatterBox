package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ChatGUI extends JFrame {

    private ChatActionBar actionBar;

    private ChatRoomList chatRoomList;

    private UserList userList;

    private SenderPanel senderPanel;

    private MessagesArea messagesArea;

    public ChatGUI()
    {
        initGUI();
    }

    private void initGUI()
    {
        actionBar    = new ChatActionBar();
        chatRoomList = new ChatRoomList();
        userList     = new UserList();
        senderPanel  = new SenderPanel();
        messagesArea = new MessagesArea();

        add(actionBar,    BorderLayout.NORTH);
        add(chatRoomList, BorderLayout.WEST);
        add(userList,     BorderLayout.EAST);
        add(senderPanel,  BorderLayout.SOUTH);
        add(messagesArea, BorderLayout.CENTER);

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

    public void addSendButtonListener(ActionListener listener)
    {
        senderPanel.sendButton.addActionListener(listener);
    }

    public void addChatRoomListSelectionListener(ListSelectionListener listener)
    {
        chatRoomList.addListSelectionListener(listener);
    }

    public String getMessageContent()
    {
        return senderPanel.messageTextField.getText();
    }

    public String requestChatRoomName()
    {
        return JOptionPane.showInputDialog(this, "Enter a name for new chat room:");
    }

    public String getSelectedChatRoomName()
    {
        Object selectedValue = chatRoomList.getSelectedValue();

        if (selectedValue != null) {
            return selectedValue.toString();
        }
        return null;
    }

    public void addChatRoom(String chatRoomName)
    {
        chatRoomList.addChatRoom(chatRoomName);
    }

    public void removeChatRoom(String chatRoomName)
    {
        chatRoomList.removeChatRoom(chatRoomName);
    }

    public void updateUserList(List<String> usernames)
    {
        userList.updateUsers(usernames);
    }
}
