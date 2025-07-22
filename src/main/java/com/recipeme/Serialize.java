package com.recipeme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public final class Serialize {
    
    private Serialize(){}

    @SuppressWarnings("unchecked")
    public static ArrayList<Recipe> loadRecipeList() {
        File file = new File("recipes.ser");
        ArrayList<Recipe> recs;

        if(!file.exists()) {
            recs = new ArrayList<>();
            return recs;
        }

        try(FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {

            recs = (ArrayList<Recipe>) in.readObject();

        } catch(IOException | ClassNotFoundException e) {

            recs = new ArrayList<>();

        }

        return recs;
    }

    public static void saveRecipeList(ArrayList<Recipe> recs) {
        
        try(FileOutputStream fileOut = new FileOutputStream("recipes.ser"); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(recs);

        } catch(IOException e) {

            System.out.println("ERROR");
            
        }
    }
}
