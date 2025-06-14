package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI_BasePage extends JPanel {

    private final JButton newButton;

    public GUI_BasePage() {
        
        // Layout setup
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x27282c));

        // Creation of top panel including search bar and buttons
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
        topBar.setBackground(new Color(0x27282c));

        // Creation of search field
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Dialog", Font.PLAIN, 30));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(Color.DARK_GRAY);

        // Creation of search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        searchButton.setForeground(new Color(0xFF5F1F));
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setFocusPainted(false);

        // Creation of new recipe button
        newButton = new JButton("New");
        newButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        newButton.setForeground(new Color(0xFF5F1F));
        newButton.setBackground(Color.DARK_GRAY);
        newButton.setFocusPainted(false);

        // Add buttons and fields with specific order to create appropriate bar
        topBar.add(searchField);
        topBar.add(searchButton);
        topBar.add(Box.createHorizontalGlue());
        topBar.add(newButton);
        this.add(topBar, BorderLayout.NORTH);
    }

    public JButton getNewButton() {
        return newButton;
    }
}
