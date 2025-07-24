package com.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUI_LandingPage extends JPanel {

    private final JButton landPageStart;

    public GUI_LandingPage() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        SwingUtilities.invokeLater(() -> {
            GUIColorsUtil.bindBackgroundToColorManager(this); //Do this later to avoid leaking 'this' in constructor
        });
        
        // Creation of title label
        JLabel landPageTitle = new JLabel("RecipeMe");
        landPageTitle.setFont(new Font("Dialog", Font.BOLD, 50));
        GUIColorsUtil.bindTextToColorManager(landPageTitle);
        landPageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creation of start button (with event listener defined later)
        landPageStart = new JButton("Start");
        landPageStart.setFont(new Font("Dialog", Font.BOLD, 40));
        GUIColorsUtil.bindTextToColorManager(landPageStart);
        landPageStart.setBackground(Color.DARK_GRAY);
        landPageStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        landPageStart.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(10, 20, 10, 20)));
        landPageStart.setFocusPainted(false);

        // Creation of spacing between title and button for asthetic purposes
        this.add(Box.createVerticalGlue());
        this.add(landPageTitle);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(landPageStart);
        this.add(Box.createVerticalGlue());
    }

    public JButton getStartButton() {
        return landPageStart;
    }
}