package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatRoomsListPanel extends JList {

    public ChatRoomsListPanel()
    {
        initPanel();
    }

    private void initPanel()
    {
        String[] data = {"one", "two", "three", "four"};

        setListData(data);

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(235, 245, 255));
        setPreferredSize(new Dimension(200, 100));
    }
}
