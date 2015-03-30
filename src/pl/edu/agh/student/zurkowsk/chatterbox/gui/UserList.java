package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class UserList extends JList {

    DefaultListModel<String> listModel;

    public UserList()
    {
        listModel = new DefaultListModel<String>();

        initList();
    }

    private void initList()
    {
        setModel(listModel);

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(242, 242, 235));
        setPreferredSize(new Dimension(200, 100));
    }

    public void addUser(String username)
    {
        if (!listModel.contains(username)) {
            listModel.addElement(username);
        }
    }

    public void removeUser(String username)
    {
        listModel.removeElement(username);
    }

    public void updateUsers(List<String> usernames)
    {
        listModel.clear();

        if (usernames == null) return;

        for (String username : usernames) {
            listModel.addElement(username);
        }
    }
}
