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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUI_LandingPage extends JPanel {

    Color bg = new Color(0x27282c);
    Color txt = new Color(0xFF5F1F);
    private final JButton landPageStart;

    public GUI_LandingPage() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(bg);

        // Creation of title label
        JLabel landPageTitle = new JLabel("RecipeMe");
        landPageTitle.setFont(new Font("Dialog", Font.BOLD, 50));
        landPageTitle.setForeground(txt);
        landPageTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creation of start button (with event listener defined later)
        landPageStart = new JButton("Start");
        landPageStart.setFont(new Font("Dialog", Font.BOLD, 40));
        landPageStart.setForeground(txt);
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