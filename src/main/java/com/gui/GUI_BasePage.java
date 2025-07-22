package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.Border;

import com.recipeme.Ingredient;
import com.recipeme.Recipe;
import com.recipeme.Serialize;

public class GUI_BasePage extends JPanel {

    private final JButton newButton;
    private final JPanel recipeScrollPanel;
    private final JScrollPane scroll;
    private ArrayList<Recipe> listOfRecipes;
    private final Border recipeListingBorder = BorderFactory.createLineBorder(new Color(0xFF5F1F), 2);

    public GUI_BasePage() {
        
        // Layout setup
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x27282c));
        Border topBarBorder = BorderFactory.createLineBorder(Color.GRAY, 1);

        // Creation of top panel including search bar and buttons
        JPanel topBar = new JPanel();
        topBar.setLayout(new BoxLayout(topBar, BoxLayout.X_AXIS));
        topBar.setBackground(new Color(0x27282c));

        // Creation of search field
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Dialog", Font.ITALIC, 20));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(Color.DARK_GRAY);
        searchField.setText("Enter ingredients here (with '!' before item to exclude)...");
        searchField.setBorder(topBarBorder);

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
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        searchButton.setForeground(new Color(0xFF5F1F));
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(topBarBorder);
        
        // Creation of new recipe button
        newButton = new JButton("New");
        newButton.setFont(new Font("Dialog", Font.PLAIN, 30));
        newButton.setForeground(new Color(0xFF5F1F));
        newButton.setBackground(Color.DARK_GRAY);
        newButton.setFocusPainted(false);
        newButton.setBorder(topBarBorder);

        // Add buttons and fields with specific order to create appropriate bar
        topBar.add(searchField);
        topBar.add(searchButton);
        topBar.add(Box.createHorizontalGlue());
        topBar.add(newButton);
        this.add(topBar, BorderLayout.NORTH);

        recipeScrollPanel = new JPanel();
        recipeScrollPanel.setLayout(new BoxLayout(recipeScrollPanel, BoxLayout.Y_AXIS));
        //recipeScrollPanel.setBackground(new Color(0x27282c));

        scroll = new JScrollPane(recipeScrollPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scroll, BorderLayout.CENTER);

        updateRecipes();

    }

    public JButton getNewButton() {
        return newButton;
    }

    private void addNewRecipe(Recipe rec) {
        // Place recipes into the scroll pane

        // Panel set-up
        JPanel listedRecipePanel = new JPanel();
        listedRecipePanel.setBorder(recipeListingBorder);
        listedRecipePanel.setBackground(new Color(0x27282c));
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
        titleOfRecipe.setForeground(new Color(0xFF5F1F));
        listOfIngredients.setFont(new Font("Dialog", Font.PLAIN, 20));
        listOfIngredients.setForeground(new Color(0xFF5F1F));

        listedRecipePanel.add(titleOfRecipe);
        listedRecipePanel.add(listOfIngredients);

        recipeScrollPanel.add(listedRecipePanel);
    }

    public final void updateRecipes() {
        // Load recipes from the save data (to create concurrency) and add all to pane
        listOfRecipes = Serialize.loadRecipeList();

        for(Recipe rec : listOfRecipes) {
            addNewRecipe(rec);
        }
    }
}
