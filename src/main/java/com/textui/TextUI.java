package com.textui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.recipeme.Ingredient;
import com.recipeme.Recipe;

public class TextUI {

    private final static Scanner userIn = new Scanner(System.in);
    private static ArrayList<Recipe> recipes;

    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[0;34m";
    public static final String CYAN = "\033[0;36m";
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";

    public static void main(String[] args) {
        //Load the list of recipes from the data file
        loadListFromFile("data.ser");
        System.out.println("Welcome to RecipeMe. Type 'help' for commands.");
            
        //Until requesting exit, repeat options
        while(true) {
            System.out.print(CYAN + ">" + RESET);
            String decision = userIn.nextLine().trim();
            if(decision.equalsIgnoreCase("help")) {
                helpMenu();
            } else if(decision.equalsIgnoreCase("create")) {
                //Create a recipe
                createRecipe();
            } else if(decision.equalsIgnoreCase("search")) {
                //Search for a recipe
                searchRecipe();
            } else if(decision.equalsIgnoreCase("edit")) {
                //Edit a recipe
                editRecipe();
            } else if(decision.equalsIgnoreCase("delete")) {
                //Delete a recipe
                deleteRecipe();
            } else if(decision.equalsIgnoreCase("save")) {
                //Save recipe list
                saveListToFile("data.ser");
            } else if(decision.equalsIgnoreCase("exit")) {
                //Exit the program
                System.out.println(GREEN + "Exiting..." + RESET);
                break;
            } else {
                //Invalid entry
                System.out.println(RED + "Not valid entry." + RESET);
            }
        }
    }

    /**
     * Print a help menu for use of the program.
     */
    public static void helpMenu() {
        System.out.println(GREEN + "create - Create a recipe."); 
        System.out.println("search - Search a recipe by name or ingredient.");
        System.out.println("edit - Edit a recipe.");
        System.out.println("delete - Delete a recipe.");
        System.out.println("save - Save the program.");
        System.out.println("exit - Exit the program (does not save automatically)." + RESET);
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

        System.out.println("Difficulty rating of recipe (1-5):");
        difficulty = userIn.nextInt();
        userIn.nextLine();

        boolean cont = true;
        while(cont) {
            System.out.println("Enter the name of 1 needed ingredient:");
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

        System.out.println(GREEN + "Creating " + nameOfRecipe + "..." + RESET);
        Recipe newRecipe = new Recipe(nameOfRecipe, ingredients, difficulty, instructions);
        recipes.add(newRecipe);
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
                System.out.println(GREEN + "\n" + recipes.get(recipeIndex).getName() + ": " + recipes.get(recipeIndex).getStringIngredients() + RESET);
                System.out.println(BLUE + "\n" + rec.getFormattedRecipe() + RESET + "\n");
                printedRecipe = 1;
            }

            recipeIndex += 1;
        }

        if(printedRecipe == 0) {
            System.out.println(RED + "No recipes found." + RESET);
        }
    }

    /**
     * Edit a selected recipe.
     */
    public static void editRecipe() {
        boolean cont;

        System.out.println(GREEN + "Choose a recipe to edit:\n" + RESET);
        for(Recipe rec : recipes) {
            System.out.println(rec.getName());
        }
        System.out.print("\n");

        String decision = userIn.nextLine().trim();

        for (Recipe currentRecipe : recipes) {
            if(currentRecipe.getName().equalsIgnoreCase(decision)) {
                //Enter editing mode
                
                //Edit the name of the recipe
                System.out.println(GREEN + "Edit the name (y/n):" + RESET);
                String yesNo = userIn.nextLine().trim();

                if(yesNo.equalsIgnoreCase("y")) {
                    System.out.println("Name of recipe:");
                    String newName = userIn.nextLine().trim();
                    currentRecipe.setName(newName);
                }

                //Edit the difficulty of the recipe
                System.out.println(GREEN + "Edit the difficulty(y/n):" + RESET);
                yesNo = userIn.nextLine().trim();

                if(yesNo.equalsIgnoreCase("y")) {
                    System.out.println("Difficulty rating of recipe(1-5):");
                    int newDifficulty = userIn.nextInt();
                    userIn.nextLine();
                    currentRecipe.setDifficulty(newDifficulty);
                }

                //Edit the ingredients of the recipe
                System.out.println(GREEN + "Edit the ingredients(y/n):" + RESET);
                yesNo = userIn.nextLine().trim();

                if(yesNo.equalsIgnoreCase("y")) {
                    ArrayList<Ingredient> newIngredients = new ArrayList<>();
                    cont = true;

                    while(cont) {
                        System.out.println("Enter the name of 1 needed ingredient:");
                        String nameOfIngredient = userIn.nextLine().trim();
                        System.out.println("Unit of measurement:");
                        String measurement = userIn.nextLine().trim();
                        System.out.println("Quantity of measurement (as decimal):");
                        double quantity = userIn.nextDouble();
                        userIn.nextLine();
                        Ingredient newIngredient = new Ingredient(nameOfIngredient, measurement, quantity);
                        newIngredients.add(newIngredient);
                        System.out.println("Add another ingredient (y/n): ");
                        String response = userIn.nextLine().trim();

                        if(response.equalsIgnoreCase("n")) {
                            cont = false;
                        }
                    }

                    currentRecipe.setIngredients(newIngredients);
                }

                //Edit the instructions of the recipe
                System.out.println(GREEN + "Edit the instructions(y/n)" + RESET);
                yesNo = userIn.nextLine().trim();

                if(yesNo.equalsIgnoreCase("y")) {
                    ArrayList<String> newInstructions = new ArrayList<>();
                    cont = true;
                    int instructNum = 1;
                    
                    while(cont) {
                        System.out.println("Instruction " + instructNum + ":");
                        String newInstruct = userIn.nextLine().trim();
                        newInstructions.add(newInstruct);
                        System.out.println("Add another instruction (y/n): ");
                        String response = userIn.nextLine().trim();

                        if(response.equalsIgnoreCase("n")) {
                            cont = false;
                        } else {
                            instructNum += 1;
                        }
                    }

                    currentRecipe.setInstructions(newInstructions);
                }

                System.out.println(GREEN + "Edits complete." + RESET);
                return;
            }
        }

        System.out.println(RED + "No recipe found with name " + decision + "." + RESET);
    }

    /**
     * Delete a recipe by user-entered name.
     */
    public static void deleteRecipe() {
        System.out.println("Enter the name of the recipe to be deleted:");
        String nameOfRecipe = userIn.nextLine().trim();

        //Iterator for going over ArrayList of recipes.
        Iterator<Recipe> iterator = recipes.iterator();
        while(iterator.hasNext()) {
            //If the name is found, remove the recipe from the list and return to main menu.
            if(iterator.next().getName().equalsIgnoreCase(nameOfRecipe)) {
                iterator.remove();
                return;
            }
        }

        //If recipe is not found, print and return to main menu
        System.out.println(RED + "No recipe found with name " + nameOfRecipe + "." + RESET);
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
            System.out.println(RED + "Error." + RESET);
        }

        System.out.println(GREEN + "Saved." + RESET);
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
}