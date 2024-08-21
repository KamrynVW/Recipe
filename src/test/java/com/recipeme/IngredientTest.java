package com.recipeme;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IngredientTest {
    
    private String name;
    private String measurement;
    private float quantity;
    private Ingredient ingredient;

    @BeforeEach
    public void setUp() {
        name = "Ground Beef";
        measurement = "Grams";
        quantity = 500;
        ingredient = new Ingredient(name, measurement, quantity);
    }

    @Test
    public void getCorrectName() {
        assertEquals("Ground Beef", ingredient.getName());
    }

    @Test
    public void getCorrectMeasurement() {
        assertEquals("Grams", ingredient.getMeasurement());
    }

    @Test
    public void getCorrectQuantity() {
        assertEquals(500, ingredient.getQuantity());
    }

    @Test
    public void setCorrectName() {
        String newName = "Ground Chicken";
        ingredient.setName(newName);

        assertEquals("Ground Chicken", ingredient.getName());
    }

    @Test
    public void setCorrectMeasurement() {
        String newMeasurement = "Pounds";
        ingredient.setMeasurement(newMeasurement);

        assertEquals("Pounds", ingredient.getMeasurement());
    }

    @Test
    public void setCorrectQuantity() {
        float newQuantity = 1;
        ingredient.setQuantity(newQuantity);

        assertEquals(1, ingredient.getQuantity());
    }

    @Test
    public void getCorrectArrayListOfWords() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Ground");
        list.add("Beef");
        assertEquals(list, ingredient.getWordsOfName());
    }

    @Test
    public void getCorrectArrayListOfWordsWhenOneWord() {
        String newName = "Hamburger";
        ingredient.setName(newName);

        ArrayList<String> list = new ArrayList<>();
        list.add("Hamburger");
        assertEquals(list, ingredient.getWordsOfName());
    }
}
