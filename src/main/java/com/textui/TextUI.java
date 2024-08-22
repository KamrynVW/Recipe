package com.textui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.recipeme.Ingredient;
import com.recipeme.Recipe;

public class TextUI {

    private final static Scanner userIn = new Scanner(System.in);
    private static ArrayList<Recipe> recipes;

    public static void main(String[] args) {
        //Load the list of recipes from the data file
        loadListFromFile("data.ser");
        
        //Until requesting exit, repeat options
        while(true) {
            System.out.println("Welcome to RecipeMe. Type 'create' to create a recipe or 'search' to look up one.");
            String decision = userIn.nextLine().trim();
            
            if(decision.equalsIgnoreCase("create")) {
                //Create a recipe
                createRecipe();
            } else if(decision.equalsIgnoreCase("search")) {
                //Search for a recipe
                searchRecipe();
            } else {
                //Invalid entry/exit
                System.out.println("Not valid entry. Exiting...");
                saveListToFile("data.ser");
                break;
            }
        }
    }

    /**
     * Load the recipe list from the saved file, should it exist.
     * 
     * @param fileName Name of file to read data from.
     */
    @SuppressWarnings("unchecked")
    private static void loadListFromFile(String fileName) {
        File file = new File(fileName);

        //If the data file does not exist, create a new recipe list and move on
        if(!file.exists()) {
            recipes = new ArrayList<>();
            return;
        }

        //Load the recipe list from data file, or create a new recipe list if an issue occurs
        try(FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn)) {

            recipes = (ArrayList<Recipe>) in.readObject();

        } catch(IOException | ClassNotFoundException e) {
            recipes = new ArrayList<>();
        }
    }

    /**
     * Save the recipe list to a file for future uses.
     * 
     * @param fileName File name to save data to.
     */
    private static void saveListToFile(String fileName) {
        //Save the recipe list to the data file
        try(FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(recipes);
        } catch(IOException e) {
            System.out.println("Error.");
        }
    }

    /**
     * Search for a recipe based off the name of a recipe or ingredient.
     */
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

    /**
     * Create a recipe with user-input data and add it to the recipes.
     */
    private static void createRecipe() {
        String nameOfRecipe;
        int difficulty;
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<String> instructions = new ArrayList<>();

        System.out.println("Name of recipe: ");
        nameOfRecipe = userIn.nextLine().trim();

        System.out.println("\nDifficulty rating of recipe (1-5):");
        difficulty = userIn.nextInt();
        userIn.nextLine();

        boolean cont = true;
        while(cont) {
            System.out.println("\nEnter the name of 1 needed ingredient:");
            String nameOfIngredient = userIn.nextLine().trim();
            System.out.println("Unit of measurement:");
            String measurement = userIn.nextLine().trim();
            System.out.println("Quantity of measurement (as decimal):");
            double quantity = userIn.nextDouble();
            userIn.nextLine();
            Ingredient ingredient = new Ingredient(nameOfIngredient, measurement, quantity);
            ingredients.add(ingredient);
            System.out.println("Add another ingredient (y/n): ");
            String response = userIn.nextLine().trim();

            if(response.equalsIgnoreCase("n")) {
                cont = false;
            }
        }

        cont = true;
        int instructNum = 1;
        while(cont) {
            System.out.println("Instruction " + instructNum + ":");
            String instruct = userIn.nextLine().trim();
            instructions.add(instruct);
            System.out.println("Add another instruction (y/n): ");
            String response = userIn.nextLine().trim();

            if(response.equalsIgnoreCase("n")) {
                cont = false;
            } else {
                instructNum += 1;
            }
        }

        System.out.println("Creating " + nameOfRecipe + "...");
        Recipe newRecipe = new Recipe(nameOfRecipe, ingredients, difficulty, instructions);
        recipes.add(newRecipe);
    }
}
