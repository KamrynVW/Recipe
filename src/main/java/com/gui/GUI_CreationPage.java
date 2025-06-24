package com.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.recipeme.Ingredient;
import com.recipeme.Recipe;

public class GUI_CreationPage extends JPanel {

    private final JButton submitButton;
    private final JButton backButton;
    private Recipe newRecipe;
    private final Runnable onCreateDone;

    public GUI_CreationPage(Runnable onCreationDone) {
        this.onCreateDone = onCreationDone;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(0x27282c));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setBackground(new Color(0x27282c));
        JLabel creationTitleLabel = new JLabel("Recipe Creation");
        creationTitleLabel.setForeground(new Color(0xFF5F1F));
        creationTitleLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        creationTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(creationTitleLabel);

        // Creation of recipe name input box
        JPanel recipeNamePanel = new JPanel();
        recipeNamePanel.setLayout(new BoxLayout(recipeNamePanel, BoxLayout.Y_AXIS));
        recipeNamePanel.setBackground(new Color(0x27282c));
        JLabel nameLabel = new JLabel("Recipe Name:");
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField recipeName = new JTextField(30);
        recipeName.setFont(new Font("Dialog", Font.PLAIN, 25));
        recipeName.setMaximumSize(new Dimension(600, 40));
        recipeName.setForeground(Color.WHITE);
        recipeName.setBackground(Color.DARK_GRAY);
        recipeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeNamePanel.add(nameLabel);
        recipeNamePanel.add(recipeName);

        // Star rating creation
        JPanel recipeRatingPanel = new JPanel();
        recipeRatingPanel.setLayout(new BoxLayout(recipeRatingPanel, BoxLayout.X_AXIS));
        recipeRatingPanel.setBackground(new Color(0x27282c));
        JLabel ratingLabel = new JLabel("Difficulty:");
        ratingLabel.setForeground(Color.WHITE);

        ImageIcon empty = new ImageIcon("empty_star.png");
        ImageIcon full = new ImageIcon("full_star.png");
        JRadioButton oneButton = new JRadioButton();
        oneButton.setBackground(new Color(0x27282c));
        oneButton.setIcon(empty);
        oneButton.setSelectedIcon(full);
        JRadioButton twoButton = new JRadioButton();
        twoButton.setBackground(new Color(0x27282c));
        twoButton.setIcon(empty);
        twoButton.setSelectedIcon(full);
        JRadioButton threeButton = new JRadioButton();
        threeButton.setBackground(new Color(0x27282c));
        threeButton.setIcon(empty);
        threeButton.setSelectedIcon(full);
        JRadioButton fourButton = new JRadioButton();
        fourButton.setBackground(new Color(0x27282c));
        fourButton.setIcon(empty);
        fourButton.setSelectedIcon(full);
        JRadioButton fiveButton = new JRadioButton();
        fiveButton.setBackground(new Color(0x27282c));
        fiveButton.setIcon(empty);
        fiveButton.setSelectedIcon(full);

        // Event listeners for dynamically filling stars depending on selection
        oneButton.addActionListener(_ -> {
            twoButton.setIcon(empty); 
            threeButton.setIcon(empty);
            fourButton.setIcon(empty);
            fiveButton.setIcon(empty);   
        });

        twoButton.addActionListener(_ -> {
            oneButton.setIcon(full);
            threeButton.setIcon(empty);
            fourButton.setIcon(empty);
            fiveButton.setIcon(empty);
        });

        threeButton.addActionListener(_ -> {
            oneButton.setIcon(full);
            twoButton.setIcon(full);
            fourButton.setIcon(empty);
            fiveButton.setIcon(empty);
        });

        fourButton.addActionListener(_ -> {
            oneButton.setIcon(full);
            twoButton.setIcon(full);
            threeButton.setIcon(full);
            fiveButton.setIcon(empty);
        });

        fiveButton.addActionListener(_ -> {
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

        final int ingredCardCounter[] = {1};
        final int ingredCardPos[] = {1};
        Font buttonFont = new Font("Dialog", Font.BOLD, 30);
        
        // Create holder panel to align typing boxes and label
        JPanel ingredPanelHolder = new JPanel();
        ingredPanelHolder.setLayout(new BoxLayout(ingredPanelHolder, BoxLayout.Y_AXIS));
        ingredPanelHolder.setBackground(new Color(0x27282c));

        // Create label for ingredient number being viewed
        JLabel ingredCountLabel = new JLabel("Ingredient " + ingredCardPos[0]);
        ingredCountLabel.setFont(buttonFont);
        ingredCountLabel.setForeground(Color.WHITE);
        ingredCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingredPanelHolder.add(ingredCountLabel);

        // Create panel and buttons for transitions between ingredients
        JPanel ingredPanel = new JPanel();
        ingredPanel.setBackground(new Color(0x27282c));
        ingredPanel.setLayout(new BoxLayout(ingredPanel, BoxLayout.X_AXIS));
        JButton backButtonIngred = new JButton("Prev");
        backButtonIngred.setFont(buttonFont);
        backButtonIngred.setForeground(Color.WHITE);
        backButtonIngred.setBackground(Color.DARK_GRAY);
        JButton fwdButtonIngred = new JButton("Add");
        fwdButtonIngred.setFont(buttonFont);
        fwdButtonIngred.setForeground(Color.WHITE);
        fwdButtonIngred.setBackground(Color.DARK_GRAY);
        
        // Create panels for the input boxes
        JPanel valuesPanelCardHolder = new JPanel();
        valuesPanelCardHolder.setBackground(new Color(0x27282c));
        CardLayout valuesLyt = new CardLayout();
        valuesPanelCardHolder.setLayout(valuesLyt);
        JPanel valuesPanel = new JPanel();
        valuesPanel.setBackground(new Color(0x27282c));
        valuesPanel.setLayout(new BoxLayout(valuesPanel, BoxLayout.X_AXIS));

        // Add the back button
        ingredPanel.add(backButtonIngred);

        // Add the individual values
        valuesPanel.add(createCardComponent("Qty...", 85));
        valuesPanel.add(createCardComponent("Measure...", 130));
        valuesPanel.add(createCardComponent("Ingredient...", 200));

        //Add the individual values panel to the card panel
        valuesPanelCardHolder.add(valuesPanel, "card1");
        ingredPanel.add(valuesPanelCardHolder);

        // Add the forward button
        ingredPanel.add(fwdButtonIngred);

        // Modify size of values card panel to ensure consistent sizing
        valuesPanelCardHolder.setMaximumSize(valuesPanelCardHolder.getPreferredSize());

        // Add the actual panel to the holder
        ingredPanelHolder.add(ingredPanel);

        // Event listeners for new ingredient cards
        backButtonIngred.addActionListener(_ -> {
            if(ingredCardPos[0] == 1) {
                ingredCardPos[0] = ingredCardCounter[0];
                ingredCountLabel.setText("Ingredient " + ingredCardPos[0]);
                valuesLyt.show(valuesPanelCardHolder, "card" + ingredCardPos[0]);
                fwdButtonIngred.setText("Add");
            } else {
                ingredCardPos[0] -= 1;
                ingredCountLabel.setText("Ingredient " + ingredCardPos[0]);
                valuesLyt.show(valuesPanelCardHolder, "card" + ingredCardPos[0]);
                fwdButtonIngred.setText("Next");
            }
        });

        fwdButtonIngred.addActionListener(_ -> {
            if(ingredCardPos[0] == ingredCardCounter[0]) {
                ingredCardPos[0] += 1;
                ingredCardCounter[0] += 1;

                ingredCountLabel.setText("Ingredient " + ingredCardPos[0]);

                JPanel newCard = new JPanel();
                newCard.setBackground(new Color(0x27282c));
                newCard.setLayout(new BoxLayout(newCard, BoxLayout.X_AXIS));
                newCard.add(createCardComponent("Qty...", 85));
                newCard.add(createCardComponent("Measure...", 130));
                newCard.add(createCardComponent("Ingredient...", 200));

                valuesPanelCardHolder.add(newCard, "card" + ingredCardCounter[0]);
                valuesLyt.show(valuesPanelCardHolder, "card" + ingredCardCounter[0]);

            } else {
                ingredCardPos[0] += 1;
                ingredCountLabel.setText("Ingredient " + ingredCardPos[0]);
                valuesLyt.show(valuesPanelCardHolder, "card" + ingredCardPos[0]);

                if(ingredCardPos[0] == ingredCardCounter[0]) {
                    fwdButtonIngred.setText("Add");
                }
            }
        });

        //

        final int instrCardCounter[] = {1};
        final int instrCardPos[] = {1};

        // Create holder panel to align typing boxes and label
        JPanel instructionPanelHolder = new JPanel();
        instructionPanelHolder.setLayout(new BoxLayout(instructionPanelHolder, BoxLayout.Y_AXIS));
        instructionPanelHolder.setBackground(new Color(0x27282c));

        // Create label for ingredient number being viewed
        JLabel instructionCountLabel = new JLabel("Instruction " + instrCardPos[0]);
        instructionCountLabel.setFont(buttonFont);
        instructionCountLabel.setForeground(Color.WHITE);
        instructionCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionPanelHolder.add(instructionCountLabel);

        // Create panel and buttons for transitions between ingredients
        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(new Color(0x27282c));
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.X_AXIS));
        JButton backButtonInstr = new JButton("Prev");
        backButtonInstr.setFont(buttonFont);
        backButtonInstr.setForeground(Color.WHITE);
        backButtonInstr.setBackground(Color.DARK_GRAY);
        JButton fwdButtonInstr = new JButton("Add");
        fwdButtonInstr.setFont(buttonFont);
        fwdButtonInstr.setForeground(Color.WHITE);
        fwdButtonInstr.setBackground(Color.DARK_GRAY);
        
        // Create panels for the input boxes
        JPanel instructionPanelCardHolder = new JPanel();
        instructionPanelCardHolder.setBackground(new Color(0x27282c));
        CardLayout instructionLyt = new CardLayout();
        instructionPanelCardHolder.setLayout(instructionLyt);
        JPanel instructionValuesPanel = new JPanel();
        instructionValuesPanel.setBackground(new Color(0x27282c));
        instructionValuesPanel.setLayout(new BoxLayout(instructionValuesPanel, BoxLayout.X_AXIS));

        // Add the back button
        instructionPanel.add(backButtonInstr);

        // Add the individual values
        instructionValuesPanel.add(createInstructionCard());
        
        //Add the individual values panel to the card panel
        instructionPanelCardHolder.add(instructionValuesPanel, "card1");
        instructionPanel.add(instructionPanelCardHolder);

        // Add the forward button
        instructionPanel.add(fwdButtonInstr);

        // Modify size of values card panel to ensure consistent sizing
        instructionPanelCardHolder.setMaximumSize(valuesPanelCardHolder.getPreferredSize());

        // Add the actual panel to the holder
        instructionPanelHolder.add(instructionPanel);

        backButtonInstr.addActionListener(_ -> {
            // If going backwards from start, go to end. Otherwise, go back a card
            if(instrCardPos[0] == 1) {
                instrCardPos[0] = instrCardCounter[0];
                instructionCountLabel.setText("Instruction " + instrCardPos[0]);
                instructionLyt.show(instructionPanelCardHolder, "card" + instrCardPos[0]);

                // Set forward button text to add
                fwdButtonInstr.setText("Add");
            } else {
                instrCardPos[0] -= 1;
                instructionCountLabel.setText("Instruction " + instrCardPos[0]);
                instructionLyt.show(instructionPanelCardHolder, "card" + instrCardPos[0]);

                // Reset forward button text to next
                fwdButtonInstr.setText("Next");
            }
        });

        fwdButtonInstr.addActionListener(_ -> {
            // If going forward at the end of card list, create a new one (add)
            // Otherwise, go to the next card
            if(instrCardPos[0] == instrCardCounter[0]) {
                instrCardPos[0] += 1;
                instrCardCounter[0] += 1;

                instructionCountLabel.setText("Instruction " + instrCardPos[0]);

                JPanel newCard = new JPanel();
                newCard.setBackground(new Color(0x27282c));
                newCard.setLayout(new BoxLayout(newCard, BoxLayout.X_AXIS));
                newCard.add(createInstructionCard());
                
                instructionPanelCardHolder.add(newCard, "card" + instrCardCounter[0]);
                instructionLyt.show(instructionPanelCardHolder, "card" + instrCardCounter[0]);

            } else {
                instrCardPos[0] += 1;
                instructionCountLabel.setText("Instruction " + instrCardPos[0]);
                instructionLyt.show(instructionPanelCardHolder, "card" + instrCardPos[0]);

                // If now at the last card, change next to add for user indication
                if(instrCardPos[0] == instrCardCounter[0]) {
                    fwdButtonInstr.setText("Add");
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(new Color(0x27282c));
        
        backButton = new JButton("<- Back");
        backButton.setFont(new Font("Dialog", Font.BOLD, 20));
        backButton.setForeground(new Color(0xFF5F1F));
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(10, 20, 10, 20)));
        backButton.setFocusPainted(false);

        submitButton = new JButton("Create ->");
        submitButton.setFont(new Font("Dialog", Font.BOLD, 20));
        submitButton.setForeground(new Color(0xFF5F1F));
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(10, 20, 10, 20)));
        submitButton.setFocusPainted(false);

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(submitButton);


        // Adding sub-panels to the Y_AXIS layout
        this.add(titlePanel);
        this.add(Box.createVerticalStrut(20));
        this.add(recipeNamePanel);
        this.add(Box.createVerticalStrut(10));
        this.add(recipeRatingPanel);
        this.add(Box.createVerticalStrut(30));
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(Box.createVerticalStrut(20));
        this.add(ingredPanelHolder);
        this.add(Box.createVerticalStrut(60));
        this.add(instructionPanelHolder);
        this.add(Box.createVerticalStrut(60));
        this.add(buttonPanel);

        // Action Listeners for recipe creation/deletion
        submitButton.addActionListener(_ -> {
            
            // Variable set-up for recipe creation
            String nameOfRecipe = recipeName.getText();
            int difficulty;
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            ArrayList<String> instructions = new ArrayList<>();

            int textFieldCounter = 1;

            // Determine difficulty by button selected
            if(oneButton.isSelected()) {
                difficulty = 1;
            } else if(twoButton.isSelected()) {
                difficulty = 2;
            } else if(threeButton.isSelected()) {
                difficulty = 3;
            } else if(fourButton.isSelected()) {
                difficulty = 4;
            } else if(fiveButton.isSelected()) {
                difficulty = 5;
            } else {
                difficulty = 1;
            }

            // Loop through cards of ingredients and place values in Ingredient class instance
            for(Component card : valuesPanelCardHolder.getComponents()) {
                if(card instanceof JPanel cardAsPanel) {
                    Ingredient ingred = new Ingredient();
                    for(Component comp : cardAsPanel.getComponents()) {
                        if(comp instanceof JTextField text) {
                            switch (textFieldCounter) {
                                case 1 -> {
                                    textFieldCounter += 1;
                                    ingred.setQuantity(text.getText());
                                }
                                case 2 -> {
                                    textFieldCounter += 1;
                                    ingred.setMeasurement(text.getText());
                                }
                                case 3 -> {
                                    textFieldCounter = 1;
                                    ingred.setName(text.getText());
                                }
                            }
                        }
                    }

                    // Add the ingredient to the list
                    ingredients.add(ingred);
                }
            }

            // Iterate through cards of the instruction panel and get instructions
            for(Component card : instructionPanelCardHolder.getComponents()) {
                if(card instanceof JPanel cardAsPanel) {
                    for(Component comp : cardAsPanel.getComponents()) {
                        if(comp instanceof JTextField text) {
                            instructions.add(text.getText());
                        }
                    }
                }
            }

            // Create new recipe with fetched instructions
            newRecipe = new Recipe(nameOfRecipe, ingredients, difficulty, instructions);
            
            // Wipe recipe name and difficulty rating
            recipeName.setText("");
            ratingGroup.clearSelection();
            oneButton.setIcon(empty);
            twoButton.setIcon(empty);
            threeButton.setIcon(empty);
            fourButton.setIcon(empty);

            // Remove all cards, reset counter/pos, and create new base card
            valuesPanelCardHolder.removeAll();
            ingredCardCounter[0] = 1;
            ingredCardPos[0] = 1;
            ingredCountLabel.setText("Ingredient 1");
            JPanel newCard = new JPanel();
            newCard.setBackground(new Color(0x27282c));
            newCard.setLayout(new BoxLayout(newCard, BoxLayout.X_AXIS));
            newCard.add(createCardComponent("Qty...", 85));
            newCard.add(createCardComponent("Measure...", 130));
            newCard.add(createCardComponent("Ingredient...", 200));

            valuesPanelCardHolder.add(newCard, "card1");
            valuesLyt.show(valuesPanelCardHolder, "card1");

            // Remove all cards, reset counter/pos, and create new base card
            instructionPanelCardHolder.removeAll();
            instrCardCounter[0] = 1;
            instrCardPos[0] = 1;
            instructionCountLabel.setText("Instruction 1");
            JPanel newCard2 = new JPanel();
            newCard2.setBackground(new Color(0x27282c));
            newCard2.setLayout(new BoxLayout(newCard2, BoxLayout.X_AXIS));
            newCard2.add(createInstructionCard());
                
            instructionPanelCardHolder.add(newCard2, "card1");
            instructionLyt.show(instructionPanelCardHolder, "card1");

            if(onCreateDone != null) {
                onCreateDone.run();
            }
        });

        backButton.addActionListener(_ -> {

            // Wipe recipe name and difficulty rating
            recipeName.setText("");
            ratingGroup.clearSelection();
            oneButton.setIcon(empty);
            twoButton.setIcon(empty);
            threeButton.setIcon(empty);
            fourButton.setIcon(empty);

            // Remove all cards, reset counter/pos, and create new base card
            valuesPanelCardHolder.removeAll();
            ingredCardCounter[0] = 1;
            ingredCardPos[0] = 1;
            ingredCountLabel.setText("Ingredient 1");
            JPanel newCard = new JPanel();
            newCard.setBackground(new Color(0x27282c));
            newCard.setLayout(new BoxLayout(newCard, BoxLayout.X_AXIS));
            newCard.add(createCardComponent("Qty...", 85));
            newCard.add(createCardComponent("Measure...", 130));
            newCard.add(createCardComponent("Ingredient...", 200));

            valuesPanelCardHolder.add(newCard, "card1");
            valuesLyt.show(valuesPanelCardHolder, "card1");

            // Remove all cards, reset counter/pos, and create new base card
            instructionPanelCardHolder.removeAll();
            instrCardCounter[0] = 1;
            instrCardPos[0] = 1;
            instructionCountLabel.setText("Instruction 1");
            JPanel newCard2 = new JPanel();
            newCard2.setBackground(new Color(0x27282c));
            newCard2.setLayout(new BoxLayout(newCard2, BoxLayout.X_AXIS));
            newCard2.add(createInstructionCard());
                
            instructionPanelCardHolder.add(newCard2, "card1");
            instructionLyt.show(instructionPanelCardHolder, "card1");

        });
    }

    private Component createCardComponent(String purpose, int size) {
        JTextField comp = new JTextField(purpose);
        comp.setPreferredSize(new Dimension(size, 50));
        comp.setFont(new Font("Dialog", Font.PLAIN, 20));

        comp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(purpose.equals(comp.getText()))
                comp.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(comp.getText())) {
                    comp.setText(purpose);
                }
            }
        });

        return comp;
    }

    private Component createInstructionCard() {
        JTextField comp = new JTextField();
        comp.setFont(new Font("Dialog", Font.PLAIN, 17));
        
        return comp;
    }

    public JButton getCreateButton() {
        return submitButton;
    }
    
    public JButton getBackButton() {
        return backButton;
    }

    public Recipe getRecipes() {
        return newRecipe;
    }
}