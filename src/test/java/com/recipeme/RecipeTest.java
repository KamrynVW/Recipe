package com.recipeme;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeTest {
    
    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        String name = "Meatballs";
        int difficulty = 3;
        
        Ingredient one = new Ingredient("Ground Beef", "Grams", 500);
        Ingredient two = new Ingredient("Breadcrumbs", "Cups", 0.25);
        Ingredient three = new Ingredient("Seasoning Salt", "Tablespoons", 3);
        Ingredient four = new Ingredient("Pasta Sauce", "Jar", 1);
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(one);
        ingredients.add(two);
        ingredients.add(three);
        ingredients.add(four);

        ArrayList<String> instructions = new ArrayList<>();

        instructions.add("Mix the ground beef, breadcrumbs, and seasoning salt.");
        instructions.add("Roll mix into balls and put into pan over medium heat.");
        instructions.add("When brown on all sides, pour desired amount of pasta sauce into pan and reduce heat to medium-low.");
        instructions.add("Let simmer for 7 minutes or until brown throughout. Serve and enjoy!");

        recipe = new Recipe(name, ingredients, difficulty, instructions);
    }

    //Name Tests

    @Test
    public void getCorrectName() {
        assertEquals("Meatballs", recipe.getName());
    }

    @Test
    public void correctlySetName() {
        String newName = "Italian Meatballs";
        recipe.setName(newName);

        assertEquals("Italian Meatballs", recipe.getName());
    }

    @Test
    public void handleNullName() {
        recipe.setName(null);

        assertEquals("", recipe.getName());
    }

    //Difficulty Tests

    @Test
    public void getCorrectDifficulty() {
        assertEquals(3, recipe.getDifficulty());
    }

    @Test
    public void correctlySetDifficulty() {
        int newDifficulty = 2;
        recipe.setDifficulty(newDifficulty);

        assertEquals(2, recipe.getDifficulty());
    }

    @Test
    public void correctlyRoundDifficultyFloor() {
        recipe.setDifficulty(0);
        assertEquals(1, recipe.getDifficulty());
    }

    @Test
    public void correctlyRoundDifficultyRoof() {
        recipe.setDifficulty(6);
        assertEquals(5, recipe.getDifficulty());
    }

    //Ingredients Tests

    @Test
    public void correctlySetIngredients() {
        Ingredient one = new Ingredient("Ground Beef", "Grams", 500);
        Ingredient two = new Ingredient("Breadcrumbs", "Cups", 0.25);
        Ingredient three = new Ingredient("Pasta Sauce", "Jars", 1);
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(one);
        ingredients.add(two);
        ingredients.add(three);

        recipe.setIngredients(ingredients);

        assertEquals(ingredients, recipe.getIngredients());
    }

    @Test
    public void correctlySetEmptyIngredients() {
        recipe.setIngredients(null);
        ArrayList<Ingredient> emptyList = new ArrayList<>();
        assertEquals(emptyList, recipe.getIngredients());
    }

    @Test
    public void findExistingIngredient() {
        assertEquals(1, recipe.doesRecipeContainIngredient("Breadcrumbs"));
    }

    @Test
    public void findExistingTwoWordIngredientFromWordOne() {
        assertEquals(1, recipe.doesRecipeContainIngredient("Ground"));
    }

    @Test
    public void findExistingTwoWordIngredientFromWordTwo() {
        assertEquals(1, recipe.doesRecipeContainIngredient("Beef"));
    }

    @Test
    public void doNotFindNonexistantIngredient() { 
        assertEquals(0, recipe.doesRecipeContainIngredient("Porkchops"));
    }

    @Test
    public void doNotFindIngredientInEmptyIngredientList() {
        ArrayList<Ingredient> emptyList = new ArrayList<>();
        recipe.setIngredients(emptyList);

        assertEquals(0, recipe.doesRecipeContainIngredient("Breadcrumbs"));
    }

    //Instructions Tests

    @Test
    public void correctlyNumbersInstructions() {
        ArrayList<String> instructions = new ArrayList<>();

        instructions.add("1. Mix the ground beef, breadcrumbs, and seasoning salt.");
        instructions.add("2. Roll mix into balls and put into pan over medium heat.");
        instructions.add("3. When brown on all sides, pour desired amount of pasta sauce into pan and reduce heat to medium-low.");
        instructions.add("4. Let simmer for 7 minutes or until brown throughout. Serve and enjoy!");

        assertEquals(instructions, recipe.getInstructions());
    }

    @Test
    public void getCorrectNumOfInstructions() {
        assertEquals(4, recipe.getNumOfInstructions());
    }

    @Test
    public void correctlySetInstructions() {
        ArrayList<String> instructions = new ArrayList<>();

        instructions.add("Mix the ground beef, breadcrumbs, and seasoning salt; roll into balls and put over medium heat.");
        instructions.add("When brown on all sides, pour desired amount of pasta sauce into pan and reduce heat to medium-low.");
        instructions.add("Let simmer for 7 minutes or until brown throughout. Serve and enjoy!");

        recipe.setInstructions(instructions);

        ArrayList<String> numberedInstructions = new ArrayList<>();

        numberedInstructions.add("1. Mix the ground beef, breadcrumbs, and seasoning salt; roll into balls and put over medium heat.");
        numberedInstructions.add("2. When brown on all sides, pour desired amount of pasta sauce into pan and reduce heat to medium-low.");
        numberedInstructions.add("3. Let simmer for 7 minutes or until brown throughout. Serve and enjoy!");

        assertEquals(numberedInstructions, recipe.getInstructions());
    }

    @Test
    public void getCorrectNumOfInstructionsAfterAssignment() {
        ArrayList<String> instructions = new ArrayList<>();

        instructions.add("Mix the ground beef, breadcrumbs, and seasoning salt; roll into balls and put over medium heat.");
        instructions.add("When brown on all sides, pour desired amount of pasta sauce into pan and reduce heat to medium-low.");
        instructions.add("Let simmer for 7 minutes or until brown throughout. Serve and enjoy!");

        recipe.setInstructions(instructions);

        assertEquals(3, recipe.getNumOfInstructions());
    }

    @Test
    public void correctlySetEmptyInstructions() {
        ArrayList<String> emptyList = new ArrayList<>();

        recipe.setInstructions(emptyList);

        ArrayList<String> testEmptyList = new ArrayList<>();

        assertEquals(testEmptyList, recipe.getInstructions());        
    }

    @Test
    public void getZeroInstructionsFromEmptyList() {
        ArrayList<String> emptyList = new ArrayList<>();

        recipe.setInstructions(emptyList);

        assertEquals(0, recipe.getNumOfInstructions());
    }

    @Test
    public void correctlyFormatsInstructions() {
        String formattedInstructions = "Name: Meatballs\nNeeds: 500 Grams Ground Beef, 0.25 Cups Breadcrumbs, 3 Tablespoons Seasoning Salt, 1 Jar Pasta Sauce\n";
        formattedInstructions += "Instructions:\n1. Mix the ground beef, breadcrumbs, and seasoning salt.\n2. Roll mix into balls and put into pan over medium heat.\n";
        formattedInstructions += "3. When brown on all sides, pour desired amount of pasta sauce into pan and reduce heat to medium-low.\n4. Let simmer for 7 minutes or until brown throughout. Serve and enjoy!";

        assertEquals(formattedInstructions, recipe.getFormattedRecipe());
    }

    @Test
    public void correctlyFormatsEmptyInstructions() {
        ArrayList<String> emptyList = new ArrayList<>();

        recipe.setInstructions(emptyList);

        String formattedInstructions = "Name: Meatballs\nNeeds: 500 Grams Ground Beef, 0.25 Cups Breadcrumbs, 3 Tablespoons Seasoning Salt, 1 Jar Pasta Sauce\n";
        formattedInstructions += "Instructions:\n";

        assertEquals(formattedInstructions, recipe.getFormattedRecipe());
    }
    
}
