package pl.edu.agh.student.zurkowsk.chatterbox.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class ChatActionBar extends JToolBar {

    JButton createButton, joinButton, leaveButton;

    public ChatActionBar()
    {
        initToolBar();
    }

    private void initToolBar()
    {
        setFloatable(false);

        ImageIcon createIcon = new ImageIcon(getIconPath("create"));
        ImageIcon joinIcon   = new ImageIcon(getIconPath("join"));
        ImageIcon leaveIcon  = new ImageIcon(getIconPath("leave"));

        createButton = new JButton("New chat room", createIcon);
        joinButton   = new JButton("Join", joinIcon);
        leaveButton  = new JButton("Leave", leaveIcon);

        Border actionButtonBorder = new EmptyBorder(5, 5, 5, 5);

        createButton.setBorder(actionButtonBorder);
        joinButton.setBorder(actionButtonBorder);
        leaveButton.setBorder(actionButtonBorder);

        add(createButton);
        add(joinButton);
        add(leaveButton);

        setBackground(new Color(238, 238, 238));
    }

    private URL getIconPath(String iconName)
    {
        String iconPath = "../resources/images/" + iconName + ".png";
        return getClass().getResource(iconPath);
    }
}
