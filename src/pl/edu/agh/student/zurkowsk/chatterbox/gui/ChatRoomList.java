package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

public class ChatRoomList extends JList {

    DefaultListModel<String> listModel;

    public ChatRoomList()
    {
        listModel = new DefaultListModel<String>();

        initList();
    }

    private void initList()
    {
        setModel(listModel);

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(235, 245, 255));
        setPreferredSize(new Dimension(200, 100));
    }

    public void addChatRoom(String chatRoomName) {
        if (!listModel.contains(chatRoomName)) {
            listModel.addElement(chatRoomName);
        }
    }

    public void removeChatRoom(String chatRoomName) {
        listModel.removeElement(chatRoomName);
    }

    public void updateChatRooms(java.util.List<String> chatRoomNames)
    {
        listModel.clear();

        if (chatRoomNames == null) return;

        for (String chatRoomName : chatRoomNames) {
            addChatRoom(chatRoomName);
        }
    }
}
