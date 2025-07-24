package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import com.recipeme.Ingredient;
import com.recipeme.Recipe;
import com.recipeme.Serialize;

public class GUI_BasePage extends JPanel {

    private final JButton newButton;
    private final JButton settingsButton;
    private final JPanel recipeScrollPanel;
    private final JScrollPane scroll;
    private ArrayList<Recipe> listOfRecipes;
    private final Border recipeListingBorder = BorderFactory.createLineBorder(new Color(0xFF5F1F), 2);
    
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
        searchField.setFont(new Font("Dialog", Font.ITALIC, 20));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(Color.DARK_GRAY);
        searchField.setText("Enter ingredients here (with '!' before item to exclude)...");
        searchField.setBorder(topBarBorder);
        searchField.setMaximumSize(new Dimension(7000, 100));

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if("Enter ingredients here (with '!' before item to exclude)...".equals(searchField.getText()))
                searchField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(searchField.getText())) {
                    searchField.setText("Enter ingredients here (with '!' before item to exclude)...");
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
        
        searchButton.addActionListener(_ -> {
            updateRecipes(searchField.getText());
            GUIColors.INSTANCE.setBackgroundColor(Color.BLACK);
            GUIColors.INSTANCE.setTextColor(Color.CYAN);
        });
        
        // Creation of new recipe button
        newButton = new JButton(" \u271A ");
        newButton.setFont(new Font("Dialog", Font.PLAIN, 35));
        GUIColorsUtil.bindTextToColorManager(newButton);
        newButton.setBackground(Color.DARK_GRAY);
        newButton.setFocusPainted(false);
        newButton.setBorder(topBarBorder);

        settingsButton = new JButton(" \u2699 ");
        settingsButton.setFont(new Font("Dialog", Font.PLAIN, 35));
        settingsButton.setMargin(new Insets(10, 20, 10, 20));
        GUIColorsUtil.bindTextToColorManager(settingsButton);
        settingsButton.setBackground(Color.DARK_GRAY);
        settingsButton.setFocusPainted(false);
        settingsButton.setBorder(topBarBorder);

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

        // Panel set-up
        JPanel listedRecipePanel = new JPanel();
        listedRecipePanel.setBorder(recipeListingBorder);
        GUIColorsUtil.bindBackgroundToColorManager(listedRecipePanel);
        listedRecipePanel.setLayout(new BoxLayout(listedRecipePanel, BoxLayout.Y_AXIS));
        listedRecipePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

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

        listedRecipePanel.add(titleOfRecipe);
        listedRecipePanel.add(listOfIngredients);

        recipeScrollPanel.add(listedRecipePanel);
    }

    public final void updateRecipes() {
        // Load recipes from the save data (to create concurrency) and add all to pane
        listOfRecipes = Serialize.loadRecipeList();

        recipeScrollPanel.removeAll();
        recipeScrollPanel.revalidate();
        recipeScrollPanel.repaint();

        for(Recipe rec : listOfRecipes) {
            addNewRecipe(rec);
        }
    }

    public final void updateRecipes(String searchTerms) {

        boolean showRecipe = true;
        listOfRecipes = Serialize.loadRecipeList();

        recipeScrollPanel.removeAll();
        recipeScrollPanel.revalidate();
        recipeScrollPanel.repaint();  
        
        ArrayList<String> searchTermsSeparated = new ArrayList<>();

        for(String str : searchTerms.split(",")) {
            searchTermsSeparated.add(str.trim());
        }

        for(Recipe rec : listOfRecipes) {
            for(String term : searchTermsSeparated) {
                if(rec.doesRecipeContainIngredient(term) != 1) {
                    showRecipe = false;
                    break;
                }
            }

            if(showRecipe) {
                addNewRecipe(rec);
            } else {
                showRecipe = true;
            }
        }
    }
}
