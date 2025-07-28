package com.gui;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RecipeMeGUI extends JFrame {

    public RecipeMeGUI() {

        CardLayout lyt = new CardLayout();
        JPanel mainPanel = new JPanel(lyt);

        /* Landing Page */

        GUI_LandingPage landPagePanel = new GUI_LandingPage();

        /* Base Page */

        GUI_BasePage basePage = new GUI_BasePage();
        
        /* Creation Page */

        GUI_CreationPage creationPanel = new GUI_CreationPage(() -> {lyt.show(mainPanel, "base"); basePage.updateRecipes();});

        /* Settings Page */

        GUI_SettingsPage settingsPanel = new GUI_SettingsPage();
        
        // Adding all panels to the main CardLayout panel
        mainPanel.add(landPagePanel, "landing");
        mainPanel.add(basePage, "base");
        mainPanel.add(creationPanel, "create");
        mainPanel.add(settingsPanel, "settings");

        // General frame setup and addition of main card panel
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(750, 750));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(mainPanel);

        // Action Listener definitions
        landPagePanel.getStartButton().addActionListener(_ -> lyt.show(mainPanel, "base"));
        basePage.getNewButton().addActionListener(_ -> lyt.show(mainPanel, "create"));
        basePage.getSettingsButton().addActionListener(_ -> lyt.show(mainPanel, "settings"));
        creationPanel.getBackButton().addActionListener(_ -> lyt.show(mainPanel, "base"));
        settingsPanel.getBackButton().addActionListener(_ -> lyt.show(mainPanel, "base"));

    }
}
