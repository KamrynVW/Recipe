package com.textui;

import java.util.ArrayList;
import java.util.Scanner;

import com.recipeme.Recipe;

public class TextUI {

    private final static Scanner userIn = new Scanner(System.in);
    private static final ArrayList<Recipe> recipes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to RecipeMe. Type 'create' to create a recipe or 'search' to look up one.");
        
        while(true) {
            String decision = userIn.nextLine().trim();
            
            if(decision.equalsIgnoreCase("create")) {
                //Create a recipe
                createRecipe();
            } else if(decision.equalsIgnoreCase("search")) {
                //Search for a recipe
                searchRecipe();
            } else {
                //Invalid entry
                System.out.println("Not valid entry.");
            }
        }
    }

    private static void searchRecipe() {
        System.out.println("Enter ingredient or name of recipe to search for:");
        String search = userIn.nextLine().trim();

        int recipeIndex = 0;
        int printedRecipe = 0;

        for(Recipe rec : recipes) {

            if(rec.getName().equalsIgnoreCase(search) || rec.doesRecipeContainIngredient(search) == 1) {
                System.out.println(recipes.get(recipeIndex).getName() + ": " + recipes.get(recipeIndex).getStringIngredients());
                printedRecipe = 1;
            }

            recipeIndex += 1;
        }

        if(printedRecipe == 0) {
            System.out.println("No recipes found.");
        }
    }

    private static void createRecipe() {

    }
}
