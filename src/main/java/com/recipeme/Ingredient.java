package com.recipeme;

import java.util.ArrayList;
import java.util.Arrays;

public class Ingredient {
    
    private String name;
    private String measurement;
    private double quantity;

    /**
     * Default constructor. 
     */
    public Ingredient() {
        super();
    }

    /**
     * Constructor with set name, measurement, and quantity
     * 
     * @param nameOfIngredient The name of the ingredient
     * @param unit The unit used to measure the ingredient
     * @param total The amount of the unit for the ingredient
     */
    public Ingredient(String nameOfIngredient, String unit, double total) {
        this.name = nameOfIngredient;
        this.measurement = unit;
        this.quantity = total;
    }

    /**
     * Get name of ingredient.
     * 
     * @return Name of ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Get measurement unit of ingredient.
     * 
     * @return Name of measurement unit.
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     * Get quantity of ingredient measurement.
     * 
     * @return Quantity of ingredient measurement.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Set the name of the ingredient to a passed String.
     * 
     * @param name New name of ingredient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the measurement unit of the ingredient to a passed String.
     * 
     * @param measurement The name of the new measurement unit.
     */
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    /**
     * Set the quantity of the ingredient to a passed double.
     * 
     * @param quantity Quantity of ingredient.
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Get a list of all the individual words in the name of ingredient for searching purposes.
     * 
     * @return ArrayList of individual words in ingredient name.
     */
    public ArrayList<String> getWordsOfName() {        
        String[] words = this.name.split(" ");
        ArrayList<String> listOfWords = new ArrayList<>(Arrays.asList(words));

        return listOfWords;
    }
}
