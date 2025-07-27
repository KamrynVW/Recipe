package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.recipeme.Ingredient;
import com.recipeme.Recipe;
import com.recipeme.Serialize;

public class GUI_BasePage extends JPanel {

    private final JButton newButton;
    private final JButton settingsButton;
    private final JPanel recipeScrollPanel;
    private final JScrollPane scroll;
    private ArrayList<Recipe> listOfRecipes;
    private final Border recipeListingBorder = BorderFactory.createLineBorder(Color.GRAY, 2);
    private int displayedRecipes;
    
    public GUI_BasePage() {
        
        // Layout setup
        this.setLayout(new BorderLayout());
        SwingUtilities.invokeLater(() -> {
            GUIColorsUtil.bindBackgroundToColorManager(this); //Do this later to avoid leaking 'this' in constructor
        });
        Border topBarBorder = BorderFactory.createLineBorder(Color.GRAY, 1);

        // Creation of top panel including search bar and buttons
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        GUIColorsUtil.bindBackgroundToColorManager(topBar);

        //Sub-panels for top bar
        JPanel topBarSearch = new JPanel();
        GUIColorsUtil.bindBackgroundToColorManager(topBarSearch);
        topBarSearch.setLayout(new BoxLayout(topBarSearch, BoxLayout.X_AXIS));

        JPanel topBarButtons = new JPanel();
        GUIColorsUtil.bindBackgroundToColorManager(topBarButtons);
        topBarButtons.setLayout(new BoxLayout(topBarButtons, BoxLayout.X_AXIS));

        // Creation of search field
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Dialog", Font.ITALIC, 17));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(Color.DARK_GRAY);
        searchField.setText("Enter ingredients here (between commas with '!' before term to exclude)...");
        searchField.setBorder(topBarBorder);
        searchField.setMaximumSize(new Dimension(7000, 100));
        searchField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if("Enter ingredients here (between commas with '!' before term to exclude)...".equals(searchField.getText()))
                searchField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(searchField.getText())) {
                    searchField.setText("Enter ingredients here (between commas with '!' before term to exclude)...");
                }
            }
        });
        
        // Creation of search button
        JButton searchButton = new JButton(" \uD83D\uDD0E ");
        searchButton.setFont(new Font("Dialog", Font.PLAIN, 35));
        GUIColorsUtil.bindTextToColorManager(searchButton);
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(topBarBorder);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        SwingUtilities.invokeLater(() -> {
            searchButton.addActionListener(_ -> updateRecipes(searchField.getText()));

            searchField.addActionListener(_ -> {
                searchButton.doClick();
            });
        });
        
        // Creation of new recipe button
        newButton = new JButton(" \u271A ");
        newButton.setFont(new Font("Dialog", Font.PLAIN, 35));
        GUIColorsUtil.bindTextToColorManager(newButton);
        newButton.setBackground(Color.DARK_GRAY);
        newButton.setFocusPainted(false);
        newButton.setBorder(topBarBorder);
        newButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        newButton.addActionListener(_ -> {
            searchField.setText("Enter ingredients here (between commas with '!' before term to exclude)...");
            updateRecipes();
        });

        settingsButton = new JButton(" \u2699 ");
        settingsButton.setFont(new Font("Dialog", Font.PLAIN, 35));
        settingsButton.setMargin(new Insets(10, 20, 10, 20));
        GUIColorsUtil.bindTextToColorManager(settingsButton);
        settingsButton.setBackground(Color.DARK_GRAY);
        settingsButton.setFocusPainted(false);
        settingsButton.setBorder(topBarBorder);
        settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        settingsButton.addActionListener(_ -> {
            searchField.setText("Enter ingredients here (between commas with '!' before term to exclude)...");
            updateRecipes();
        });

        // Add components to sub-panels
        topBarSearch.add(searchField);
        topBarSearch.add(Box.createHorizontalStrut(2));
        topBarSearch.add(searchButton);

        topBarButtons.add(newButton);
        topBarButtons.add(Box.createHorizontalStrut(3));
        topBarButtons.add(settingsButton);

        // Add sub-panels to top bar panel
        topBar.add(topBarSearch, BorderLayout.WEST);
        topBar.add(topBarButtons, BorderLayout.EAST);
        this.add(topBar, BorderLayout.NORTH);

        recipeScrollPanel = new JPanel();
        recipeScrollPanel.setLayout(new BoxLayout(recipeScrollPanel, BoxLayout.Y_AXIS));
        GUIColorsUtil.bindBackgroundToColorManager(recipeScrollPanel);

        scroll = new JScrollPane(recipeScrollPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scroll, BorderLayout.CENTER);

        updateRecipes();

    }

    public JButton getNewButton() {
        return newButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    private void addNewRecipe(Recipe rec) {
        // Place recipes into the scroll pane

        displayedRecipes += 1; // Increment amount of showed recipies
        int indexOfRecipe = displayedRecipes;

        // Panel set-up
        JPanel listedRecipePanel = new JPanel();
        listedRecipePanel.setBorder(recipeListingBorder);
        GUIColorsUtil.bindBackgroundToColorManager(listedRecipePanel);
        listedRecipePanel.setLayout(new BoxLayout(listedRecipePanel, BoxLayout.Y_AXIS));
        listedRecipePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // Dynamic titling and ingredient listing
        JLabel titleOfRecipe;
        JLabel listOfIngredients = new JLabel("Ingredients: ");

        if(rec.getName().length() > 23) {
            titleOfRecipe = new JLabel(rec.getName().substring(0, 20) + "... - " + rec.getDifficulty() + "/5");
        } else {
            titleOfRecipe = new JLabel(rec.getName() + " - " + rec.getDifficulty() + "/5");
        }

        if(rec.getIngredients().size() > 4) {
            for(int i = 0; i < 4; i++) {
                listOfIngredients.setText(listOfIngredients.getText() + rec.getIngredients().get(i).getName() + ", ");
            }
            listOfIngredients.setText(listOfIngredients.getText().substring(0, listOfIngredients.getText().length() - 2) + "...");
        } else {
            for(Ingredient ing : rec.getIngredients()) {
                listOfIngredients.setText(listOfIngredients.getText() + ing.getName() + ", ");
            }
            listOfIngredients.setText(listOfIngredients.getText().substring(0, listOfIngredients.getText().length() - 2));
        }

        titleOfRecipe.setFont(new Font("Dialog", Font.BOLD, 35));
        GUIColorsUtil.bindTextToColorManager(titleOfRecipe);
        listOfIngredients.setFont(new Font("Dialog", Font.PLAIN, 20));
        GUIColorsUtil.bindTextToColorManager(listOfIngredients);

        // Edit and delete button creation and decoration
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        GUIColorsUtil.bindTextToColorManager(editButton);
        GUIColorsUtil.bindTextToColorManager(deleteButton);
        editButton.setFocusPainted(false); 
        editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editButton.setRolloverEnabled(false);
        deleteButton.setFocusPainted(false); 
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setRolloverEnabled(false);
        editButton.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 2), new EmptyBorder(1, 3, 1, 3)));
        deleteButton.setBorder(new CompoundBorder(new LineBorder(Color.LIGHT_GRAY, 2), new EmptyBorder(1, 3, 1, 3)));
        editButton.setBackground(Color.GRAY);
        deleteButton.setBackground(Color.GRAY);
        editButton.setFont(new Font("Dialog", Font.PLAIN, 15));
        deleteButton.setFont(new Font("Dialog", Font.PLAIN, 15));

        // Delete event listener
        
        deleteButton.addActionListener(_ -> {
            // Make a quick pop-up confirming whether user wants to delete or not with recipe name
            int choice = JOptionPane.showConfirmDialog(null, "Delete " + rec.getName() + "?", "Confirm deletion", JOptionPane.OK_CANCEL_OPTION);

            if(choice == JOptionPane.OK_OPTION) {
                deleteRecipe(indexOfRecipe - 1); // Provides an index of recipe in list for removal
            }
        }); 

        // Edit action listener
        editButton.addActionListener(_ -> {
            editRecipe(indexOfRecipe - 1);
        });

        // Panel to properly align edit and delete buttons
        JPanel editOrDeleteHolder = new JPanel();
        editOrDeleteHolder.setLayout(new FlowLayout(FlowLayout.RIGHT));
        editOrDeleteHolder.setAlignmentX(Component.LEFT_ALIGNMENT);
        editOrDeleteHolder.add(editButton);
        editOrDeleteHolder.add(deleteButton);
        editOrDeleteHolder.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        GUIColorsUtil.bindBackgroundToColorManager(editOrDeleteHolder);
        
        listedRecipePanel.add(titleOfRecipe);
        listedRecipePanel.add(listOfIngredients);
        listedRecipePanel.add(editOrDeleteHolder);
        addViewRecipe(listedRecipePanel, rec);

        recipeScrollPanel.add(listedRecipePanel);
        recipeScrollPanel.add(Box.createVerticalGlue());
    }

    public final void updateRecipes() {
        // Load recipes from the save data (to create concurrency) and add all to pane
        listOfRecipes = Serialize.loadRecipeList();
        displayedRecipes = 0;

        recipeScrollPanel.removeAll();
        recipeScrollPanel.revalidate();
        recipeScrollPanel.repaint();

        for(Recipe rec : listOfRecipes) {
            addNewRecipe(rec);
        }

        if(listOfRecipes.isEmpty()) {
            stateNoRecipes();
        }
    }

    public final void updateRecipes(String searchTerms) {

        boolean showRecipe = false;
        boolean didShowRecipe = false;
        listOfRecipes = Serialize.loadRecipeList();
        displayedRecipes = 0;

        recipeScrollPanel.removeAll();
        recipeScrollPanel.revalidate();
        recipeScrollPanel.repaint();  
        
        ArrayList<String> searchTermsSeparated = new ArrayList<>();

        for(String str : searchTerms.split(",")) {
            searchTermsSeparated.add(str.trim());
        }

        // If the placeholder in search bar ends up as a search, filter it out and replace with nothing
        if(searchTermsSeparated.size() == 1 & searchTermsSeparated.get(0).equals("Enter ingredients here (between commas with '!' before term to exclude)...")) {
            searchTermsSeparated.remove(0);
        }

        for(Recipe rec : listOfRecipes) {
            for(String term : searchTermsSeparated) {
                // If there is a '!', filter the ingredient rather than search for
                if(term.contains("!")) {
                    showRecipe = rec.doesRecipeContainIngredient(term.substring(1, term.length())) == 0; // Do substring to eliminate '!'
                } else {
                    // Regular recipe search utility
                    showRecipe = rec.doesRecipeContainIngredient(term) == 1 || rec.getName().equalsIgnoreCase(term);
                }
            }

            if(showRecipe) {
                // Add the recipe to the screen, state we've shown a recipe, and reset that a recipe has been found for future results
                addNewRecipe(rec);
                didShowRecipe = true;
                showRecipe = false;
            } 
        }

        if(!didShowRecipe) {
            stateNoRecipes();
        }
    }

    private void addViewRecipe(JComponent display, Recipe rec) {
        display.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        display.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Create a new window to display the recipe in off to the side
                JFrame recipeDisplay = new JFrame();
                recipeDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                recipeDisplay.setSize(new Dimension(350, 750));
                recipeDisplay.setLocation(1000, 0);
                recipeDisplay.setVisible(true);
                recipeDisplay.setResizable(false);
                
                // This panel holds all components in a vertical stack
                JPanel recipeDisplayPanel = new JPanel();
                recipeDisplayPanel.setLayout(new BoxLayout(recipeDisplayPanel, BoxLayout.Y_AXIS));
                GUIColorsUtil.bindBackgroundToColorManager(recipeDisplayPanel);

                // Recipe name big and bold at the top
                JLabel recipeName = new JLabel(rec.getName());
                GUIColorsUtil.bindTextToColorManager(recipeName);
                recipeName.setFont(new Font("Dialog", Font.BOLD, 30));
                recipeName.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Thin separator below the title for aesthetics
                JSeparator horizBreak = new JSeparator();
                GUIColorsUtil.bindTextToColorManager(horizBreak);
                GUIColorsUtil.bindBackgroundToColorManager(horizBreak);

                Font ingredientFont = new Font("Dialog", Font.PLAIN, 20); //Font for ingredients and instructions

                // A vertically stacked panel that holds ingredients
                JPanel ingredientsStackedPanel = new JPanel();
                ingredientsStackedPanel.setLayout(new BoxLayout(ingredientsStackedPanel, BoxLayout.Y_AXIS));
                GUIColorsUtil.bindBackgroundToColorManager(ingredientsStackedPanel);
                JTextArea ingredientsHeader = new JTextArea("Ingredients:");
                ingredientsHeader.setWrapStyleWord(true);
                ingredientsHeader.setLineWrap(true);
                ingredientsHeader.setEditable(false);
                ingredientsHeader.setFocusable(false);
                ingredientsHeader.setOpaque(false);
                ingredientsHeader.setBorder(null);
                ingredientsHeader.setFont(ingredientFont);
                ingredientsHeader.setFont(new Font("Dialog", Font.BOLD, 22));
                GUIColorsUtil.bindTextToColorManager(ingredientsHeader);
                ingredientsStackedPanel.add(ingredientsHeader);

                // For each ingredient, add them in formatted string to the stacked panel
                for(Ingredient ing : rec.getIngredients()) {
                    JTextArea ingredient = new JTextArea(ing.getName() + " - " + ing.getQuantity() + " " + ing.getMeasurement());

                    // If ingredient text is empty, remove dash
                    if(ingredient.getText().equals(" -  ")) {
                        ingredient.setText("");
                    }

                    ingredient.setWrapStyleWord(true);
                    ingredient.setLineWrap(true);
                    ingredient.setEditable(false);
                    ingredient.setFocusable(false);
                    ingredient.setOpaque(false);
                    ingredient.setBorder(null);
                    ingredient.setFont(ingredientFont);
                    GUIColorsUtil.bindTextToColorManager(ingredient);
                    ingredientsStackedPanel.add(ingredient);
                }

                // Turn the stacked panel to a scroll panel with specific measures to ensure visibility
                JScrollPane scrollPane = new JScrollPane(ingredientsStackedPanel);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setPreferredSize(new Dimension(330, 315)); // visible size - tested according to current dimensions
                scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 315));
                scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                GUIColorsUtil.bindBackgroundToColorManager(scrollPane.getViewport());
                GUIColorsUtil.bindBackgroundToColorManager(scrollPane);

                // Holder for vertically stacked ingredients that aligns them to the left for formatting
                JPanel recipeIngredientHolder = new JPanel(new FlowLayout(FlowLayout.LEFT));
                recipeIngredientHolder.setAlignmentX(Component.CENTER_ALIGNMENT);
                GUIColorsUtil.bindBackgroundToColorManager(recipeIngredientHolder);
                recipeIngredientHolder.add(scrollPane);

                // Reset position of scroll panel because text areas send it down (for some reason?)
                SwingUtilities.invokeLater(() -> {
                    scrollPane.getVerticalScrollBar().setValue(0);
                });

                // A vertically stacked panel that holds ingredients
                JPanel instructionsStackedPanel = new JPanel();
                instructionsStackedPanel.setLayout(new BoxLayout(instructionsStackedPanel, BoxLayout.Y_AXIS));
                GUIColorsUtil.bindBackgroundToColorManager(instructionsStackedPanel);
                JTextArea instructionsHeader = new JTextArea("Instructions:");
                instructionsHeader.setWrapStyleWord(true);
                instructionsHeader.setLineWrap(true);
                instructionsHeader.setEditable(false);
                instructionsHeader.setFocusable(false);
                instructionsHeader.setOpaque(false);
                instructionsHeader.setBorder(null);
                instructionsHeader.setFont(new Font("Dialog", Font.BOLD, 22));
                GUIColorsUtil.bindTextToColorManager(instructionsHeader);
                instructionsStackedPanel.add(instructionsHeader);

                // For each ingredient, add them in formatted string to the stacked panel
                for(String inst : rec.getInstructions()) {
                    JTextArea instruction = new JTextArea(inst);                    
                    instruction.setWrapStyleWord(true);
                    instruction.setLineWrap(true);
                    instruction.setEditable(false);
                    instruction.setFocusable(false);
                    instruction.setOpaque(false);
                    instruction.setBorder(null);
                    instruction.setFont(ingredientFont);
                    GUIColorsUtil.bindTextToColorManager(instruction);
                    instructionsStackedPanel.add(instruction);
                }

                // Turn the stacked panel to a scroll panel with specific measures to ensure visibility
                JScrollPane scrollPane2 = new JScrollPane(instructionsStackedPanel);
                scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane2.setPreferredSize(new Dimension(330, 330)); // visible size - tested according to current dimensions
                scrollPane2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
                scrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT);
                scrollPane2.setBorder(BorderFactory.createEmptyBorder());
                GUIColorsUtil.bindBackgroundToColorManager(scrollPane2.getViewport());
                GUIColorsUtil.bindBackgroundToColorManager(scrollPane2);

                // Holder for vertically stacked ingredients that aligns them to the left for formatting
                JPanel recipeInstructionHolder = new JPanel(new FlowLayout(FlowLayout.LEFT));
                recipeInstructionHolder.setAlignmentX(Component.CENTER_ALIGNMENT);
                GUIColorsUtil.bindBackgroundToColorManager(recipeInstructionHolder);
                recipeInstructionHolder.add(scrollPane2);

                // Reset position of scroll panel because text areas send it down (for some reason?)
                SwingUtilities.invokeLater(() -> {
                    scrollPane2.getVerticalScrollBar().setValue(0);
                });

                // Thin separator between instruction and ingredients for aesthetics
                JSeparator horizBreak2 = new JSeparator();
                GUIColorsUtil.bindTextToColorManager(horizBreak2);
                GUIColorsUtil.bindBackgroundToColorManager(horizBreak2);

                // Combine everything with spacing between
                recipeDisplayPanel.add(recipeName);
                recipeDisplayPanel.add(Box.createVerticalStrut(5));
                recipeDisplayPanel.add(horizBreak);
                recipeDisplayPanel.add(recipeIngredientHolder);
                recipeDisplayPanel.add(horizBreak2);
                recipeDisplayPanel.add(recipeInstructionHolder);
                recipeDisplay.add(recipeDisplayPanel);
            }
        });
    }

    private void deleteRecipe(int recipeIndex) {
        listOfRecipes.remove(recipeIndex); // Remove particular recipe
        Serialize.saveRecipeList(listOfRecipes); // Save state of list after removal
        updateRecipes(); // Update shown recipe list based on change
    }

    private void stateNoRecipes() {
        // Create and decorate label stating no recipes, and add to scroll panel (will be removed automatically if any recipes are added)
        JLabel noRecipes = new JLabel("No recipes yet... Create one with the '+' icon!");
        noRecipes.setFont(new Font("Dialog", Font.BOLD, 30));
        noRecipes.setAlignmentX(Component.CENTER_ALIGNMENT);
        GUIColorsUtil.bindTextToColorManager(noRecipes);

        recipeScrollPanel.add(Box.createVerticalStrut(10));
        recipeScrollPanel.add(noRecipes);
    }

    private void editRecipe(int recipeIndex) {
        JFrame editRecipeFrame = new JFrame();
        editRecipeFrame.setSize(new Dimension(750, 750));
        editRecipeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editRecipeFrame.setLocation(750, 0);
        editRecipeFrame.setResizable(false);
        editRecipeFrame.setVisible(true);

        editRecipeFrame.add(new GUI_EditPage(listOfRecipes.get(recipeIndex), recipeIndex, () -> updateRecipes()));
    }
}