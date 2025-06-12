package com.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
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

        /* Landing Page */

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

        /* Base Page */

        // Creation of page with border layout
        JPanel basePage = new JPanel(new BorderLayout());
        basePage.setBackground(bg);

        // Creation of top panel including search bar and buttons
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
        topBar.setBackground(bg);

        // Creation of search field
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Dialog", Font.PLAIN, 30));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(Color.DARK_GRAY);

        // Creation of search button
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        searchButton.setForeground(txt);
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setFocusPainted(false);

        // Creation of new recipe button
        JButton newButton = new JButton("New");
        newButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        newButton.setForeground(txt);
        newButton.setBackground(Color.DARK_GRAY);
        newButton.setFocusPainted(false);

        // Add buttons and fields with specific order to create appropriate bar
        topBar.add(searchField);
        topBar.add(searchButton);
        topBar.add(Box.createHorizontalGlue());
        topBar.add(newButton);
        basePage.add(topBar, BorderLayout.NORTH);

        /* Creation Page */

        // Creation panel creation
        JPanel creationPanel = new JPanel();
        creationPanel.setLayout(new BoxLayout(creationPanel, BoxLayout.Y_AXIS));
        creationPanel.setBackground(bg);

        // Creation of recipe name input box
        JPanel recipeNamePanel = new JPanel();
        recipeNamePanel.setBackground(bg);
        JLabel nameLabel = new JLabel("Recipe Name:");
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        nameLabel.setForeground(Color.WHITE);
        JTextField recipeName = new JTextField(30);
        recipeName.setFont(new Font("Dialog", Font.PLAIN, 25));
        recipeName.setForeground(Color.WHITE);
        recipeName.setBackground(Color.DARK_GRAY);
        recipeNamePanel.add(nameLabel);
        recipeNamePanel.add(recipeName);

        // Star rating creation
        JPanel recipeRatingPanel = new JPanel();
        recipeRatingPanel.setBackground(bg);
        JLabel ratingLabel = new JLabel("Difficulty:");
        ratingLabel.setForeground(Color.WHITE);

        ImageIcon empty = new ImageIcon("empty_star.png");
        ImageIcon full = new ImageIcon("full_star.png");
        JRadioButton oneButton = new JRadioButton();
        oneButton.setBackground(bg);
        oneButton.setIcon(empty);
        oneButton.setSelectedIcon(full);
        JRadioButton twoButton = new JRadioButton();
        twoButton.setBackground(bg);
        twoButton.setIcon(empty);
        twoButton.setSelectedIcon(full);
        JRadioButton threeButton = new JRadioButton();
        threeButton.setBackground(bg);
        threeButton.setIcon(empty);
        threeButton.setSelectedIcon(full);
        JRadioButton fourButton = new JRadioButton();
        fourButton.setBackground(bg);
        fourButton.setIcon(empty);
        fourButton.setSelectedIcon(full);
        JRadioButton fiveButton = new JRadioButton();
        fiveButton.setBackground(bg);
        fiveButton.setIcon(empty);
        fiveButton.setSelectedIcon(full);

        // Event listeners for dynamically filling stars depending on selection
        oneButton.addActionListener(e -> {
            twoButton.setIcon(empty); 
            threeButton.setIcon(empty);
            fourButton.setIcon(empty);
            fiveButton.setIcon(empty);   
        });

        twoButton.addActionListener(e -> {
            oneButton.setIcon(full);
            threeButton.setIcon(empty);
            fourButton.setIcon(empty);
            fiveButton.setIcon(empty);
        });

        threeButton.addActionListener(e -> {
            oneButton.setIcon(full);
            twoButton.setIcon(full);
            fourButton.setIcon(empty);
            fiveButton.setIcon(empty);
        });

        fourButton.addActionListener(e -> {
            oneButton.setIcon(full);
            twoButton.setIcon(full);
            threeButton.setIcon(full);
            fiveButton.setIcon(empty);
        });

        fiveButton.addActionListener(e -> {
            oneButton.setIcon(full);
            twoButton.setIcon(full);
            threeButton.setIcon(full);
            fourButton.setIcon(full);
        });

        // Creation of button group and addition to the overall panel
        ButtonGroup ratingGroup = new ButtonGroup();
        ratingGroup.add(oneButton);
        ratingGroup.add(twoButton);
        ratingGroup.add(threeButton);
        ratingGroup.add(fourButton);
        ratingGroup.add(fiveButton);
        recipeRatingPanel.add(ratingLabel);
        recipeRatingPanel.add(oneButton);
        recipeRatingPanel.add(twoButton);
        recipeRatingPanel.add(threeButton);
        recipeRatingPanel.add(fourButton);
        recipeRatingPanel.add(fiveButton);

        // Adding sub-panels to the Y_AXIS layout
        creationPanel.add(recipeNamePanel);
        creationPanel.add(recipeRatingPanel);

        

        // Adding all panels to the main CardLayout panel
        mainPanel.add(landPagePanel, "landing");
        mainPanel.add(basePage, "base");
        mainPanel.add(creationPanel, "create");

        // General frame setup and addition of main card panel
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(750, 750));
        this.setVisible(true);
        this.add(mainPanel);

        // Action Listener definitions
        landPageStart.addActionListener(e -> lyt.show(mainPanel, "base"));
        newButton.addActionListener(e -> lyt.show(mainPanel, "create"));
    }
    
}
