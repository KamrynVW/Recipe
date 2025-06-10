package com.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class RecipeMeGUI extends JFrame {

    public RecipeMeGUI() {

        // Color and Card Layout definitions for future use
        Color bg = new Color(0x27282c);
        Color txt = new Color(0xFF5F1F);
        CardLayout lyt = new CardLayout();
        JPanel mainPanel = new JPanel(lyt);

        // Creation of landing panel with vertical organization
        JPanel landPagePanel = new JPanel();
        landPagePanel.setLayout(new BoxLayout(landPagePanel, BoxLayout.Y_AXIS));
        landPagePanel.setBackground(bg);

        // Creation of title label
        JLabel landPageTitle = new JLabel("RecipeMe");
        landPageTitle.setFont(new Font("Dialog", Font.BOLD, 50));
        landPageTitle.setForeground(txt);
        landPageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creation of start button (with event listener defined later)
        JButton landPageStart = new JButton("Start");
        landPageStart.setFont(new Font("Dialog", Font.BOLD, 40));
        landPageStart.setForeground(txt);
        landPageStart.setBackground(Color.DARK_GRAY);
        landPageStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        landPageStart.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(10, 20, 10, 20)));
        landPageStart.setFocusPainted(false);

        // Creation of spacing between title and button for asthetic purposes
        landPagePanel.add(Box.createVerticalGlue());
        landPagePanel.add(landPageTitle);
        landPagePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        landPagePanel.add(landPageStart);
        landPagePanel.add(Box.createVerticalGlue());

        // Adding all panels to the main CardLayout panel
        mainPanel.add(landPagePanel, "landing");

        // General frame setup and addition of main card panel
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(750, 750));
        this.setVisible(true);
        this.add(mainPanel);

        // Action Listener definitions
        landPageStart.addActionListener(e -> lyt.show(mainPanel, "placeholder"));

    }
    
}
