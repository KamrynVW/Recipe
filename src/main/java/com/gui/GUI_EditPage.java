package com.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
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
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.recipeme.Ingredient;
import com.recipeme.Recipe;
import com.recipeme.Serialize;

public class GUI_EditPage extends JPanel {
    private final JButton saveButton;
    private final JButton backButton;
    private ArrayList<Recipe> recipes;
    
    public GUI_EditPage(Recipe rec, int recipeIndex, Runnable onCreationDone) {

        // Variables for forward and backward buttons to function properly
        final int ingredCardCounter[] = {1};
        final int ingredCardPos[] = {1};
        final int instrCardCounter[] = {1};
        final int instrCardPos[] = {1};
        Font buttonFont = new Font("Dialog", Font.BOLD, 30);

        // Panel set-up
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        SwingUtilities.invokeLater(() -> {
            GUIColorsUtil.bindBackgroundToColorManager(this); //Do this later to avoid leaking 'this' in constructor
        });

        // Panel for title holding in center of frame
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        GUIColorsUtil.bindBackgroundToColorManager(titlePanel);
        JLabel creationTitleLabel = new JLabel("Editing: " + rec.getName());
        GUIColorsUtil.bindTextToColorManager(creationTitleLabel);
        creationTitleLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        creationTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(creationTitleLabel);

        // Creation of recipe name input box
        JPanel recipeNamePanel = new JPanel();
        recipeNamePanel.setLayout(new BoxLayout(recipeNamePanel, BoxLayout.Y_AXIS));
        GUIColorsUtil.bindBackgroundToColorManager(recipeNamePanel);
        JLabel nameLabel = new JLabel("Recipe Name:");
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 25));
        GUIColorsUtil.bindTextToColorManager(nameLabel);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField recipeName = new JTextField(30);
        recipeName.setText(rec.getName());
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
        GUIColorsUtil.bindBackgroundToColorManager(recipeRatingPanel);
        JLabel ratingLabel = new JLabel("Difficulty:");
        GUIColorsUtil.bindTextToColorManager(ratingLabel);

        // Image Icons for star rating difficulty
        ImageIcon empty = new ImageIcon("empty_star.png");
        ImageIcon full = new ImageIcon("full_star.png");

        // Identical button creation for each
        JRadioButton oneButton = new JRadioButton();
        GUIColorsUtil.bindBackgroundToColorManager(oneButton);
        oneButton.setIcon(empty);
        oneButton.setSelectedIcon(full);
        oneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JRadioButton twoButton = new JRadioButton();
        GUIColorsUtil.bindBackgroundToColorManager(twoButton);
        twoButton.setIcon(empty);
        twoButton.setSelectedIcon(full);
        twoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JRadioButton threeButton = new JRadioButton();
        GUIColorsUtil.bindBackgroundToColorManager(threeButton);
        threeButton.setIcon(empty);
        threeButton.setSelectedIcon(full);
        threeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JRadioButton fourButton = new JRadioButton();
        GUIColorsUtil.bindBackgroundToColorManager(fourButton);
        fourButton.setIcon(empty);
        fourButton.setSelectedIcon(full);
        fourButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JRadioButton fiveButton = new JRadioButton();
        GUIColorsUtil.bindBackgroundToColorManager(fiveButton);
        fiveButton.setIcon(empty);
        fiveButton.setSelectedIcon(full);
        fiveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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

        // Click the button according to the already set difficulty
        switch(rec.getDifficulty()) {
            case 1 -> {
                oneButton.doClick();
            }
            
            case 2 -> {
                twoButton.doClick();
            }

            case 3 -> {
                threeButton.doClick();
            }

            case 4 -> {
                fourButton.doClick();
            }

            case 5 -> {
                fiveButton.doClick();
            }
        }

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
        
        // Create holder panel to align typing boxes and label
        JPanel ingredPanelHolder = new JPanel();
        ingredPanelHolder.setLayout(new BoxLayout(ingredPanelHolder, BoxLayout.Y_AXIS));
        GUIColorsUtil.bindBackgroundToColorManager(ingredPanelHolder);

        // Create label for ingredient number being viewed
        JLabel ingredCountLabel = new JLabel("Ingredient " + ingredCardPos[0]);
        ingredCountLabel.setFont(buttonFont);
        GUIColorsUtil.bindTextToColorManager(ingredCountLabel);
        ingredCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingredPanelHolder.add(ingredCountLabel);

        // Create panel and buttons for transitions between ingredients
        JPanel ingredPanel = new JPanel();
        GUIColorsUtil.bindBackgroundToColorManager(ingredPanel);
        ingredPanel.setLayout(new BoxLayout(ingredPanel, BoxLayout.X_AXIS));

        JButton backButtonIngred = new JButton("Prev");
        backButtonIngred.setFont(buttonFont);
        GUIColorsUtil.bindTextToColorManager(backButtonIngred);
        backButtonIngred.setBackground(Color.DARK_GRAY);
        backButtonIngred.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton fwdButtonIngred = new JButton("Add");
        fwdButtonIngred.setFont(buttonFont);
        GUIColorsUtil.bindTextToColorManager(fwdButtonIngred);
        fwdButtonIngred.setBackground(Color.DARK_GRAY);
        fwdButtonIngred.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Create panels for the input boxes
        JPanel valuesPanelCardHolder = new JPanel();
        GUIColorsUtil.bindBackgroundToColorManager(valuesPanelCardHolder);
        CardLayout valuesLyt = new CardLayout();
        valuesPanelCardHolder.setLayout(valuesLyt);

        // This is where creating the components for each ingredient will go
        for(Ingredient ing : rec.getIngredients()) {
            // Create panel for ingredient and set it up
            JPanel valuesPanel = new JPanel();
            GUIColorsUtil.bindBackgroundToColorManager(valuesPanel);
            valuesPanel.setLayout(new BoxLayout(valuesPanel, BoxLayout.X_AXIS));

            // Add ingredient to panel and add panel to card holder
            valuesPanel.add(createCardComponent(ing.getQuantity(), 85));
            valuesPanel.add(createCardComponent(ing.getMeasurement(), 130));
            valuesPanel.add(createCardComponent(ing.getName(), 200));
            valuesPanelCardHolder.add(valuesPanel, "card" + ingredCardPos[0]);

            // Increment counters
            ingredCardPos[0] += 1;
        }

        // Reset position counter and update amount of ingredients
        ingredCardPos[0] = 1;
        ingredCardCounter[0] = rec.getIngredients().size();

        // Button text correction
        if(ingredCardCounter[0] > 1) {
            fwdButtonIngred.setText("Next");
        }

        // Add the back button
        ingredPanel.add(backButtonIngred);

        //Add the individual values panel to the card panel
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

                // Create new entry screen
                JPanel newCard = new JPanel();
                GUIColorsUtil.bindBackgroundToColorManager(newCard);
                newCard.setLayout(new BoxLayout(newCard, BoxLayout.X_AXIS));
                newCard.add(createCardComponent("Qty...", 85));
                newCard.add(createCardComponent("Measure...", 130));
                newCard.add(createCardComponent("Ingredient...", 200));

                valuesPanelCardHolder.add(newCard, "card" + ingredCardCounter[0]);
                valuesLyt.show(valuesPanelCardHolder, "card" + ingredCardCounter[0]);

            } else {
                // Show next ingredient present
                ingredCardPos[0] += 1;
                ingredCountLabel.setText("Ingredient " + ingredCardPos[0]);
                valuesLyt.show(valuesPanelCardHolder, "card" + ingredCardPos[0]);

                if(ingredCardPos[0] == ingredCardCounter[0]) {
                    fwdButtonIngred.setText("Add");
                }
            }
        });

        // Create holder panel to align typing boxes and label
        JPanel instructionPanelHolder = new JPanel();
        instructionPanelHolder.setLayout(new BoxLayout(instructionPanelHolder, BoxLayout.Y_AXIS));
        GUIColorsUtil.bindBackgroundToColorManager(instructionPanelHolder);

        // Create label for ingredient number being viewed
        JLabel instructionCountLabel = new JLabel("Instruction " + instrCardPos[0]);
        instructionCountLabel.setFont(buttonFont);
        GUIColorsUtil.bindTextToColorManager(instructionCountLabel);
        instructionCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionPanelHolder.add(instructionCountLabel);

        // Create panel and buttons for transitions between ingredients
        JPanel instructionPanel = new JPanel();
        GUIColorsUtil.bindBackgroundToColorManager(instructionPanel);
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.X_AXIS));

        JButton backButtonInstr = new JButton("Prev");
        backButtonInstr.setFont(buttonFont);
        GUIColorsUtil.bindTextToColorManager(backButtonInstr);
        backButtonInstr.setBackground(Color.DARK_GRAY);
        backButtonInstr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton fwdButtonInstr = new JButton("Add");
        fwdButtonInstr.setFont(buttonFont);
        GUIColorsUtil.bindTextToColorManager(fwdButtonInstr);
        fwdButtonInstr.setBackground(Color.DARK_GRAY);
        fwdButtonInstr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Create panels for the input boxes
        JPanel instructionPanelCardHolder = new JPanel();
        GUIColorsUtil.bindBackgroundToColorManager(instructionPanelCardHolder);
        CardLayout instructionLyt = new CardLayout();
        instructionPanelCardHolder.setLayout(instructionLyt);

        // This is where the instructions will be generated

        for(String str : rec.getInstructions()) {
            // Create new panel and add new instruction card with instruction contained
            JPanel instructionValuesPanel = new JPanel();
            GUIColorsUtil.bindBackgroundToColorManager(instructionValuesPanel);
            instructionValuesPanel.setLayout(new BoxLayout(instructionValuesPanel, BoxLayout.X_AXIS));
            instructionValuesPanel.add(createInstructionCard(str.substring(3, str.length())));

            // Add card and increment counter
            instructionPanelCardHolder.add(instructionValuesPanel, "card" + instrCardPos[0]);
            instrCardPos[0] += 1;

        }

        // Reset counters to proper values
        instrCardPos[0] = 1;
        instrCardCounter[0] = rec.getNumOfInstructions();

        // Button text correction
        if(instrCardCounter[0] > 1) {
            fwdButtonInstr.setText("Next");
        }

        // Add the back button
        instructionPanel.add(backButtonInstr);

        //Add the individual values panel to the card panel
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
                GUIColorsUtil.bindBackgroundToColorManager(newCard);
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

        // Back/Save button panel creation
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        GUIColorsUtil.bindBackgroundToColorManager(buttonPanel);
        
        // Back button creation and customization
        backButton = new JButton("<- Back");
        backButton.setFont(new Font("Dialog", Font.BOLD, 20));
        GUIColorsUtil.bindTextToColorManager(backButton);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(10, 20, 10, 20)));
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Save button creation and submission
        saveButton = new JButton("Save ->");
        saveButton.setFont(new Font("Dialog", Font.BOLD, 20));
        GUIColorsUtil.bindTextToColorManager(saveButton);
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(10, 20, 10, 20)));
        saveButton.setFocusPainted(false);
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonPanel.add(backButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(saveButton);

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
        saveButton.addActionListener(_ -> {

            // Variable set-up for recipe updating
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            ArrayList<String> instructions = new ArrayList<>();
            int textFieldCounter = 1;

            // Set new name of recipe
            if(recipeName.getText().equals("")) {
                rec.setName("Recipe");
            } else {
                rec.setName(recipeName.getText());
            }

            // Determine difficulty by button selected
            if(oneButton.isSelected()) {
                rec.setDifficulty(1);
            } else if(twoButton.isSelected()) {
                rec.setDifficulty(2);
            } else if(threeButton.isSelected()) {
                rec.setDifficulty(3);
            } else if(fourButton.isSelected()) {
                rec.setDifficulty(4);
            } else if(fiveButton.isSelected()) {
                rec.setDifficulty(5);
            } else {
                rec.setDifficulty(1);
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
                                    if(text.getText().equals("Qty...")) {
                                        text.setText("");
                                    }
                                    ingred.setQuantity(text.getText());
                                }
                                case 2 -> {
                                    textFieldCounter += 1;
                                    if(text.getText().equals("Measure...")) {
                                        text.setText("");
                                    }
                                    ingred.setMeasurement(text.getText());
                                }
                                case 3 -> {
                                    textFieldCounter = 1;
                                    if(text.getText().equals("Ingredient...")) {
                                        text.setText("");
                                    }
                                    ingred.setName(text.getText());
                                }
                            } 
                        }
                    }

                    // Add the ingredient to the list, unless all empty
                    if(!ingred.getName().equals("") || !ingred.getMeasurement().equals("") || !ingred.getQuantity().equals("")) {
                        ingredients.add(ingred);
                    }
                   
                }
            }

            // Replace ingredients list with new ingredient list
            rec.setIngredients(ingredients);

            // Iterate through cards of the instruction panel and get instructions
            for(Component card : instructionPanelCardHolder.getComponents()) {
                if(card instanceof JPanel cardAsPanel) {
                    for(Component comp : cardAsPanel.getComponents()) {
                        if(comp instanceof JTextField text) {
                            if(!text.getText().equals("")) {
                                instructions.add(text.getText());
                            }
                        }
                    }
                }
            }

            // Replace instruction list with new instruction list
            rec.setInstructions(instructions);

            recipes = Serialize.loadRecipeList();
            recipes.remove(recipeIndex);
            recipes.add(rec);
            Serialize.saveRecipeList(recipes);

            if(onCreationDone != null) {
                onCreationDone.run();

                // Close the editing frame without direct reference
                Window window = SwingUtilities.getWindowAncestor(backButton);
                if (window != null) window.dispose();
            }

        });

        backButton.addActionListener(_ -> {
            
            // Close the editing frame without direct reference
            Window window = SwingUtilities.getWindowAncestor(backButton);
            if (window != null) window.dispose();
        });
    }

    private Component createCardComponent(String purpose, int size) {
        JTextField comp = new JTextField(purpose);
        comp.setPreferredSize(new Dimension(size, 50));
        comp.setFont(new Font("Dialog", Font.PLAIN, 20));
        comp.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

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
        comp.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        
        return comp;
    }

    private Component createInstructionCard(String text) {
        JTextField comp = new JTextField(text);
        comp.setFont(new Font("Dialog", Font.PLAIN, 17));
        comp.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        
        return comp;
    }

    public JButton getCreateButton() {
        return saveButton;
    }
    
    public JButton getBackButton() {
        return backButton;
    }
}