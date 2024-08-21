package com.recipeme;

import java.util.ArrayList;

public class Recipe {
    
    private String name;
    private int difficulty;
    private ArrayList<Ingredient> ingredients;
    private int numOfInstructions;
    private ArrayList<String> instructions;

    /**
     * Default constructor.
     */
    public Recipe() {
        super();
    }

    /**
     * Constructor with set name, ingredients, and instructions.
     * 
     * @param name Name of recipe.
     * @param ingredients ArrayList of Ingredient objects for the recipe.\
     * @param difficulty Integer representing difficulty of recipe on scale 1-5.
     * @param instructions ArrayList of Strings making up instructions for recipe.
     */
    public Recipe(String name, ArrayList<Ingredient> ingredients, int difficulty, ArrayList<String> instructions) {
        this.name = name;
        roundDifficulty(difficulty);
        this.ingredients = ingredients;
        formatInstructions(instructions);
        this.numOfInstructions = instructions.size();
    }

    /**
     * Get name of recipe.
     * 
     * @return Name of recipe.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the difficulty level of the recipe.
     * 
     * @return Difficulty level of recipe.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Get the list of Ingredient objects.
     * 
     * @return ArrayList of Ingredient objects.
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Get the number of instructions.
     * 
     * @return Number of instructions.
     */
    public int getNumOfInstructions() {
        return numOfInstructions;
    }

    /**
     * Return instructions of recipe.
     * 
     * @return ArrayList of Strings representing step-by-step instructions.
     */
    public ArrayList<String> getInstructions() {
        return instructions;
    }

    /**
     * Set name of recipe.
     * 
     * @param name New name of recipe.
     */
    public void setName(String name) {
        if(name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
    }

    /**
     * Set difficulty level of recipe.
     * 
     * @param difficulty Numberical difficulty level.
     */
    public void setDifficulty(int difficulty) {
        roundDifficulty(difficulty);
    }

    /**
     * Set the ingredients of the recipe.
     * 
     * @param ingredients ArrayList of Ingredient objects.
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        if(ingredients != null) {
            this.ingredients = ingredients;
        } else {
            ArrayList<Ingredient> emptyList = new ArrayList<>();
            this.ingredients = emptyList;
        }
        
    }

    /**
     * Set the instructions and the number of instructions.
     * 
     * @param instructions ArrayList of Strings representing instructions.
     */
    public void setInstructions(ArrayList<String> instructions) {
        if(instructions != null) {
            formatInstructions(instructions);
            this.numOfInstructions = instructions.size();
        } else {
            ArrayList<String> emptyList = new ArrayList<>();
            this.instructions = emptyList;
            this.numOfInstructions = 0;
        }

        
    }

    /**
     * Created numbered instructions and sets them as the instructions of the recipe.
     * 
     * @param instructions Original ArrayList of Strings representing recipe instructions.
     */
    private void formatInstructions(ArrayList<String> instructions) {
        ArrayList<String> numberedInstructions = new ArrayList<>();

        int instructionNumber = 1;

        for(String instruct : instructions) {
            numberedInstructions.add("" + instructionNumber + ". " + instruct);
            instructionNumber = instructionNumber + 1;
        }

        this.instructions = numberedInstructions;
    }

    private void roundDifficulty(int difficulty) {
        if(difficulty < 1) {
            difficulty = 1;
        } else if(difficulty > 5) {
            difficulty = 5;
        }

        this.difficulty = difficulty;
    }

    /**
     * Check if the recipe contains a provided word in the ingredients.
     * 
     * @param nameOfIngredient Ingredient name we are searching for.
     * @return 1 if recipe contains the ingredient, 0 if not.
     */
    public int doesRecipeContainIngredient(String nameOfIngredient) {
        for(Ingredient item : ingredients) {

            if(item.getName().equalsIgnoreCase(nameOfIngredient)) {
                return 1;
            }

            for(String itemName : item.getWordsOfName()) {

                if (itemName.equalsIgnoreCase(nameOfIngredient)) {
                    //If the provided word matches any words in the recipe instructions, return 1.
                    return 1;
                }

            }

        }

        //If we exit the loop without returning 1, the recipe does not contain the ingredient, and we can return 0.
        return 0;
    }

    /**
     * Return a formatted version of the Recipe object.
     * 
     * @return String version of recipe.
     */
    public String getFormattedRecipe() {
        StringBuilder formattedRecipe = new StringBuilder();
        formattedRecipe.append("Name: ").append(this.name).append("\nNeeds: ");

        for(int i = 0; i < this.ingredients.size(); i++) {
            if(this.ingredients.get(i).getQuantity() % 1 == 0) {
                formattedRecipe.append((int)this.ingredients.get(i).getQuantity()).append(" ").append(this.ingredients.get(i).getMeasurement()).append(" ").append(this.ingredients.get(i).getName());
            } else {
                formattedRecipe.append(this.ingredients.get(i).getQuantity()).append(" ").append(this.ingredients.get(i).getMeasurement()).append(" ").append(this.ingredients.get(i).getName());
            }

            if(i < this.ingredients.size() - 1) {
                formattedRecipe.append(", ");
            }

        }

        formattedRecipe.append("\n").append("Instructions:\n");

        for(int j = 0; j < numOfInstructions; j++) {
            formattedRecipe.append(this.instructions.get(j));

            if(j < numOfInstructions - 1) {
                formattedRecipe.append("\n");
            }
        }

        String strFormattedRecipe = formattedRecipe.toString();
        return strFormattedRecipe;
    }

    /**
     * Get a String list of ingredients needed for recipe.
     * 
     * @return String of ingredients.
     */
    public String getStringIngredients() {
        String returnString = "";
        int i = 0;

        for(Ingredient ingred : ingredients) {
            returnString += ingred.getName();

            if(i < ingredients.size() - 1) {
                returnString += ", ";
            }

            i += 1;
        }

        return returnString;
    }
}
