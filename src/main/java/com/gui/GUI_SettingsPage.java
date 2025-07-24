package com.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GUI_SettingsPage extends JPanel {

    // Font for subtitles and button invisible text
    private final Font subtitleFont = new Font("Dialog", Font.PLAIN, 20);
    private final JButton backButton;
    
    public GUI_SettingsPage() {

        // Background and Text Colors:

        // Backgrounds
        Color black = new Color(0x000000);
        Color white = new Color(0xFFFFFF);
        Color grey = new Color(0x27282c);
        Color navyBlue = new Color(0x030552);
        Color deepGreen = new Color(0x02490d);
        Color deepPurple = new Color(0x360246);
        Color deepRed = new Color(0x620404);

        // Text Colors
        Color red = new Color(0xfb0404);
        Color orange = new Color(0xff7b00);
        Color yellow = new Color(0xfffb00);
        Color green = new Color(0x2aff00);
        Color lightBlue = new Color(0x00fff6);
        Color blue = new Color(0x001dff);
        Color purple = new Color(0x9b00ff);
        Color pink = new Color(0xf800ff);

        // Panel setup
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        SwingUtilities.invokeLater(() -> {
            GUIColorsUtil.bindBackgroundToColorManager(this); //Do this later to avoid leaking 'this' in constructor
        });

        // Header creation
        JLabel settingsHeader = new JLabel("Settings");
        GUIColorsUtil.bindTextToColorManager(settingsHeader);
        settingsHeader.setFont(new Font("Dialog", Font.BOLD, 40));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        GUIColorsUtil.bindBackgroundToColorManager(headerPanel);
        headerPanel.add(settingsHeader);

        // Quick separator for aesthetics
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        GUIColorsUtil.bindBackgroundToColorManager(separator);
        GUIColorsUtil.bindTextToColorManager(separator);

        // Selection array panel
        JPanel backgroundColorPanel = new JPanel();
        GUIColorsUtil.bindBackgroundToColorManager(backgroundColorPanel);
        backgroundColorPanel.setLayout(new FlowLayout());

        // Header text creation
        JLabel backgroundLabel = new JLabel("Background:");
        GUIColorsUtil.bindTextToColorManager(backgroundLabel);
        backgroundLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        backgroundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Color selection creation
        JButton bgBlack = new JButton("  "); //Double-space as the text makes the button proper size, used for all buttons
        decorateButton(bgBlack, black, 1);
        JButton bgWhite = new JButton("  ");
        decorateButton(bgWhite, white, 1);
        JButton bgGrey = new JButton("  ");
        decorateButton(bgGrey, grey, 1);
        JButton bgNavyBlue = new JButton("  ");
        decorateButton(bgNavyBlue, navyBlue, 1);
        JButton bgGreen = new JButton("  ");
        decorateButton(bgGreen, deepGreen, 1);
        JButton bgPurple = new JButton("  ");
        decorateButton(bgPurple, deepPurple, 1);
        JButton bgRed = new JButton("  ");
        decorateButton(bgRed, deepRed, 1);
        
        // Add elements to their respective panel, then add both subpanels to holder panel
        backgroundColorPanel.add(bgBlack);
        backgroundColorPanel.add(Box.createHorizontalStrut(30));
        backgroundColorPanel.add(bgWhite);
        backgroundColorPanel.add(Box.createHorizontalStrut(30));
        backgroundColorPanel.add(bgGrey);
        backgroundColorPanel.add(Box.createHorizontalStrut(30));
        backgroundColorPanel.add(bgNavyBlue);
        backgroundColorPanel.add(Box.createHorizontalStrut(30));
        backgroundColorPanel.add(bgGreen);
        backgroundColorPanel.add(Box.createHorizontalStrut(30));
        backgroundColorPanel.add(bgPurple);
        backgroundColorPanel.add(Box.createHorizontalStrut(30));
        backgroundColorPanel.add(bgRed);
        backgroundColorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Text color selection panel
        JPanel textColorPanel = new JPanel();
        GUIColorsUtil.bindBackgroundToColorManager(textColorPanel);
        textColorPanel.setLayout(new FlowLayout());

        // Header text creation
        JLabel textColorLabel = new JLabel("Text Color:");
        GUIColorsUtil.bindTextToColorManager(textColorLabel);
        textColorLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        textColorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Color selection creation
        JButton txtBlack = new JButton("  ");
        decorateButton(txtBlack, black, 2);
        JButton txtWhite = new JButton("  ");
        decorateButton(txtWhite, white,2 );
        JButton txtRed = new JButton("  ");
        decorateButton(txtRed, red, 2);
        JButton txtOrange = new JButton("  ");
        decorateButton(txtOrange, orange, 2);
        JButton txtYellow = new JButton("  ");
        decorateButton(txtYellow, yellow, 2);
        JButton txtGreen = new JButton("  ");
        decorateButton(txtGreen, green, 2);
        JButton txtLightBlue = new JButton("  ");
        decorateButton(txtLightBlue, lightBlue, 2);
        JButton txtBlue = new JButton("  ");
        decorateButton(txtBlue, blue, 2);
        JButton txtPurple = new JButton("  ");
        decorateButton(txtPurple, purple, 2);
        JButton txtPink = new JButton("  ");
        decorateButton(txtPink, pink, 2);

        textColorPanel.add(txtBlack);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtWhite);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtRed);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtOrange);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtYellow);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtGreen);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtLightBlue);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtBlue);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtPurple);
        textColorPanel.add(Box.createHorizontalStrut(15));
        textColorPanel.add(txtPink);
        textColorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Back button initialiation and creation
        backButton = new JButton("<- Back");
        GUIColorsUtil.bindBackgroundToColorManager(backButton);
        GUIColorsUtil.bindTextToColorManager(backButton);
        backButton.setFont(new Font("Dialog", Font.BOLD, 40));
        backButton.setFocusPainted(false); 
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setRolloverEnabled(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(headerPanel);
        this.add(separator);
        this.add(Box.createVerticalStrut(30));
        this.add(backgroundLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(backgroundColorPanel);
        this.add(Box.createVerticalStrut(50));
        this.add(textColorLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(textColorPanel);
        this.add(Box.createVerticalStrut(20));
        this.add(backButton);
        this.add(Box.createVerticalGlue());
    }

    public JButton getBackButton() {
        return backButton;
    }

    private void decorateButton(JButton button, Color color, int type) {
        button.setFont(subtitleFont);
        button.setFocusPainted(false);
        button.setBackground(color);
        button.setForeground(color);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setRolloverEnabled(false);
        
        if(type == 1) {
            button.addActionListener(_ -> {GUIColors.INSTANCE.setBackgroundColor(color); GUIColors.INSTANCE.saveState();});
        } else if (type == 2) {
            button.addActionListener(_ -> {GUIColors.INSTANCE.setTextColor(color); GUIColors.INSTANCE.saveState();});
        }
    }
}
